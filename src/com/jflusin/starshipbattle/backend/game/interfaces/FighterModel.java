package com.jflusin.starshipbattle.backend.game.interfaces;

public interface FighterModel {

	public void takeDamage(int damage);
	public boolean isAlive();
	public void heal(int heal);
	
}
