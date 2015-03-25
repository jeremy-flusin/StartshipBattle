package com.jflusin.starshipbattle.backend.game.entities.textured.bonus.ui.impl;

import com.jflusin.starshipbattle.backend.engine.views.AbstractScene;
import com.jflusin.starshipbattle.backend.game.entities.textured.bonus.ui.BonusUIEntity;
import com.jflusin.starshipbattle.backend.game.entities.textured.player.impl.ShipPlayerEntity;

public class LaserBonusUIEntity extends BonusUIEntity {

	public LaserBonusUIEntity(AbstractScene scene, ShipPlayerEntity player) {
		super(scene, player, "res/bonus-laser.png");
	}
}
