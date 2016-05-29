package com.snake.support;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Snake {

	Handler handler;
	ArrayList<Part> snakeParts = new ArrayList<Part>();

	DIR dir;
	int velX = 1, velY = 0;
	int tempX, tempY;

	Color headColor = new Color(17, 140, 70);
	Color bodyColor = new Color(36, 166, 82);
	Rectangle tempPart, tempCherry;
	Part headPart, tailPart;

	public Snake(Handler handler) {
		this.handler = handler;

		snakeParts.add(new Part(20, 20, handler));
		snakeParts.add(new Part(40, 20, handler));
		snakeParts.add(new Part(60, 20, handler));
	}

	public void tick() {
		headPart = snakeParts.get(snakeParts.size() - 1);
		tailPart = snakeParts.get(0);
		tempPart = new Rectangle(headPart.point.x, headPart.point.y, Part.LENGTH, Part.LENGTH);
		tempCherry = new Rectangle(handler.cherry.point.x, handler.cherry.point.y, Part.LENGTH, Part.LENGTH);

		if (tempPart.intersects(tempCherry)) {
			handler.cherry.setRandomPoint();
			snakeParts.add(0, new Part(tailPart.point.x, tailPart.point.y, handler));
			handler.score++;
		}

		// Moving
		tempX = headPart.point.x +(20* velX);
		tempY = headPart.point.y +(20* velY);
		snakeParts.add(new Part(tempX, tempY, handler));
		snakeParts.remove(0);
		
		for (int i = 0; i < snakeParts.size(); i++){
			snakeParts.get(i).tick();
		}

	}

	public void render(Graphics g) {
		g.setColor(bodyColor);
		for (int i = 0; i < snakeParts.size(); i++) {
			if (i == snakeParts.size() - 1) // Last element
				g.setColor(headColor);

			snakeParts.get(i).render(g);
		}
	}
}
