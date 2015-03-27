package com.jflusin.starshipbattle.backend.game.entities.textured.ammo;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.jflusin.starshipbattle.backend.engine.views.AbstractScene;
import com.jflusin.starshipbattle.backend.game.entities.AbstractEntity;
import com.jflusin.starshipbattle.backend.game.entities.textured.AbstractTexturedEntity;
import com.jflusin.starshipbattle.backend.game.interfaces.Fighter;
import com.jflusin.starshipbattle.backend.game.interfaces.IsSolid;
import com.jflusin.starshipbattle.backend.game.utils.AngleUtils;
import com.jflusin.starshipbattle.backend.game.utils.BalancingConstants;

public class AmmoEntity extends AbstractTexturedEntity {

	protected static int INITIAL_POWER = BalancingConstants.MAX_ENERGY_HIT;
	protected int currentPower = INITIAL_POWER;
	
	protected static float VELOCITY = BalancingConstants.ENERGY_VELOCITY;

	private Fighter shooter;
	
	public AmmoEntity(AbstractScene scene, String texturePath, float width, float height, Vector2 position, Vector2 target, Fighter shooter) {
		super(scene, texturePath, position, width, height, true);
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
		getTexturedSprite().getSprite().setAlpha((float)currentPower / (float)INITIAL_POWER);
		
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
		if(other instanceof IsSolid){
			if(!shooter.equals(other) && !(other instanceof AmmoEntity)){
				destroy();
			}
		}
	}

	public Fighter getShooter() {
		return shooter;
	}
	
	public int getCurrentPower() {
		return currentPower;
	}
}
