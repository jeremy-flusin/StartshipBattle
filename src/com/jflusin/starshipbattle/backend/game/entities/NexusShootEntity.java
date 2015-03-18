package com.jflusin.starshipbattle.backend.game.entities;

import com.badlogic.gdx.math.Vector2;
import com.jflusin.starshipbattle.backend.engine.views.AbstractScene;
import com.jflusin.starshipbattle.backend.game.interfaces.CanShoot;
import com.jflusin.starshipbattle.backend.game.utils.AngleUtils;

public class NexusShootEntity extends AmmoEntity {

	private PlayerEntity targetToFollow;
	
	public NexusShootEntity(AbstractScene scene, Vector2 position, AbstractEntity target, CanShoot shooter) {
		super(scene, "res/laser.png", 60, 60, position, target.position, shooter);
		targetToFollow = (PlayerEntity)target;
	}

	@Override
	public void update(float dt) {
		if(targetToFollow.getModel().isAlive()){
			setAngle(AngleUtils.getRadAngle(position, targetToFollow.position));
		}
		super.update(dt);
	}
	
}
