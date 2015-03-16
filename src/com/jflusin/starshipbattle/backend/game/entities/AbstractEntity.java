package com.jflusin.starshipbattle.backend.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.jflusin.starshipbattle.backend.engine.utils.B2DVars;

public abstract class AbstractEntity {

	protected Vector2 position;
	protected Vector2 acceleration;
	
	protected Texture texture;
	protected Sprite sprite;
	
	protected World world;
	protected Body body;

	protected float width;
	protected float height;
	protected float angle;
	
	public AbstractEntity(World world, String texturePath, 
			Vector2 initPosition, float width, float height){
		this.texture = new Texture(Gdx.files.internal(texturePath));
		this.sprite = new Sprite(texture);
		this.world = world;
		this.width = width;
		this.height = height;
		this.position = initPosition;
		this.acceleration = new Vector2();
		this.body = createBody();
	}
	
	public Sprite getSprite() {
		return sprite;
	}

	public void setPosition(Vector2 position){
		this.position = position;
	}
	
	public void setX(float x){
		position.x = x;
	}
	
	public void setY(float y){
		position.y = y;
	}
	
	public float getX(){
		return position.x;
	}
	
	public float getY(){
		return position.y;
	}

	public float getAngle(){
		return angle;
	}

	public void setAngle(float angle) {
		this.angle = angle;
	}
	
	public void setAcceleration(Vector2 acceleration){
		this.acceleration = acceleration;
	}
	
	public void setAccelerationX(float x){
		this.acceleration.x = x;
	}
	
	public void setAccelerationY(float y){
		this.acceleration.y = y;
	}

	public float getAccelerationX(){
		return acceleration.x;
	}
	
	public float getAccelerationY(){
		return acceleration.y;
	}
	
	public Body getBody() {
		return body;
	}
	
	public void update(){
		sprite.setPosition(position.x, position.y);
		sprite.setRotation((float)Math.toDegrees(angle));
		body.setTransform(new Vector2(
					(position.x + width) / B2DVars.PPM,
					(position.y + height) / B2DVars.PPM), angle);
	};

	public abstract void handleInput();
	public abstract Body createBody();
	
}
