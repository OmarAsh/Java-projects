package com.battleroyale.support;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Handler {

	Font font = new Font("Arial", 1, 25);
	BufferedImage bg;
	Player leftPlayer;
	Player rightPlayer;

	public Handler() {
		try {
			bg = ImageIO.read(new File("img/bg.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		leftPlayer = new Player(ID.LEFTPLAYER, this);
		rightPlayer = new Player(ID.RIGHTPLAYER, this);
	}

	public void tick() {
		leftPlayer.tick();
		rightPlayer.tick();
	}

	public void render(Graphics g) {
		g.setFont(font);
		
		g.drawImage(bg, 0, 0, null);
		leftPlayer.render(g);
		rightPlayer.render(g);
	}
}
