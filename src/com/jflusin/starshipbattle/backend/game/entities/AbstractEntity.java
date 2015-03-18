package com.jflusin.starshipbattle.backend.game.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.jflusin.starshipbattle.backend.engine.utils.B2DVars;
import com.jflusin.starshipbattle.backend.engine.utils.UserData;
import com.jflusin.starshipbattle.backend.engine.views.AbstractScene;
import com.jflusin.starshipbattle.backend.game.models.AbstractModel;

public abstract class AbstractEntity {
	protected Vector2 position;
	protected Vector2 acceleration;
	protected AbstractScene scene;
	protected Body body;
	protected boolean collidable;
	protected float width;
	protected float height;
	protected float angle;
	protected UserData userData;
	protected AbstractModel model;

	public AbstractEntity(AbstractScene scene, Vector2 initPosition, float width, float height, boolean collidable) {
		this.scene = scene;
		this.width = width;
		this.height = height;
		this.position = initPosition;
		this.acceleration = new Vector2();
		this.body = createBody();
		this.collidable = collidable;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public void setX(float x) {
		position.x = x;
	}

	public void setY(float y) {
		position.y = y;
	}

	public Vector2 getPosition() {
		return position;
	}

	public Vector2 getAcceleration() {
		return acceleration;
	}

	public float getX() {
		return position.x;
	}

	public float getY() {
		return position.y;
	}

	public float getAngle() {
		return angle;
	}

	public void setAngle(float angle) {
		this.angle = angle;
	}

	public void setAcceleration(Vector2 acceleration) {
		this.acceleration = acceleration;
	}

	public void setAccelerationX(float x) {
		this.acceleration.x = x;
	}

	public void setAccelerationY(float y) {
		this.acceleration.y = y;
	}

	public float getAccelerationX() {
		return acceleration.x;
	}

	public float getAccelerationY() {
		return acceleration.y;
	}

	public Body getBody() {
		return body;
	}

	public boolean isCollidable() {
		return collidable;
	}

	public UserData getUserData() {
		return userData;
	}

	public AbstractModel getModel() {
		return model;
	}

	public AbstractScene getScene() {
		return scene;
	}

	public void update(float dt) {
		body.setTransform(new Vector2((position.x + width / 2) / B2DVars.PPM, (position.y + height / 2) / B2DVars.PPM),
				angle);
	};

	public void destroy() {
		body.setUserData(UserData.TO_DESTROY);
		userData = UserData.TO_DESTROY;
	}

	public abstract void handleInput();

	public abstract Body createBody();

	public abstract void onContact(AbstractEntity other);
}