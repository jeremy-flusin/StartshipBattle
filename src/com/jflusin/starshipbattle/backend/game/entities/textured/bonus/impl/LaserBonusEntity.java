package com.jflusin.starshipbattle.backend.game.entities.textured.bonus.impl;

import com.jflusin.starshipbattle.backend.engine.views.AbstractScene;
import com.jflusin.starshipbattle.backend.game.entities.textured.bonus.BonusEntity;
import com.jflusin.starshipbattle.backend.game.enums.BonusSpawn;
import com.jflusin.starshipbattle.backend.game.enums.BonusType;

public class LaserBonusEntity extends BonusEntity {

	public LaserBonusEntity(AbstractScene scene, BonusSpawn spawn) {
		super(scene, spawn, "res/bonus-laser.png", true);
	}

	@Override
	public BonusType getType() {
		return BonusType.LASER;
	}

}
