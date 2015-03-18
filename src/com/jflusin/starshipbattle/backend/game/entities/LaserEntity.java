package com.jflusin.starshipbattle.backend.game.entities;

import com.badlogic.gdx.math.Vector2;
import com.jflusin.starshipbattle.backend.engine.views.AbstractScene;
import com.jflusin.starshipbattle.backend.game.interfaces.CanShoot;

public class LaserEntity extends AmmoEntity {

	public LaserEntity(AbstractScene scene, Vector2 position, Vector2 target, CanShoot shooter) {
		super(scene, "res/laser.png", 75, 75, position, target, shooter);
	}

}
