package com.jflusin.starshipbattle.backend.game.entities.textured.asteroid;

import java.util.Random;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.jflusin.starshipbattle.backend.engine.main.Game;
import com.jflusin.starshipbattle.backend.engine.views.AbstractScene;
import com.jflusin.starshipbattle.backend.game.entities.AbstractEntity;
import com.jflusin.starshipbattle.backend.game.entities.textured.AbstractTexturedEntity;
import com.jflusin.starshipbattle.backend.game.interfaces.IsSolid;
import com.jflusin.starshipbattle.backend.game.utils.AngleUtils;

public class AsteroidEntity extends AbstractTexturedEntity implements IsSolid {

	private boolean up;
	private boolean right;
	private Vector2 dest;
	private float velocity;
	private static float MAX_VELOCITY = 5f;
	private static float MIN_VELOCITY = 1f;
	
	public AsteroidEntity(AbstractScene scene) {
		super(scene, "res/asteroid-1.png", new Vector2(0,0), 190, 190, true);
		Random r = new Random();
		up = r.nextBoolean();
		right = r.nextBoolean();
		
		float x = right ? r.nextFloat() * (Game.V_WIDTH / 2) + (Game.V_WIDTH / 2) 
				: r.nextFloat() * (Game.V_WIDTH / 2);
		float y = up ? Game.V_HEIGHT 
				: -width;
		setX(x);
		setY(y);
		float destX = right ? r.nextFloat() * (Game.V_WIDTH / 2)
				: r.nextFloat() * (Game.V_WIDTH / 2) + (Game.V_WIDTH / 2);
		float destY = up ? -width
				: Game.V_HEIGHT;
		dest = new Vector2(destX, destY);
		setAngle(AngleUtils.getRadAngle(position, dest));
		velocity = r.nextFloat() * MAX_VELOCITY;
		if(velocity < MIN_VELOCITY){
			velocity = MIN_VELOCITY;
		}
		setAcceleration(new Vector2(1,1));
	}

	@Override
	public Body createBody() {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.x = this.position.x;
		bodyDef.position.y = this.position.y;
		Body body = scene.getWorld().createBody(bodyDef);
		CircleShape circle = new CircleShape();
		circle.setRadius(1f);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = circle;
		body.createFixture(fixtureDef);
		circle.dispose();
		return body;
	}

	@Override
	public void onContact(AbstractEntity other) {

	}

	@Override
	public void handleInput() {
		
	}
	
	@Override
	public void update(float dt) {
		super.update(dt);
		float scaleX = (float)Math.cos(getAngle());
		float scaleY = (float)Math.sin(getAngle());
		float accX = getAccelerationX();
		float accY = getAccelerationY();
		float velX = scaleX * velocity * accX;
		float velY = scaleY * velocity * accY;

		setX(getX() + velX);
		setY(getY() + velY);
	}

}
