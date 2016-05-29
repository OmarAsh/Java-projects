package com.chess.support;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public abstract class Piece {

	protected ArrayList<Point> availableMoves = new ArrayList<Point>();
	protected Point tempMove;
	protected Color possibleMove = Color.orange;

	protected Player player;
	protected Player enemy;

	protected String tempLetter;
	protected int tempNum;

	protected String pos;
	protected ID id;
	protected boolean showMoves;
	protected String color;
	protected int x, y;
	protected Image img;
	protected String type;

	protected int width, height;

	String alpha = "ABCDEFGH";

	public Piece(ID id, String pos, String color, Player player) {
		this.id = id;
		this.pos = pos;
		this.color = color;
		this.player = player;
		setEnemy();
		getImage();
	}

	public void setEnemy() {
		if (player.id == ID.WHITEPLAYER)
			enemy = player.handler.blackPlayer;
		else if (player.id == ID.BLACKPLAYER)
			enemy = player.handler.whitePlayer;
	}

	public void getImage() {
		type = String.valueOf(id).toLowerCase();
		img = new ImageIcon(getClass().getResource("/" + color + "/" + type + ".png")).getImage();

		width = img.getWidth(null);
		height = img.getHeight(null);
	}

	public abstract void getMoves();

	public abstract void tick();

	public abstract void render(Graphics g);

	public void translatePos() {
		pos.toUpperCase(); // In case it isn't

		for (int i = 0; i < 8; i++) {
			if (pos.charAt(0) == alpha.charAt(i))
				x = Board.board_xy[0][i];
		}
		y = Board.board_xy[1][8 - Integer.parseInt(String.valueOf(pos.charAt(1)))];
	}

	public String translateCoordinate(int x, int y) {
		try {
			tempLetter = alpha.charAt((x - 110) / 70) + "";
			for (int i = 0; i < Board.board_xy[1].length; i++) {
				if (y == Board.board_xy[1][i]) {
					tempNum = 8 - i;
				}
			}
			return tempLetter + tempNum;
		} catch (Exception e) {
			return null;
		}
	}

	public boolean tryAppend(int x, int y) {
		int fix = 1;
		if (color == "white")
			fix = -1;
		else if (color == "black")
			fix = 1;

		if (this.x + (x * 70) >= Board.board_xy[0][0] && this.x + (x * 70) <= Board.board_xy[0][7]
				&& this.y + (y * 70 * fix) >= Board.board_xy[1][0] && this.y + (y * 70 * fix) <= Board.board_xy[1][7]) {

			if (spotIsEnemy(translateCoordinate(this.x + x * 70, this.y + (y * 70 * fix)))) {
				availableMoves.add(new Point(this.x + (x * 70), this.y + (y * 70 * fix)));
				return false;
			}
			if (!spotIsFriendly(translateCoordinate(this.x + x * 70, this.y + (y * 70 * fix)))) {
				availableMoves.add(new Point(this.x + (x * 70), this.y + (y * 70 * fix)));
				return true;
			}

		}
		return false;
	}

	public void checkEnemiesPawn() {
		int fix = 1;
		if (color == "white")
			fix = -1;
		else if (color == "black")
			fix = 1;

		if (translateCoordinate(x + 70, y + (70 * fix)) != null)
			if (spotIsEnemy(translateCoordinate(x + 70, y + (70 * fix))))
				availableMoves.add(new Point(x + 70, y + (70 * fix)));

		if (translateCoordinate(x - 70, y + (70 * fix)) != null)
			if (spotIsEnemy(translateCoordinate(x - 70, y + (70 * fix))))
				availableMoves.add(new Point(x - 70, y + (70 * fix)));

	}

	public boolean spotIsEnemy(String pos) {
		if (enemy == null) {
			setEnemy();
		}
		for (int i = 0; i < enemy.piece.size(); i++) {
			if (pos.equals(enemy.piece.get(i).getPos()))
				return true;
		}
		return false;
	}

	public boolean spotIsFriendly(String pos) {
		for (int i = 0; i < player.piece.size(); i++) {
			if (pos.equals(player.piece.get(i).getPos()))
				return true;
		}
		return false;
	}

	public void killEnemy(String pos) {
		if (enemy == null) {
			setEnemy();
		}
		for (int i = 0; i < enemy.piece.size(); i++) {
			if (enemy.piece.get(i).getPos().equals(pos))
				enemy.piece.remove(i);
		}
	}

	public void removeMoves() {
		availableMoves.clear();
	}

	// Getters
	public String getPos() {
		return pos;
	}

	public ID getID() {
		return id;
	}

	public boolean getShowMoves() {
		return showMoves;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

	public Rectangle getBoundsMoves(int i) {
		tempMove = availableMoves.get(i);
		return new Rectangle(tempMove.x, tempMove.y, 70, 70);
	}

	// Setters
	public void setPos(String pos) {
		this.pos = pos;
		killEnemy(pos);
		setShowMoves(false);
		player.isTurn = false;
		enemy.isTurn = true;

	}

	public void setID(ID id) {
		this.id = id;
	}

	public void setShowMoves(boolean showMoves) {
		this.showMoves = showMoves;
	}
}
