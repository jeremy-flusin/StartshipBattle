package com.jflusin.starshipbattle.backend.game.models.impl;

import com.jflusin.starshipbattle.backend.game.interfaces.FighterModel;
import com.jflusin.starshipbattle.backend.game.models.AbstractModel;

public class NexusModel extends AbstractModel implements FighterModel{

	public static int MAX_HP = 20000;
	protected int currentHP = MAX_HP;
	protected static int MAX_COOLDOWN = 20;
	protected int shootCooldown = MAX_COOLDOWN;
	protected boolean isVulnerable = false;
	
	public NexusModel() {

	}
	
	public void updateCooldown(){
		if(shootCooldown < MAX_COOLDOWN){
			shootCooldown++;
		}
	}
	
	public boolean canShoot(){
		return shootCooldown == MAX_COOLDOWN;
	}
	
	public void setShootCooldown(int shootCooldown) {
		this.shootCooldown = shootCooldown;
	}
	
	@Override
	public void takeDamage(int damage){
		if(isVulnerable){
			currentHP -= damage;
			if(currentHP < 0){
				currentHP = 0;
			}
		}
	}
	
	public int getCurrentHP() {
		return currentHP;
	}

	@Override
	public boolean isAlive() {
		return currentHP > 0;
	}
	
	public void setVulnerable(boolean nexusVulnerable) {
		this.isVulnerable = nexusVulnerable;
	}
	
	public void heal(int heal){
		currentHP += heal;
		if(currentHP > MAX_HP){
			currentHP = MAX_HP;
		}
	}
}
