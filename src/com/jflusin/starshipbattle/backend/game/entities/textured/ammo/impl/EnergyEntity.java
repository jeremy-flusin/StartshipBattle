package com.jflusin.starshipbattle.backend.game.entities.textured.ammo.impl;

import com.badlogic.gdx.math.Vector2;
import com.jflusin.starshipbattle.backend.engine.views.AbstractScene;
import com.jflusin.starshipbattle.backend.game.entities.textured.ammo.AmmoEntity;
import com.jflusin.starshipbattle.backend.game.entities.textured.ship.ShipEntity;

public class EnergyEntity extends AmmoEntity {

	public EnergyEntity(AbstractScene scene, Vector2 position, Vector2 target,
			ShipEntity shooter) {
		super(scene, "res/energy.png", 30, 15, position, target, shooter);
		setX(shooter.getX() + ShipEntity.WIDTH / 2);
		setY(shooter.getY() + ShipEntity.HEIGHT / 2);
	}

}
