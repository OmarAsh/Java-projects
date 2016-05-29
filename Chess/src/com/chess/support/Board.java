package com.chess.support;

import java.awt.Color;
import java.awt.Graphics;

import com.chess.main.Game;

public class Board {

	int x, y;
	Color dark = new Color(156,135,105);
	Color light = new Color(194,174,134);
	public static final int SQUARE_LENGTH = 70;
	public static final int[][] board_xy = new int [2][8];
	int tempX, tempY;
	
	public Board(int x, int y){
		this.x = x;
		this.y = y;
		setupCoordinates();
	}
	
	public void setupCoordinates(){
		for (int i = 0; i < 8; i++){
			board_xy[0][i] = i * SQUARE_LENGTH + x;
			board_xy[1][i] = i * SQUARE_LENGTH + y;
		}
	}
	
	public void render(Graphics g){
		g.clearRect(0, 0, Game.WIDTH, Game.HEIGHT);
		
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				
				if (i % 2 == j % 2)
					g.setColor(dark);
				else
					g.setColor(light);
				
				tempX = board_xy[0][i];
				tempY = board_xy[1][j];
				g.fillRect(tempX, tempY, SQUARE_LENGTH, SQUARE_LENGTH);
				
				g.setColor(Color.white);
				g.drawRect(tempX, tempY, SQUARE_LENGTH, SQUARE_LENGTH);
			}
		}
	}
}
