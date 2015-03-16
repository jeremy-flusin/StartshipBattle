package com.jflusin.starshipbattle.backend.game.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;


public class FixedEntity extends AbstractEntity {

	public FixedEntity(World world, String texturePath, 
			Vector2 initPosition, float width, float height) {
		super(world, texturePath, initPosition, width, height);
	}

	@Override
	public void handleInput() {
		
	}

	@Override
	public void update() {
		super.update();
	}

	@Override
	public Body createBody() {
		return null;
	}

	@Override
	public void onContact(AbstractEntity other) {
		
	}
	
}
