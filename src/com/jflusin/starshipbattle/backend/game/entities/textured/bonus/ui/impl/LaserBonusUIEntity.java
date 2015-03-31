package com.jflusin.starshipbattle.backend.game.entities.textured.bonus.ui.impl;

import com.badlogic.gdx.graphics.Color;
import com.jflusin.starshipbattle.backend.engine.views.AbstractScene;
import com.jflusin.starshipbattle.backend.game.entities.textured.bonus.ui.BonusUIEntity;
import com.jflusin.starshipbattle.backend.game.entities.textured.player.impl.PlayerEntity;

public class LaserBonusUIEntity extends BonusUIEntity {

	public LaserBonusUIEntity(AbstractScene scene, PlayerEntity player) {
		super(scene, player, "res/bonus-laser.png");
		getTexturedSprite().getSprite().setColor(Color.RED);
	}
	
	@Override
	public float getPixelOffset() {
		return 0;
	}
}
