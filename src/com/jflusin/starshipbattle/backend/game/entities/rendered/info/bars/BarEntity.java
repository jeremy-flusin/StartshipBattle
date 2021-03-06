package com.jflusin.starshipbattle.backend.game.entities.rendered.info.bars;

import com.badlogic.gdx.math.Vector2;
import com.jflusin.starshipbattle.backend.engine.views.AbstractScene;
import com.jflusin.starshipbattle.backend.game.entities.rendered.AbstractRenderedEntity;


public abstract class BarEntity extends AbstractRenderedEntity {

	public BarEntity(AbstractScene scene, Vector2 initPosition, float width, float height, boolean collidable) {
		super(scene, initPosition, width, height, collidable);
	}


}
