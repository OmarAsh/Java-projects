package com.chess.support;

import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseEvents extends MouseAdapter {

	private Handler handler;
	int mouseX, mouseY;
	Rectangle mousePos;
	Piece tempPiece;
	Player tempPlayer;

	public MouseEvents(Handler handler) {
		this.handler = handler;
	}

	public void mouseMoved(MouseEvent me) {
		// System.out.println("Moved");
	}

	public void mouseClicked(MouseEvent me) {
		mouseX = me.getX();
		mouseY = me.getY();
		// System.out.println("X: " + mouseX + " Y: " + mouseY);
		mousePos = new Rectangle(mouseX, mouseY, 1, 1);

		for (int i = 0; i < handler.player.size(); i++) {
			tempPlayer = handler.player.get(i);
			if (tempPlayer.isTurn) {

				for (int j = 0; j < tempPlayer.piece.size(); j++) {
					tempPiece = tempPlayer.piece.get(j);

					if (mousePos.intersects(tempPiece.getBounds())) {
						if (!tempPiece.getShowMoves())
							tempPiece.setShowMoves(true);

						else if (tempPiece.getShowMoves())
							tempPiece.setShowMoves(false);
					} else
						tempPiece.setShowMoves(false);

					if (!tempPiece.availableMoves.isEmpty()) {
						for (int k = 0; k < tempPiece.availableMoves.size(); k++) {
							if (mousePos.intersects(tempPiece.getBoundsMoves(k))) {
								tempPiece.setPos(tempPiece.translateCoordinate(tempPiece.availableMoves.get(k).x,
										tempPiece.availableMoves.get(k).y));
							}
						}
					}
				}
			}
		}
	}

}
