package com.jflusin.starshipbattle.backend.game.entities.textured.player.impl;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.jflusin.starshipbattle.backend.engine.handlers.inputs.Actions;
import com.jflusin.starshipbattle.backend.engine.handlers.inputs.InputHandler;
import com.jflusin.starshipbattle.backend.engine.handlers.inputs.PlayerKeyMapping;
import com.jflusin.starshipbattle.backend.engine.handlers.sound.effects.SoundType;
import com.jflusin.starshipbattle.backend.engine.main.Game;
import com.jflusin.starshipbattle.backend.engine.views.AbstractScene;
import com.jflusin.starshipbattle.backend.game.entities.AbstractEntity;
import com.jflusin.starshipbattle.backend.game.entities.rendered.info.bars.impl.HPBarEntity;
import com.jflusin.starshipbattle.backend.game.entities.rendered.info.bars.impl.ShieldBarEntity;
import com.jflusin.starshipbattle.backend.game.entities.textured.asteroid.AsteroidEntity;
import com.jflusin.starshipbattle.backend.game.entities.textured.bonus.BonusEntity;
import com.jflusin.starshipbattle.backend.game.entities.textured.bonus.impl.LaserBonusEntity;
import com.jflusin.starshipbattle.backend.game.entities.textured.bonus.impl.NexusHealBonusEntity;
import com.jflusin.starshipbattle.backend.game.entities.textured.bonus.impl.TeamShieldBonusEntity;
import com.jflusin.starshipbattle.backend.game.entities.textured.bonus.ui.BonusUIEntity;
import com.jflusin.starshipbattle.backend.game.entities.textured.bonus.ui.impl.LaserBonusUIEntity;
import com.jflusin.starshipbattle.backend.game.entities.textured.bonus.ui.impl.TeamShieldBonusUIEntity;
import com.jflusin.starshipbattle.backend.game.entities.textured.player.AbstractShipPlayerEntity;
import com.jflusin.starshipbattle.backend.game.enums.BonusSpawn;
import com.jflusin.starshipbattle.backend.game.enums.BonusType;
import com.jflusin.starshipbattle.backend.game.enums.ShootTypes;
import com.jflusin.starshipbattle.backend.game.enums.Team;
import com.jflusin.starshipbattle.backend.game.utils.BalancingConstants;


public class PlayerEntity extends AbstractShipPlayerEntity {

	public static float MAX_VELOCITY = BalancingConstants.SHIP_VELOCITY;
	public static float ACCELERATION = 0.8f;
	public static float DECELERATION = 0.5f;
	public static float TURBO = 2f;

	private Map<BonusType, BonusUIEntity> bonus;
	
	private Team team;
	
	private PlayerKeyMapping keys;

	private HPBarEntity hpBar;
	private ShieldBarEntity shieldBar;

	public PlayerEntity(AbstractScene scene, Team team,
			Vector2 initPosition, PlayerKeyMapping keys) {
		super(scene, initPosition);
		this.team = team;
		this.keys = keys;
		hpBar = new HPBarEntity(scene, this);
		scene.addRenderedEntity(hpBar);
		shieldBar = new ShieldBarEntity(scene, this);
		scene.addRenderedEntity(shieldBar);
		
		bonus = new HashMap<BonusType, BonusUIEntity>();
		LaserBonusUIEntity laserBonusEntity = new LaserBonusUIEntity(scene, this);
		scene.addTexturedEntity(laserBonusEntity);
		bonus.put(BonusType.LASER, laserBonusEntity);
		TeamShieldBonusUIEntity teamShieldBonusEntity = new TeamShieldBonusUIEntity(scene, this);
		scene.addTexturedEntity(teamShieldBonusEntity);
		bonus.put(BonusType.TEAM_PROTECTION, teamShieldBonusEntity);
	}

	@Override
	public void handleInput() {
		
		
		//--------TESTS CONTROLS---------------//
		if(InputHandler.isPressed(Input.Keys.K)){
			getScene().addTexturedEntity(
					new LaserBonusEntity(getScene(), BonusSpawn.TOP));
		}
		
		if(InputHandler.isPressed(Input.Keys.L)){
			getScene().addTexturedEntity(
					new NexusHealBonusEntity(getScene(), BonusSpawn.MIDDLE));
		}	
		
		if(InputHandler.isPressed(Input.Keys.M)){
			getScene().addTexturedEntity(
					new TeamShieldBonusEntity(getScene(), BonusSpawn.BOTTOM));
		}
		
		if(InputHandler.isPressed(Input.Keys.J)){
			getScene().addTexturedEntity(new AsteroidEntity(getScene()));
		}
		//--------END TESTS CONTROLS------------//
		
		//Dirty
		if(InputHandler.isDown(Input.Keys.ESCAPE)){
			Game.PAUSE = true;
		}
		
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
			getScene().getSFX().playRandom(SoundType.LASER);
		};
		if(InputHandler.isClicked(Input.Buttons.RIGHT)){
			shoot(ShootTypes.SECONDARY, new Vector2(InputHandler.mouseX, InputHandler.mouseY));
		};
		if(InputHandler.isClicked(Input.Buttons.MIDDLE)){
			if(bonus.get(BonusType.LASER).isVisible()){
				shoot(ShootTypes.UNIQUE, new Vector2(InputHandler.mouseX, InputHandler.mouseY));
				useBonus(BonusType.LASER);
			}
		};
		if(InputHandler.isPressed(keys.getKeyForAction(Actions.PROTECT))){
			if(bonus.get(BonusType.TEAM_PROTECTION).isVisible()){
				protect();
				useBonus(BonusType.TEAM_PROTECTION);
			}
		}
		
	}

	@Override
	public void update(float dt) {
		if(Team.BLUE.equals(team)){
			getTexturedSprite().getSprite().setColor(Color.CYAN);
		}else if (Team.RED.equals(team)){
			getTexturedSprite().getSprite().setColor(Color.RED);
		}
		super.update(dt);
		bonus.get(BonusType.LASER).update(dt);
		bonus.get(BonusType.TEAM_PROTECTION).update(dt);
	}
	
	@Override
	public void destroy() {
		super.destroy();
		hpBar.destroy();
		shieldBar.destroy();
		bonus.get(BonusType.LASER).destroy();
		bonus.get(BonusType.TEAM_PROTECTION).destroy();
	}
	
	@Override
	public void onContact(AbstractEntity other) {
		super.onContact(other);
		if(other instanceof BonusEntity){
			BonusEntity bonus = (BonusEntity) other;

			if(bonus.isPickable()){
				pickBonus(bonus.getType());
			}
		}
	}
	
	@Override
	public void setVisible(boolean isVisible) {
		super.setVisible(isVisible);
		hpBar.setVisible(isVisible);
		shieldBar.setVisible(isVisible);
		if(!isVisible){
			bonus.get(BonusType.LASER).setVisible(false);
			bonus.get(BonusType.TEAM_PROTECTION).setVisible(false);
		}
	}
	
	@Override
	public Team getTeam() {
		return team;
	}
	
	public void pickBonus(BonusType bonusType){
		bonus.get(bonusType).setVisible(true);
	}
	
	public void useBonus(BonusType bonusType){
		bonus.get(bonusType).setVisible(false);
	}
	
}
