package com.jflusin.starshipbattle.backend.game.entities.rendered;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.jflusin.starshipbattle.backend.engine.views.AbstractScene;
import com.jflusin.starshipbattle.backend.game.entities.AbstractEntity;

public abstract class AbstractRenderedEntity extends AbstractEntity {

	public AbstractRenderedEntity(AbstractScene scene, Vector2 initPosition, float width, float height,
			boolean collidable) {
		super(scene, initPosition, width, height, collidable);
	}

	public abstract void render(ShapeRenderer sr, SpriteBatch sb);

}
