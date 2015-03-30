package com.jflusin.starshipbattle.backend.game.entities.textured.bonus.impl;

import com.jflusin.starshipbattle.backend.engine.views.AbstractScene;
import com.jflusin.starshipbattle.backend.game.entities.textured.bonus.BonusEntity;
import com.jflusin.starshipbattle.backend.game.enums.BonusSpawn;
import com.jflusin.starshipbattle.backend.game.enums.BonusType;

public class TeamShieldBonusEntity extends BonusEntity {

	public static final int DURATION = 1000;
	
	public TeamShieldBonusEntity(AbstractScene scene, BonusSpawn spawn) {
		super(scene, spawn, "res/bonus-shield.png", true);
	}

	@Override
	public BonusType getType() {
		return BonusType.TEAM_PROTECTION;
	}

}
