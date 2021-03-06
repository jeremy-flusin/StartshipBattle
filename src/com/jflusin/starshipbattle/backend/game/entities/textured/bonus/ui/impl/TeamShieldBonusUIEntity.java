package com.jflusin.starshipbattle.backend.game.entities.textured.bonus.ui.impl;

import com.badlogic.gdx.graphics.Color;
import com.jflusin.starshipbattle.backend.engine.views.AbstractScene;
import com.jflusin.starshipbattle.backend.game.entities.textured.bonus.ui.BonusUIEntity;
import com.jflusin.starshipbattle.backend.game.entities.textured.player.impl.PlayerEntity;

public class TeamShieldBonusUIEntity extends BonusUIEntity {

	public TeamShieldBonusUIEntity(AbstractScene scene, PlayerEntity player) {
		super(scene, player, "res/bonus-shield.png");
		getTexturedSprite().getSprite().setColor(Color.YELLOW);
	}
	
	@Override
	public float getPixelOffset() {
		return 20;
	}
}
