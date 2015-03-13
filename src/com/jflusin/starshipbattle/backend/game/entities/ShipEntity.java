package com.jflusin.starshipbattle.backend.game.entities;

import com.badlogic.gdx.Input;
import com.jflusin.starshipbattle.backend.engine.handlers.inputs.InputHandler;

public class ShipEntity extends AbstractEntity {

	public ShipEntity(String texturePath) {
		super(texturePath);
	}

	@Override
	public void handleInput() {
		if(InputHandler.isDown(Input.Keys.UP)){
			System.out.println("UP !");
		}
	}

}
