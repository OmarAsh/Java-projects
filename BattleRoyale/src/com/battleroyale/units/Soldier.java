package com.battleroyale.units;

import java.awt.Color;
import java.awt.Graphics;

import com.battleroyale.support.ID;
import com.battleroyale.support.Player;
import com.battleroyale.support.STANCE;
import com.battleroyale.support.Unit;

public class Soldier extends Unit {

	public Soldier(ID id, int lane, Player player) {
		super(id, lane, player);
		hp = 400;
		dmg = 1;
	}

	public void tick() {
		determineEnemy();
		stance = STANCE.NEUTRAL;
		if (getBounds().intersects(enemy.getBounds())){
			removeUnit(player, this);
			enemy.setHp(enemy.getHp() - 10);
		}
		
		for (int i = 0; i < enemy.unit.size(); i++) {
			Unit tempUnit = enemy.unit.get(i);

			if (getBounds().intersects(tempUnit.getBounds())) {
				speed = 0;
				stance = STANCE.ATTACK;
				randomDmg = (int) (Math.random() * 5 + 1);
				tempUnit.setHp(tempUnit.getHp() - (dmg + randomDmg));
				if (tempUnit.getHp() <= 0){
					removeUnit(enemy, tempUnit);
					moneyForKill(tempUnit);
				}
					
				
			}
		}

		if (hp <= 0)
			removeUnit(player, this);
		if (stance == STANCE.NEUTRAL)
			setDefaultSpeed();

		x += speed * player.dir;
	}

	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, 40, 40);
		g.setColor(Color.white);
		g.drawRect(x, y, 40, 40);
		
		g.setColor(Color.red);
		g.fillRect(x, y - 30, 40, 20);
		g.setColor(Color.green);
		g.fillRect(x, y-30, hp/10, 20);
	}
}
