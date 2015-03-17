package com.jflusin.starshipbattle.backend.game.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.jflusin.starshipbattle.backend.engine.views.AbstractScene;
import com.jflusin.starshipbattle.backend.game.utils.AngleUtils;

public class AmmoEntity extends AbstractEntity {

	private static int INITIAL_POWER = 500;
	private int currentPower = INITIAL_POWER;
	
	private static float VELOCITY = 5f;

	private static float WIDTH = 64;
	private static float HEIGHT = 64;
	
	private AbstractEntity shooter;
	
	public AmmoEntity(AbstractScene scene, String texturePath, Vector2 position, Vector2 target, AbstractEntity shooter) {
		super(scene, texturePath, position, WIDTH, HEIGHT, true);
		setAngle(AngleUtils.getRadAngle(position, target));
		setX(position.x);
		setY(position.y);
		this.shooter = shooter;
	}
	
	@Override
	public void handleInput() {

	}
	
	@Override
	public void update(float dt) {
		super.update(dt);
		float scaleX = (float)Math.cos(getAngle());
		float scaleY = (float)Math.sin(getAngle());
		float velX = scaleX * VELOCITY;
		float velY = scaleY * VELOCITY;

		setX(getX() + velX);
		setY(getY() + velY);
		currentPower--;
		getSprite().setAlpha((float)currentPower / (float)INITIAL_POWER);
		
		if(currentPower <= 0){
			destroy();
		}
	}

	@Override
	public Body createBody() {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.x = this.position.x;
		bodyDef.position.y = this.position.y;
		Body body = scene.getWorld().createBody(bodyDef);
		CircleShape circle = new CircleShape();
		circle.setRadius(0.25f);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = circle;
		body.createFixture(fixtureDef);
		circle.dispose();
		return body;
	}

	@Override
	public void onContact(AbstractEntity other) {
		
	}

	public AbstractEntity getShooter() {
		return shooter;
	}
	
	public int getCurrentPower() {
		return currentPower;
	}
}
