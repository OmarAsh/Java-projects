package com.snake.support;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.snake.main.Game;

public class Handler {

	Snake snake;
	Cherry cherry;

	int score = 0;
	Font font = new Font("Arial", 1, 20);

	Color bg = new Color(242, 224, 201);

	public Handler() {
		snake = new Snake(this);
		cherry = new Cherry(this);
	}

	public void tick() {
		cherry.tick();
		snake.tick();
	}

	public void render(Graphics g) {
		g.setColor(bg);
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);

		cherry.render(g);
		snake.render(g);

		g.setColor(Color.gray);
		g.setFont(font);
		g.drawString(String.valueOf(score), 10, 20);
	}
}
