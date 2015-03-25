package com.jflusin.starshipbattle.backend.game.entities.textured.ammo.impl;

import com.badlogic.gdx.math.Vector2;
import com.jflusin.starshipbattle.backend.engine.views.AbstractScene;
import com.jflusin.starshipbattle.backend.game.entities.textured.ammo.AmmoEntity;
import com.jflusin.starshipbattle.backend.game.interfaces.Fighter;

public class EnergyEntity extends AmmoEntity {

	public EnergyEntity(AbstractScene scene, Vector2 position, Vector2 target, Fighter shooter) {
		super(scene, "res/energy.png", 75, 75, position, target, shooter);
	}

}
