package com.jflusin.starshipbattle.backend.game.entities.textured.fixed.impl;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.jflusin.starshipbattle.backend.engine.views.AbstractScene;
import com.jflusin.starshipbattle.backend.game.entities.textured.fixed.FixedEntity;

public class BackgroundEntity extends FixedEntity {

	public BackgroundEntity(AbstractScene scene) {
		super(scene, "res/background.jpg", new Vector2(0,0), 1920, 1080);
		getTexturedSprite().getSprite().setColor(new Color(1,1,1,0.6f));
	}

}
