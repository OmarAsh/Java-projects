package com.chess.pieces;

import java.awt.Graphics;
import java.awt.Point;

import com.chess.support.ID;
import com.chess.support.Piece;
import com.chess.support.Player;

public class King extends Piece {

	public King(ID id, String pos, String color, Player player) {
		super(id, pos, color, player);
	}

	public void getMoves() {
		tryAppend(-1,0);
		tryAppend(1,0);
		tryAppend(0,1);
		tryAppend(0,-1);
		tryAppend(-1,1);
		tryAppend(-1,-1);
		tryAppend(1,1);
		tryAppend(1,-1);
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
