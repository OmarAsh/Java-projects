package com.battleroyale.support;

public class Cost {
	
	public static final int soldierCost = 100;
	public static final int archerCost = 150;
	public static final int knightCost = 200;
	
	public static int getCost(ID id){
		if (id == ID.SOLDIER)
			return soldierCost;
		else if (id == ID.ARCHER)
			return archerCost;
		else if (id == ID.KNIGHT)
			return knightCost;
		else{
			System.out.println("Error finding cost: Unit not found.");
			return 0;
		}
			
	}
}
