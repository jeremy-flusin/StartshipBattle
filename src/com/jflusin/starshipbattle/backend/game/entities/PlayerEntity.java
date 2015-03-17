package com.jflusin.starshipbattle.backend.game.entities;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.jflusin.starshipbattle.backend.engine.handlers.inputs.InputHandler;
import com.jflusin.starshipbattle.backend.engine.views.AbstractScene;
import com.jflusin.starshipbattle.backend.game.enums.Team;


public class PlayerEntity extends ShipEntity {

	public static float MAX_VELOCITY = 10f;
	public static float ACCELERATION = 0.8f;
	public static float DECELERATION = 0.5f;
	
	private Team team;
	
	public PlayerEntity(AbstractScene scene, Team team,
			Vector2 initPosition) {
		super(scene, initPosition);
	}

	@Override
	public void handleInput() {
		float accelerationX = getAccelerationX();
		float accelerationY = getAccelerationY();
		
		if(InputHandler.isDown(Input.Keys.UP)){
			accelerationY += ACCELERATION;
			if(accelerationY >= MAX_VELOCITY){
				accelerationY =  MAX_VELOCITY;
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
			scene.addEntity(new LaserEntity(scene, 
					new Vector2(this.position.x, this.position.y), 
					new Vector2(InputHandler.mouseX, InputHandler.mouseY), this));
		};
		if(InputHandler.isClicked(Input.Buttons.RIGHT)){
			scene.addEntity(new FireEntity(scene, 
					new Vector2(this.position.x, this.position.y), 
					new Vector2(InputHandler.mouseX, InputHandler.mouseY), this));
		};
	}

	public Team getTeam() {
		return team;
	}
	
}
