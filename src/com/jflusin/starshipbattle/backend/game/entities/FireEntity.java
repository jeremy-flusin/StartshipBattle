package com.jflusin.starshipbattle.backend.game.entities;

import com.badlogic.gdx.math.Vector2;
import com.jflusin.starshipbattle.backend.engine.views.AbstractScene;
import com.jflusin.starshipbattle.backend.game.interfaces.CanShoot;

public class FireEntity extends AmmoEntity {

	public FireEntity(AbstractScene scene, Vector2 position, Vector2 target, CanShoot shooter) {
		super(scene, "res/fire.png", 64, 64, position, target, shooter);
	}

	
}
