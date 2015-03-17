package com.jflusin.starshipbattle.backend.game.entities;

import com.badlogic.gdx.math.Vector2;
import com.jflusin.starshipbattle.backend.engine.views.AbstractScene;

public abstract class NexusEntity extends AbstractEntity {

	public NexusEntity(AbstractScene scene, String texturePath,
			Vector2 initPosition, float width, float height, boolean collidable) {
		super(scene, texturePath, initPosition, width, height, collidable);
	}
}
