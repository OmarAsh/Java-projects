package com.snake.support;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

	Handler handler;
	int key;

	public KeyInput(Handler handler) {
		this.handler = handler;
	}

	public void keyPressed(KeyEvent e) {
		key = e.getKeyCode();

		if (key == KeyEvent.VK_ESCAPE)
			System.exit(0);
		else if (key == KeyEvent.VK_UP) {
			handler.snake.dir = DIR.UP;
			handler.snake.velX = 0;
			handler.snake.velY = -1;
		} else if (key == KeyEvent.VK_DOWN) {
			handler.snake.dir = DIR.DOWN;
			handler.snake.velX = 0;
			handler.snake.velY = 1;
		} else if (key == KeyEvent.VK_LEFT) {
			handler.snake.dir = DIR.LEFT;
			handler.snake.velX = -1;
			handler.snake.velY = 0;
		} else if (key == KeyEvent.VK_RIGHT) {
			handler.snake.dir = DIR.RIGHT;
			handler.snake.velX = 1;
			handler.snake.velY = 0;
		} else if (key == KeyEvent.VK_SPACE)
			handler.cherry.setRandomPoint();
	}
}
