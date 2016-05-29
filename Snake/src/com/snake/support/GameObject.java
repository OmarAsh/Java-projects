package com.snake.support;

import java.awt.Graphics;
import java.awt.Point;

public abstract class GameObject {

	Handler handler;
	Point point;

	public static final int LENGTH = 20;

	public GameObject(Handler handler) {
		this.handler = handler;
	}

	public GameObject(int x, int y, Handler handler) {
		point = new Point(x, y);
		this.handler = handler;
	}

	public abstract void tick();

	public abstract void render(Graphics g);

	public int translateCo(int n) {
		return n * 20;
	}

	// Getters
	public Point getPoint() {
		return point;
	}

	// Setters
	public void setPoint(Point point) {
		this.point = point;
	}
}
