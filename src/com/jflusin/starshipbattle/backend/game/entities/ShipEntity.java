package com.jflusin.starshipbattle.backend.game.entities;

import java.util.ArrayList;

import com.badlogic.gdx.Input;
import com.jflusin.starshipbattle.backend.engine.handlers.inputs.InputHandler;

public class ShipEntity extends AbstractEntity {

	public float velocity = 5f;
	ArrayList<AmmoEntity> ammos;
	
	
	public ShipEntity(String texturePath) {
		super(texturePath);
		ammos = new ArrayList<AmmoEntity>();
	}

	@Override
	public void handleInput() {
		if(InputHandler.isDown(Input.Keys.UP)){
			setY(getY() + velocity); 
		}
		if(InputHandler.isDown(Input.Keys.DOWN)){
			setY(getY() - velocity); 
		}		
		if(InputHandler.isDown(Input.Keys.LEFT)){
			setX(getX() - velocity); 
		}
		if(InputHandler.isDown(Input.Keys.RIGHT)){
			setX(getX() + velocity); 
		}
		if(InputHandler.isClicked(Input.Buttons.LEFT)){
			ammos.add(new AmmoEntity(getX(), getY()));
		};
	}
	
	@Override
	public void update() {
		super.update();
		for (AmmoEntity ammoEntity : ammos) {
			ammoEntity.update();
		}
	}
	
	public ArrayList<AmmoEntity> getAmmos() {
		return ammos;
	}

}
