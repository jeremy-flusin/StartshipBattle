package com.jflusin.starshipbattle.backend.game.entities;

import com.badlogic.gdx.math.Vector2;
import com.jflusin.starshipbattle.backend.engine.views.AbstractScene;

public class FireEntity extends AmmoEntity {

	public FireEntity(AbstractScene scene, Vector2 position, Vector2 target, AbstractEntity shooter) {
		super(scene, "res/fire.png", position, target, shooter);
	}

	
}
