package com.jflusin.starshipbattle.backend.game.models;

public class ShipModel extends AbstractModel {

	protected static int MAX_LIFE = 50;
	protected int currentLife = MAX_LIFE;
	
	protected static int SHIELD_MAX_POWER = 1000;
	protected int currentShieldPower = SHIELD_MAX_POWER;
	protected boolean shieldActivated;	
	
	protected static int TURBO_MAX_LEVEL= 1000;
	protected int currentTurboLevel = TURBO_MAX_LEVEL;
	protected boolean turboActivated;
	protected float turboCoeff = 2f;
	
	public ShipModel() {

	}
	
	public int getCurrentLife() {
		return currentLife;
	}

	public void takeDamage(int damage){
		currentLife -= damage;
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
}
