package com.battleroyale.main;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import com.battleroyale.support.Handler;
import com.battleroyale.support.KeyInput;


public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 1000, HEIGHT = WIDTH * 9 / 12;
	Thread thread;
	boolean running;
	Handler handler;

	public static void main(String args[]) {
		new Game();
	}

	public Game() {
		handler = new Handler();

		KeyInput e = new KeyInput(handler);
		this.addKeyListener(e);

		new Window(WIDTH, HEIGHT, "Battle Royale", this);
		System.out.println("width: " + WIDTH + ", height: " + HEIGHT);
	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public synchronized void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			if (running)
				render();

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
			}
		}
	}

	private void tick() {
		handler.tick();
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		handler.render(g);

		g.dispose();
		bs.show();
	}

}
