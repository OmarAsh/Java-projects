package com.battleroyale.support;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.battleroyale.main.Game;
import com.battleroyale.units.Archer;
import com.battleroyale.units.Knight;
import com.battleroyale.units.Soldier;

public class Player {

	Handler handler;
	BufferedImage arrow;
	int lane = 1, y;
	int activeSpawn = 1;

	ID tempId;

	ID id;
	public int dir;
	int hp = 1000;
	int spawnX;
	double money = 500.0;

	ID activeUnit;
	public ArrayList<Unit> unit = new ArrayList<Unit>(0);

	public Player(ID id, Handler handler) {
		this.id = id;
		this.handler = handler;
		try {
			setup();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setup() throws IOException {
		if (id == ID.LEFTPLAYER) {
			dir = 1;
			spawnX = 0;
			arrow = ImageIO.read(new File("img/arrowRight.png"));
		} else if (id == ID.RIGHTPLAYER) {
			dir = -1;
			spawnX = 960;
			arrow = ImageIO.read(new File("img/arrowLeft.png"));
		}
	}

	public void spawnUnit() {
		if (activeSpawn == 1)
			tempId = ID.SOLDIER;
		else if (activeSpawn == 2)
			tempId = ID.ARCHER;
		else if (activeSpawn == 3)
			tempId = ID.KNIGHT;

		addUnit(tempId, lane);
	}

	public void addUnit(ID id, int lane) {
		if (id == ID.SOLDIER) {
			if (money >= Cost.getCost(id)) {
				unit.add(new Soldier(id, lane, this));
				money -= Cost.getCost(id);
			}
		} else if (id == ID.ARCHER) {
			if (money >= Cost.getCost(id)) {
				unit.add(new Archer(id, lane, this));
				money -= Cost.getCost(id);
			}
		} else if (id == ID.KNIGHT) {
			if (money >= Cost.getCost(id)) {
				unit.add(new Knight(id, lane, this));
				money -= Cost.getCost(id);
			}
		}
	}

	public void tick() {
		System.out.println(hp);
		money += 0.5;
		if ((int)money > 700)
			money = 700;
		
		setLane(lane);

		for (int i = 0; i < unit.size(); i++) {
			unit.get(i).tick();
		}
	}

	public void render(Graphics g) {
		for (int i = 0; i < unit.size(); i++) {
			unit.get(i).render(g);
		}

		if (id == ID.LEFTPLAYER) {
			g.setColor(Color.red);
			g.fillRect(150, 90, 200, 50);
			g.setColor(Color.green);
			g.fillRect(150, 90, hp/10*2, 50);
			
			g.setColor(Color.black);
			g.drawString((int) money + "", 35, 122);
			g.drawImage(arrow, 50, y, null);
			g.setColor(Color.orange);
			g.fillArc(activeSpawn * 70 - 55, 25, 30, 30, 0, 360);
		} else if (id == ID.RIGHTPLAYER) {
			g.setColor(Color.red);
			g.fillRect(650, 90, 200, 50);
			g.setColor(Color.green);
			g.fillRect(650, 90, hp/10*2, 50);
			
			g.setColor(Color.black);
			g.drawString((int) money + "", 880, 122);
			g.drawImage(arrow, 940, y, null);
			g.setColor(Color.orange);
			g.fillArc(activeSpawn * 70 + 740, 25, 30, 30, 0, 360);
		}
	}
	
	public Rectangle getBounds(){
		if (id == ID.LEFTPLAYER)
			return new Rectangle(0,0,1,Game.HEIGHT);
		else if (id == ID.RIGHTPLAYER)
			return new Rectangle(Game.WIDTH-1,0,1,Game.HEIGHT);
		
		System.out.println("Can't retrieve player bounds");
		return null;
	}

	public void setLane(int lane) {
		if (lane >= 1 && lane <= 5) {
			this.lane = lane;
			y = lane * 64 + (lane * 40) + 90;
		}
	}

	public int getLane() {
		return lane;
	}

	public void setActiveSpawn(int activeSpawn) {
		if (activeSpawn >= 1 && activeSpawn <= 3)
			this.activeSpawn = activeSpawn;
	}

	public int getActiveSpawn() {
		return activeSpawn;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getMoney() {
		return (int) money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
}
