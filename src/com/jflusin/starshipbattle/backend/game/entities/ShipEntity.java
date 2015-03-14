package com.jflusin.starshipbattle.backend.game.entities;

import java.util.ArrayList;

import com.badlogic.gdx.Input;
import com.jflusin.starshipbattle.backend.engine.handlers.inputs.InputHandler;
import com.jflusin.starshipbattle.backend.engine.main.Game;

public class ShipEntity extends AbstractEntity {

	public static float MAX_VELOCITY = 10f;
	public static float ACCELERATION = 0.8f;
	public static float DECELERATION = 0.5f;
	ArrayList<AmmoEntity> ammos;
	ArrayList<FireEntity> fires;
	
	public ShipEntity(String texturePath) {
		super(texturePath);
		ammos = new ArrayList<AmmoEntity>();
		fires = new ArrayList<FireEntity>();
	}

	@Override
	public void handleInput() {
		
		float accelerationX = getAccelerationX();
		float accelerationY = getAccelerationY();
		
		if(InputHandler.isDown(Input.Keys.UP)){
			accelerationY += ACCELERATION;
			if(accelerationY >= MAX_VELOCITY){
				accelerationY = MAX_VELOCITY;
			}
		}else if(InputHandler.isDown(Input.Keys.DOWN)){
			accelerationY -= ACCELERATION;
			if(accelerationY <= - MAX_VELOCITY){
				accelerationY = - MAX_VELOCITY;
			}
		}else{
			if(accelerationY > 0){
				accelerationY -= DECELERATION;
				if(accelerationY <= 0){
					accelerationY = 0;
				}
			}if(accelerationY < 0){
				accelerationY += DECELERATION;
				if(accelerationY >= 0){
					accelerationY = 0;
				}
			}
		}
		
		setAccelerationY(accelerationY);
		setY(getY() + accelerationY); 
		
		
		if(InputHandler.isDown(Input.Keys.RIGHT)){
			accelerationX += ACCELERATION;
			if(accelerationX >= MAX_VELOCITY){
				accelerationX = MAX_VELOCITY;
			}
		}else if(InputHandler.isDown(Input.Keys.LEFT)){
			accelerationX -= ACCELERATION;
			if(accelerationX <= - MAX_VELOCITY){
				accelerationX = - MAX_VELOCITY;
			}
		}else{
			if(accelerationX > 0){
				accelerationX -= DECELERATION;
				if(accelerationX <= 0){
					accelerationX = 0;
				}
			}if(accelerationX < 0){
				accelerationX += DECELERATION;
				if(accelerationX >= 0){
					accelerationX = 0;
				}
			}
		}
		
		setAccelerationX(accelerationX);
		setX(getX() + accelerationX); 
		
		if(InputHandler.isClicked(Input.Buttons.LEFT)){
			ammos.add(new AmmoEntity(getX(), getY(), getMouseEntityRadAngle()));
		};
		if(InputHandler.isClicked(Input.Buttons.RIGHT)){
			fires.add(new FireEntity(getX(), getY(), getMouseEntityRadAngle()));
		};
	}
	
	@Override
	public void update() {
		super.update();
		for (AmmoEntity ammoEntity : ammos) {
			ammoEntity.update();
		}
		for (FireEntity fireEntity : fires) {
			fireEntity.update();
		}
		getSprite().setRotation(getMouseEntityDegAngle());
	}

	private float getMouseEntityRadAngle() {
		double angleRad = Math.atan2(InputHandler.mouseY - getY() , InputHandler.mouseX - getX());
		return (float)angleRad;
	}
	
	private float getMouseEntityDegAngle() {
		return (float) Math.toDegrees(getMouseEntityRadAngle());
	}
	
	public ArrayList<AmmoEntity> getAmmos() {
		return ammos;
	}
	
	public ArrayList<FireEntity> getFires() {
		return fires;
	}

	@Override
	public void setX(float x) {
		if(x < 0){
			x = 0;
			setAccelerationX(0);
		}
		if(x > Game.V_WIDTH - 64){
			x = Game.V_WIDTH - 64;
			setAccelerationX(0);
		}
		super.setX(x);
	}
	
	@Override
	public void setY(float y) {
		if(y < 0){
			y = 0;
			setAccelerationY(0);
		}
		if(y > Game.V_HEIGHT - 64){
			y = Game.V_HEIGHT - 64;
			setAccelerationY(0);
		}
		super.setY(y);
	}
}
