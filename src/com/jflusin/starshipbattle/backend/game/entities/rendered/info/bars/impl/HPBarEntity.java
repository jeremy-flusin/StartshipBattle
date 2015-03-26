package com.jflusin.starshipbattle.backend.game.entities.rendered.info.bars.impl;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.physics.box2d.Body;
import com.jflusin.starshipbattle.backend.engine.views.AbstractScene;
import com.jflusin.starshipbattle.backend.game.entities.AbstractEntity;
import com.jflusin.starshipbattle.backend.game.entities.rendered.info.bars.BarEntity;
import com.jflusin.starshipbattle.backend.game.entities.textured.player.impl.ShipPlayerEntity;
import com.jflusin.starshipbattle.backend.game.models.impl.ShipModel;


public class HPBarEntity extends BarEntity {

	private ShipPlayerEntity player;
	private float value = 1f;
	
	public HPBarEntity(AbstractScene scene, ShipPlayerEntity player) {
		super(scene, player.getPosition(), player.getTexturedSprite().getSprite().getWidth(), 6, false);
		this.player = player;
	}

	@Override
	public void render(ShapeRenderer sr, SpriteBatch sb) {
		sr.begin(ShapeType.Filled);
        sr.setColor(new Color(0.6f,0,0,1));
        sr.rect(getX(), getY() + 80, width, height);
        sr.end();
		sr.begin(ShapeType.Filled);
        sr.setColor(new Color(0,0.6f,0,1));
        sr.rect(getX(), getY() + 80, width * value, height);
        sr.end();
	}

	@Override
	public void update(float dt) {
		value = (float)player.getModel().getCurrentLife() / (float)ShipModel.MAX_LIFE;
	}
	
	@Override
	public void handleInput() {
		
	}

	@Override
	public Body createBody() {
		return null;
	}

	@Override
	public void onContact(AbstractEntity other) {
		
	}


}
