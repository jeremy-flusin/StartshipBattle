package com.jflusin.starshipbattle.backend.game.entities;

public class FireEntity extends AbstractEntity {

	private float angle = 0;
	private float velocity = 70f;
	
	public FireEntity(float x, float y, float radAngle) {
		super("res/fire.png");
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
		getSprite().setRotation((float)Math.toDegrees(angle));
		float scaleX = (float)Math.cos(angle);
		float scaleY = (float)Math.sin(angle);
		float velX = scaleX * velocity;
		float velY = scaleY * velocity;

		setX(getX() + velX);
		setY(getY() + velY);
	}

}
