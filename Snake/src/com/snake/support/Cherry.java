package com.snake.support;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Cherry extends GameObject {

	int tempX, tempY;
	Color color = new Color(217, 90, 78);

	public Cherry(Handler handler) {
		super(handler);
		setRandomPoint();
	}

	public void setRandomPoint() {
		tempX = (int) (Math.random() * 15) * 20;
		tempY = (int) (Math.random() * 15) * 20;
		setPoint(new Point(tempX, tempY));
	}

	public void tick() {
		// This'll probably stay empty..
	}

	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect(point.x, point.y, LENGTH, LENGTH);
	}

}
