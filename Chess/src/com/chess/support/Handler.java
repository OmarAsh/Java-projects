package com.chess.support;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Handler {
	
	Board board;
	Player whitePlayer;
	Player blackPlayer;
	ArrayList<Player> player = new ArrayList<Player>();
	
	Color turn;
	
	public Handler(){
		board = new Board(110,0);
		whitePlayer = new Player(ID.WHITEPLAYER, "white", this);
		blackPlayer = new Player(ID.BLACKPLAYER, "black", this);
		
		player.add(whitePlayer);
		player.add(blackPlayer);
		whitePlayer.isTurn = true;
	}
	

	public void tick() {
		whitePlayer.tick();
		blackPlayer.tick();
	}

	public void render(Graphics g) {
		board.render(g);
		whitePlayer.render(g);
		blackPlayer.render(g);
		whitePlayer.render(g);
		
		if (whitePlayer.isTurn)
			turn = Color.white;
		else if (blackPlayer.isTurn)
			turn = Color.black;
		g.setColor(turn);
		
		g.fillRect(10, 10, 50, 50);
		g.setColor(Color.black);
		g.drawRect(10, 10, 50, 50);
	}
}
