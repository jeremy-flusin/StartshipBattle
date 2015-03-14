package com.jflusin.starshipbattle.backend.game.entities;

public class AmmoEntity extends AbstractEntity {

	private float angle = 0;
	private float velocity = 40f;
	
	public AmmoEntity(float x, float y, float radAngle) {
		super("res/ammo.png");
		this.angle = radAngle;
		setX(x);
		setY(y);
	}
	
	@Override
	public void handleInput() {

	}
	
	@Override
	public void update() {
		super.update();
		float scaleX = (float)Math.cos(angle);
		float scaleY = (float)Math.sin(angle);
		float velX = scaleX * velocity;
		float velY = scaleY * velocity;

		setX(getX() + velX);
		setY(getY() + velY);
	}

}
