package com.jflusin.starshipbattle.backend.game.entities.textured.fixed;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.jflusin.starshipbattle.backend.engine.views.AbstractScene;
import com.jflusin.starshipbattle.backend.game.entities.AbstractEntity;
import com.jflusin.starshipbattle.backend.game.entities.textured.AbstractTexturedEntity;


public class FixedEntity extends AbstractTexturedEntity {

	public FixedEntity(AbstractScene scene, String texturePath, 
			Vector2 initPosition, float width, float height) {
		super(scene, texturePath, initPosition, width, height, true);
	}

	@Override
	public void handleInput() {
		
	}

	@Override
	public void update(float dt) {

	}

	@Override
	public Body createBody() {
		return null;
	}

	@Override
	public void onContact(AbstractEntity other) {
		
	}
	
}
