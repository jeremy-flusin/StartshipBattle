package com.jflusin.starshipbattle.backend.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.jflusin.starshipbattle.backend.engine.handlers.inputs.InputHandler;

public abstract class AbstractEntity {

	private float x;
	private float y;
	
	private Texture texture;
	private Sprite sprite;
	
	public AbstractEntity(String texturePath) {
		texture = new Texture(Gdx.files.internal(texturePath));
		sprite = new Sprite(texture);
	}
	
	public Sprite getSprite() {
		return sprite;
	}

	public void setPosition(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public void setX(float x){
		this.x = x;
	}
	
	public void setY(float y){
		this.y = y;
	}
	
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
	}

	public abstract void handleInput();
	
	public void update(){
		sprite.setPosition(x, y);
		double angleRad = Math.atan(InputHandler.mouseY / InputHandler.mouseX);
		double angleDeg = Math.toDegrees(angleRad);
		sprite.setRotation((float) angleDeg);
	};
}
