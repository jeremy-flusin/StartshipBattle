package com.jflusin.starshipbattle.backend.game.models.impl;

import com.jflusin.starshipbattle.backend.game.interfaces.FighterModel;
import com.jflusin.starshipbattle.backend.game.models.AbstractModel;
import com.jflusin.starshipbattle.backend.game.utils.BalancingConstants;

public class ShipModel extends AbstractModel implements FighterModel {

	public static int MAX_LIFE = BalancingConstants.PLAYER_MAX_HP;
	protected int currentLife = MAX_LIFE;
	
	public static int SHIELD_MAX_POWER = BalancingConstants.SHIELD_MAX_DURATION_FRAMES;
	protected int currentShieldPower = SHIELD_MAX_POWER;
	protected boolean shieldActivated;	
	
	protected static int TURBO_MAX_LEVEL = BalancingConstants.TURBO_MAX_DURATION_FRAMES;
	protected int currentTurboLevel = TURBO_MAX_LEVEL;
	protected boolean turboActivated;
	protected float turboCoeff = BalancingConstants.TURBO_COEFF;
	
	protected boolean invicible = false;
	protected static int INVICIBLE_MAX_DURATION = BalancingConstants.INVINCIBLE_BONUS_MAX_DURATION_FRAMES;
	protected int currentInvincibleDuration = 0;
	
	public ShipModel() {

	}
	
	public int getCurrentLife() {
		return currentLife;
	}

	@Override
	public void takeDamage(int damage){
		if(!isInvicible()){
			if(isProtected()){
				damage /= BalancingConstants.SHIELD_COEFF;
			}
			currentLife -= damage;
			if(currentLife < 0){
				currentLife = 0;
			}
		}
	}

	public void setShieldActivated(boolean shield) {
		this.shieldActivated = shield;
	}
	
	public boolean isShieldActivated() {
		return shieldActivated;
	}

	public void updateShield() {
		if(shieldActivated){
			currentShieldPower -= 10;
			if(currentShieldPower <= 0){
				currentShieldPower = 0;
			}
		}else{
			currentShieldPower += 1;
			if(currentShieldPower >= SHIELD_MAX_POWER){
				currentShieldPower = SHIELD_MAX_POWER;
			}

		}
	}
	
	public void updateTurboLevel() {
		if(turboActivated){
			currentTurboLevel -= 10;
			if(currentTurboLevel <= 0){
				currentTurboLevel = 0;
			}
		}else{
			currentTurboLevel += 1;
			if(currentTurboLevel >= TURBO_MAX_LEVEL){
				currentTurboLevel = TURBO_MAX_LEVEL;
			}
		}
	}
	
	public void setTurboActivated(boolean turboActivated) {
		this.turboActivated = turboActivated;
	}
	
	public boolean isTurboActivated() {
		return turboActivated;
	}
	
	public int getCurrentTurboLevel() {
		return currentTurboLevel;
	}
	
	public int getCurrentShieldPower() {
		return currentShieldPower;
	}

	public float getTurboCoeff() {
		return turboCoeff;
	}

	public boolean isAlive() {
		return currentLife > 0;
	}
	
	public void heal(int heal){
		currentLife += heal;
		if(currentLife > MAX_LIFE){
			currentLife = MAX_LIFE;
		}
	}
	
	public boolean isProtected() {
		return isShieldActivated() && getCurrentShieldPower() > 0;
	}

	public void revive() {
		currentLife = MAX_LIFE;
	}
	
	public void setInvicible(boolean invicible) {
		this.invicible = invicible;
		if(invicible){
			currentInvincibleDuration = INVICIBLE_MAX_DURATION;
		}
	}
	
	public boolean isInvicible() {
		return invicible && currentInvincibleDuration > 0;
	}

	public void updateProtection() {
		if(invicible){
			currentInvincibleDuration --;
			if(currentInvincibleDuration < 0){
				currentInvincibleDuration = 0;
			}
		}
	}
}
