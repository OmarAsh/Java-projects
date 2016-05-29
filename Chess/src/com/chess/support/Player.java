package com.chess.support;

import java.awt.Graphics;
import java.util.ArrayList;

import com.chess.pieces.Bishop;
import com.chess.pieces.King;
import com.chess.pieces.Knight;
import com.chess.pieces.Pawn;
import com.chess.pieces.Queen;
import com.chess.pieces.Rook;

public class Player {

	ArrayList<Piece> piece = new ArrayList<Piece>();
	String color;
	ID id;
	boolean isTurn;

	String alpha = "ABCDEFGH";
	int pawnsAt;
	int othersAt;
	
	Handler handler;

	public Player(ID id, String color, Handler handler) {
		this.id = id;
		this.color = color;
		this.handler = handler;
		setupPieces();
	}

	public void setupPieces() {
		if (id == ID.WHITEPLAYER) {
			othersAt = 1;
			pawnsAt = 2;
		} else if (id == ID.BLACKPLAYER) {
			othersAt = 8;
			pawnsAt = 7;
		}
		piece.add(new Rook(ID.ROOK, "A" + othersAt, color, this));
		piece.add(new Rook(ID.ROOK, "H" + othersAt, color, this));

		piece.add(new Knight(ID.KNIGHT, "B" + othersAt, color, this));
		piece.add(new Knight(ID.KNIGHT, "G" + othersAt, color, this));

		piece.add(new Bishop(ID.BISHOP, "C" + othersAt, color, this));
		piece.add(new Bishop(ID.BISHOP, "F" + othersAt, color, this));

		piece.add(new Queen(ID.QUEEN, "D" + othersAt, color, this));

		piece.add(new King(ID.KING, "E" + othersAt, color, this));

		for (int i = 0; i < 8; i++) {
			piece.add(new Pawn(ID.PAWN, String.valueOf(alpha.charAt(i)) + pawnsAt, color, this));
		}

	}

	public void tick() {
		for (int i = 0; i < piece.size(); i++) {
			piece.get(i).tick();
		}
	}

	public void render(Graphics g) {
		for (int i = 0; i < piece.size(); i++) {
			piece.get(i).render(g);
		}
	}
}
