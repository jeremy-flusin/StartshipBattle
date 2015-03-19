package com.jflusin.starshipbattle.backend.game.entities.textured.player.impl;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.jflusin.starshipbattle.backend.engine.handlers.inputs.Actions;
import com.jflusin.starshipbattle.backend.engine.handlers.inputs.InputHandler;
import com.jflusin.starshipbattle.backend.engine.handlers.inputs.PlayerKeyMapping;
import com.jflusin.starshipbattle.backend.engine.views.AbstractScene;
import com.jflusin.starshipbattle.backend.game.entities.rendered.info.bars.impl.HPBarEntity;
import com.jflusin.starshipbattle.backend.game.entities.rendered.info.bars.impl.ShieldBarEntity;
import com.jflusin.starshipbattle.backend.game.entities.textured.player.AbstractShipPlayerEntity;
import com.jflusin.starshipbattle.backend.game.enums.ShootTypes;
import com.jflusin.starshipbattle.backend.game.enums.Team;


public class ShipPlayerEntity extends AbstractShipPlayerEntity {

	public static float MAX_VELOCITY = 10f;
	public static float ACCELERATION = 0.8f;
	public static float DECELERATION = 0.5f;
	public static float TURBO = 2f;
	
	private Team team;
	
	private PlayerKeyMapping keys;

	private HPBarEntity hpBar;
	private ShieldBarEntity shieldBar;
	
	public ShipPlayerEntity(AbstractScene scene, Team team,
			Vector2 initPosition, PlayerKeyMapping keys) {
		super(scene, initPosition);
		this.team = team;
		this.keys = keys;
		hpBar = new HPBarEntity(scene, this);
		scene.addRenderedEntity(hpBar);
		shieldBar = new ShieldBarEntity(scene, this);
		scene.addRenderedEntity(shieldBar);
	}

	@Override
	public void handleInput() {
		
		if(InputHandler.isDown(keys.getKeyForAction(Actions.TURBO))){
			getModel().setTurboActivated(true);
		}else{
			getModel().setTurboActivated(false);
		}
		
		if(InputHandler.isDown(keys.getKeyForAction(Actions.SHIELD))){
			getModel().setShieldActivated(true);
		}else{
			getModel().setShieldActivated(false);
		}
		
		float accelerationX = getAccelerationX();
		float accelerationY = getAccelerationY();
		if(InputHandler.isDown(keys.getKeyForAction(Actions.UP))){
			accelerationY += ACCELERATION;
			if(accelerationY >= MAX_VELOCITY){
				accelerationY =  MAX_VELOCITY;
			}
		}else if(InputHandler.isDown(keys.getKeyForAction(Actions.DOWN))){
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
		setY(getY() + (getModel().isTurboActivated() && getModel().getCurrentTurboLevel() > 0? 
				accelerationY * getModel().getTurboCoeff() : accelerationY)); 
		
		
		if(InputHandler.isDown(keys.getKeyForAction(Actions.RIGHT))){
			accelerationX += ACCELERATION;
			if(accelerationX >= MAX_VELOCITY){
				accelerationX = MAX_VELOCITY;
			}
		}else if(InputHandler.isDown(keys.getKeyForAction(Actions.LEFT))){
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
		setX(getX() + (getModel().isTurboActivated() && getModel().getCurrentTurboLevel() > 0? 
				accelerationX * getModel().getTurboCoeff() : accelerationX)); 
		
		if(InputHandler.isClicked(Input.Buttons.LEFT)){
			shoot(ShootTypes.PRIMARY, new Vector2(InputHandler.mouseX, InputHandler.mouseY));
		};
		if(InputHandler.isClicked(Input.Buttons.RIGHT)){
			shoot(ShootTypes.SECONDARY, new Vector2(InputHandler.mouseX, InputHandler.mouseY));
		};
	}
	
	@Override
	public void update(float dt) {
		super.update(dt);
		if(Team.BLUE.equals(team)){
			getTexturedSprite().getSprite().setColor(Color.CYAN);
		}else if (Team.RED.equals(team)){
			getTexturedSprite().getSprite().setColor(Color.RED);
		}
	}
	
	@Override
	public void destroy() {
		super.destroy();
		hpBar.destroy();
		shieldBar.destroy();
	}
	
	@Override
	public Team getTeam() {
		return team;
	}
	
}
