package com.jflusin.starshipbattle.backend.game.entities;

import com.badlogic.gdx.math.Vector2;
import com.jflusin.starshipbattle.backend.engine.views.AbstractScene;
import com.jflusin.starshipbattle.backend.game.interfaces.CanShoot;
import com.jflusin.starshipbattle.backend.game.utils.AngleUtils;

public class NexusShootEntity extends AmmoEntity {

	private Vector2 targetToFollow;
	
	public NexusShootEntity(AbstractScene scene, Vector2 position, Vector2 target, CanShoot shooter) {
		super(scene, "res/energy.png", 60, 60, position, target, shooter);
		targetToFollow = target;
		setAngle(AngleUtils.getRadAngle(position, targetToFollow));
	}

	@Override
	public void update(float dt) {
		super.update(dt);
	}
	
}
