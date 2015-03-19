package com.jflusin.starshipbattle.backend.game.entities.textured.ammo.impl;

import com.badlogic.gdx.math.Vector2;
import com.jflusin.starshipbattle.backend.engine.views.AbstractScene;
import com.jflusin.starshipbattle.backend.game.entities.AbstractEntity;
import com.jflusin.starshipbattle.backend.game.entities.textured.ammo.AmmoEntity;
import com.jflusin.starshipbattle.backend.game.entities.textured.player.impl.ShipPlayerEntity;
import com.jflusin.starshipbattle.backend.game.interfaces.CanShoot;
import com.jflusin.starshipbattle.backend.game.utils.AngleUtils;

public class NexusShootEntity extends AmmoEntity {

	private ShipPlayerEntity targetToFollow;
	
	public NexusShootEntity(AbstractScene scene, Vector2 position, AbstractEntity target, CanShoot shooter) {
		super(scene, "res/laser.png", 60, 60, position, target.getPosition(), shooter);
		targetToFollow = (ShipPlayerEntity)target;
	}

	@Override
	public void update(float dt) {
		if(targetToFollow.getModel().isAlive()){
			setAngle(AngleUtils.getRadAngle(position, targetToFollow.getPosition()));
		}
		super.update(dt);
	}
	
}
