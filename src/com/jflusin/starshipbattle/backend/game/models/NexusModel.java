package com.jflusin.starshipbattle.backend.game.models;

public class NexusModel extends AbstractModel{

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
}
