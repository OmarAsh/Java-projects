package com.snake.support;

import java.awt.Graphics;

public class Part extends GameObject {
	
	int maxSide = 15 * 20;
	public Part(int x, int y, Handler handler) {
		super(x, y, handler);
	}

	public void tick() {
		if (point.x < 0)
			point.x = maxSide;
		else if (point.x > maxSide)
			point.x = 0;
		
		if (point.y < 0)
			point.y = maxSide;
		else if (point.y > maxSide)
			point.y = 0;
			
	}

	public void render(Graphics g) {
		g.fillRect(point.x, point.y, LENGTH, LENGTH);
	}
}
