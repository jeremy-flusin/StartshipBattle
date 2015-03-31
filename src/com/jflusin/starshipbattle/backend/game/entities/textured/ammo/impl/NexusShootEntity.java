package com.jflusin.starshipbattle.backend.game.entities.textured.ammo.impl;

import com.badlogic.gdx.math.Vector2;
import com.jflusin.starshipbattle.backend.engine.views.AbstractScene;
import com.jflusin.starshipbattle.backend.game.entities.AbstractEntity;
import com.jflusin.starshipbattle.backend.game.entities.textured.ammo.AmmoEntity;
import com.jflusin.starshipbattle.backend.game.entities.textured.nexus.NexusEntity;
import com.jflusin.starshipbattle.backend.game.entities.textured.player.impl.PlayerEntity;
import com.jflusin.starshipbattle.backend.game.enums.Team;
import com.jflusin.starshipbattle.backend.game.utils.AngleUtils;

public class NexusShootEntity extends AmmoEntity {

	private PlayerEntity targetToFollow;
	
	public NexusShootEntity(AbstractScene scene, Vector2 position,
			AbstractEntity target, NexusEntity shooter) {
		super(scene, "res/energy_nexus.png", 60, 60, position, target
				.getPosition(), shooter);
		targetToFollow = (PlayerEntity)target;

		// Désolé pour ce tweaking de fdp
		int xOffset = 0;
		int yOffset = 0;
		if (Team.BLUE.equals(shooter.getTeam())) {
			xOffset = 200;
			yOffset = 300;
		} else if (Team.RED.equals(shooter.getTeam())) {
			xOffset = 25;
			yOffset = 200;
		}

		setX(shooter.getX() + xOffset);
		setY(shooter.getY() + yOffset);
	}

	@Override
	public void update(float dt) {
		if(targetToFollow.getModel().isAlive()){
			setAngle(AngleUtils.getRadAngle(position, targetToFollow.getPosition()));
		}
		super.update(dt);
	}
	
}
