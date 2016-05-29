package com.battleroyale.units;

import java.awt.Color;
import java.awt.Graphics;

import com.battleroyale.support.ID;
import com.battleroyale.support.Player;
import com.battleroyale.support.STANCE;
import com.battleroyale.support.Unit;

public class Archer extends Unit {

	Color attackMode = new Color(0.8f, 0.3f, 0.6f, 0.5f);

	public Archer(ID id, int lane, Player player) {
		super(id, lane, player);
		hp = 160;
		dmg = 1;
	}

	public void tick() {
		determineEnemy();
		stance = STANCE.NEUTRAL;
		if (getBounds("ranged").intersects(enemy.getBounds())){
			speed = 0;
			stance = STANCE.ATTACK;
			enemy.setHp(enemy.getHp() - 1);
		}

		for (int i = 0; i < enemy.unit.size(); i++) {
			Unit tempUnit = enemy.unit.get(i);

			if (getBounds("ranged").intersects(tempUnit.getBounds())) {
				speed = 0;
				stance = STANCE.ATTACK;
				randomDmg = (int) (Math.random() * 10 + 1);
				tempUnit.setHp(tempUnit.getHp() - (dmg));
				if (tempUnit.getHp() <= 0){
					removeUnit(enemy, tempUnit);
					moneyForKill(tempUnit);
				}
				break;
			}
		}

		if (hp <= 0)
			removeUnit(player, this);
		if (stance == STANCE.NEUTRAL)
			setDefaultSpeed();

		x += speed * player.dir;
	}

	public void render(Graphics g) {
		if (stance == STANCE.ATTACK)
			g.setColor(attackMode);
		else if (stance == STANCE.NEUTRAL)
			g.setColor(color);
		g.fillRect(x, y, 40, 40);
		g.setColor(Color.white);
		g.drawRect(x, y, 40, 40);
		
		g.setColor(Color.red);
		g.fillRect(x, y - 30, 40, 20);
		g.setColor(Color.green);
		g.fillRect(x, y-30, hp/4, 20);
	}
}
