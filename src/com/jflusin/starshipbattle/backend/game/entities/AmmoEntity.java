package com.jflusin.starshipbattle.backend.game.entities;

public class AmmoEntity extends AbstractEntity {

	public AmmoEntity(float x, float y) {
		super("res/ammo.png");
		setX(x + 50f);
		setY(y - 10f);
	}
	
	@Override
	public void handleInput() {

	}
	
	@Override
	public void update() {
		super.update();
		setX(getX() + 15f);
	}

}
