package com.jflusin.starshipbattle.backend.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public abstract class AbstractEntity {

	private Vector2 position;
	private Vector2 acceleration;
	
	private Texture texture;
	private Sprite sprite;
	
	public AbstractEntity(String texturePath) {
		position = new Vector2();
		acceleration = new Vector2();
		texture = new Texture(Gdx.files.internal(texturePath));
		sprite = new Sprite(texture);
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
	
	public abstract void handleInput();
	
	public void update(){
		sprite.setPosition(position.x, position.y);
	};
}
