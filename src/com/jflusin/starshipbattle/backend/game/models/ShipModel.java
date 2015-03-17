package com.jflusin.starshipbattle.backend.game.models;

public class ShipModel extends AbstractModel {

	public static int MAX_LIFE = 50;
	public int currentLife = MAX_LIFE;
	
	public ShipModel() {

	}
	
	public int getCurrentLife() {
		return currentLife;
	}

	public void takeDamage(int damage){
		currentLife -= damage;
	}
}
