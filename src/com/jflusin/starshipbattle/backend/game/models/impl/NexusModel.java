package com.jflusin.starshipbattle.backend.game.models.impl;

import com.jflusin.starshipbattle.backend.game.models.AbstractModel;

public class NexusModel extends AbstractModel{

	public static int MAX_HP = 20000;
	protected int currentHP = MAX_HP;
	protected static int MAX_COOLDOWN = 20;
	protected int shootCooldown = MAX_COOLDOWN;
	
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
	
	public void takeDamage(int damage){
		currentHP -= damage;
		if(currentHP < 0){
			currentHP = 0;
		}
	}
	
	public int getCurrentHP() {
		return currentHP;
	}
}
