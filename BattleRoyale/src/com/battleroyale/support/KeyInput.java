package com.battleroyale.support;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

	Handler handler;
	Player leftPlayer, rightPlayer;
	int key;

	public KeyInput(Handler handler) {
		this.handler = handler;
	}

	public void keyPressed(KeyEvent e) {
		key = e.getKeyCode();
		if (leftPlayer == null) {
			leftPlayer = handler.leftPlayer;
			rightPlayer = handler.rightPlayer;
		}

		if (key == KeyEvent.VK_ESCAPE)
			System.exit(0);

		// Left Player
		else if (key == KeyEvent.VK_W)
			leftPlayer.setLane(leftPlayer.getLane() - 1);
		else if (key == KeyEvent.VK_S)
			leftPlayer.setLane(leftPlayer.getLane() + 1);
		else if (key == KeyEvent.VK_A)
			leftPlayer.setActiveSpawn(leftPlayer.getActiveSpawn() - 1);
		else if (key == KeyEvent.VK_D)
			leftPlayer.setActiveSpawn(leftPlayer.getActiveSpawn() + 1);
		else if (key == KeyEvent.VK_SPACE)
			leftPlayer.spawnUnit();

		// Right Player
		else if (key == KeyEvent.VK_UP)
			rightPlayer.setLane(rightPlayer.getLane() - 1);
		else if (key == KeyEvent.VK_DOWN)
			rightPlayer.setLane(rightPlayer.getLane() + 1);
		else if (key == KeyEvent.VK_LEFT)
			rightPlayer.setActiveSpawn(rightPlayer.getActiveSpawn() - 1);
		else if (key == KeyEvent.VK_RIGHT)
			rightPlayer.setActiveSpawn(rightPlayer.getActiveSpawn() + 1);
		else if (key == KeyEvent.VK_ENTER)
			rightPlayer.spawnUnit();
	}
}
