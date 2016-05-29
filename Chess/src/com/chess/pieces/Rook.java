package com.chess.pieces;

import java.awt.Graphics;
import java.awt.Point;

import com.chess.support.ID;
import com.chess.support.Piece;
import com.chess.support.Player;

public class Rook extends Piece {

	public Rook(ID id, String pos, String color, Player player) {
		super(id, pos, color, player);
	}

	public void getMoves() {
		
		for (int i = 1; x + i*70 <= 9*70; i++) {
			if (!tryAppend(i, 0)){
				break;
			}
		}
		for (int i = 1; y - i*70 <= 9*70; i++) {
			if (!tryAppend(0,i)){
				break;
			}
		}
		
		for (int i = 1; x - i*70 <= 9*70; i++) {
			if (!tryAppend(-i, 0)){
				break;
			}
		}
		for (int i = 1; y + i*70 <= 9*70; i++) {
			if (!tryAppend(0, -i)){
				break;
			}
		}
	}

	public void tick() {
		translatePos();
		if (getShowMoves() && availableMoves.isEmpty())
			getMoves();
		else if (!getShowMoves())
			removeMoves();
	}

	public void render(Graphics g) {
		for (int i = 0; i < availableMoves.size(); i++) {
			g.setColor(possibleMove);
			Point tempSquare = availableMoves.get(i);

			g.fillRect(tempSquare.x+5, tempSquare.y+5, 60, 60);
		}
		
		g.drawImage(img, x, y, null);
	}

}
