package com.battleroyale.support;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Unit {

	protected Player player;
	protected Player enemy;

	protected Color color;
	protected ID id;
	protected STANCE stance;
	protected int cost;
	protected int speed;
	protected int hp, dmg, randomDmg;
	protected int lane;
	protected int x, y;

	public Unit(ID id, int lane, Player player) {
		this.id = id;
		this.player = player;
		stance = STANCE.NEUTRAL;
		setup();
		setDefaultSpeed();
		setLane(lane);
	}

	public abstract void tick();

	public abstract void render(Graphics g);

	public void setup() {
		x = player.spawnX;

		if (id == ID.SOLDIER) {
			color = Color.black;
			speed = 1;
		} else if (id == ID.ARCHER) {
			color = Color.red;
			speed = 1;
		} else if (id == ID.KNIGHT) {
			color = Color.green;
			speed = 2;
		}
	}

	public void setDefaultSpeed() {
		if (id == ID.SOLDIER)
			speed = 1;
		else if (id == ID.ARCHER)
			speed = 1;
		else if (id == ID.KNIGHT)
			speed = 2;
	}

	public void determineEnemy() {
		if (enemy == null) {
			if (player.id == ID.LEFTPLAYER)
				enemy = player.handler.rightPlayer;
			else if (player.id == ID.RIGHTPLAYER)
				enemy = player.handler.leftPlayer;
		}
	}

	public void moneyForKill(Unit deadUnit) {
		if (deadUnit.getID() == ID.SOLDIER)
			player.setMoney(player.getMoney() + 50);
		else if (deadUnit.getID() == ID.ARCHER)
			player.setMoney(player.getMoney() + 75);
		else if (deadUnit.getID() == ID.KNIGHT)
			player.setMoney(player.getMoney() + 100);
	}

	public void removeUnit(Player player, Unit unit) {
		player.unit.remove(unit);
	}

	public int getCost() {
		return cost;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 40, 40);
	}

	public Rectangle getBounds(String ranged) {
		if (ranged.equals("ranged")) {
			return new Rectangle(x - 200, y, 40 + 400, 40);
		} else
			return null;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getLane() {
		return lane;
	}

	public void setLane(int lane) {
		this.lane = lane;
		y = lane * 64 + (lane * 40) + 90;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public ID getID() {
		return id;
	}

	public void setID(ID id) {
		this.id = id;
	}
}
