package com.jflusin.starshipbattle.backend.game.entities.rendered.info.bars.impl;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.physics.box2d.Body;
import com.jflusin.starshipbattle.backend.engine.views.AbstractScene;
import com.jflusin.starshipbattle.backend.game.entities.AbstractEntity;
import com.jflusin.starshipbattle.backend.game.entities.rendered.info.bars.BarEntity;
import com.jflusin.starshipbattle.backend.game.entities.textured.player.impl.PlayerEntity;
import com.jflusin.starshipbattle.backend.game.models.impl.ShipModel;


public class ShieldBarEntity extends BarEntity {

	private PlayerEntity player;
	private float value = 1f;
	
	public ShieldBarEntity(AbstractScene scene, PlayerEntity player) {
		super(scene, player.getPosition(), player.getTexturedSprite().getSprite().getWidth(), 2, false);
		this.player = player;
	}

	@Override
	public void render(ShapeRenderer sr, SpriteBatch sb) {
		sr.begin(ShapeType.Filled);
        sr.setColor(Color.CYAN);
        sr.rect(getX(), getY() + 86, width * value, height);
        sr.end();
	}

	@Override
	public void update(float dt) {
		player.getModel();
		value = (float)player.getModel().getCurrentShieldPower() / (float)ShipModel.SHIELD_MAX_POWER;
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
