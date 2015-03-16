package com.jflusin.starshipbattle.backend.game.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class FireEntity extends AbstractEntity {

	private static float WIDTH = 16;
	private static float HEIGHT = 16;
	
	private float angle = 0;
	private float velocity = 70f;
	
	public FireEntity(World world, Vector2 position, float radAngle) {
		super(world, "res/fire.png", position, WIDTH, HEIGHT);
		this.angle = radAngle;
		setX(position.x);
		setY(position.y);
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

	@Override
	public Body createBody() {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.StaticBody;
		bodyDef.position.x = this.position.x;
		bodyDef.position.y = this.position.y;
		Body body = world.createBody(bodyDef);
		CircleShape circle = new CircleShape();
		circle.setRadius(0.3f);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = circle;
		body.createFixture(fixtureDef);
		circle.dispose();
		return body;
	}
	
}
