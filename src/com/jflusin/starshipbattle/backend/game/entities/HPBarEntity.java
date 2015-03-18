package com.jflusin.starshipbattle.backend.game.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.physics.box2d.Body;
import com.jflusin.starshipbattle.backend.engine.views.AbstractScene;
import com.jflusin.starshipbattle.backend.game.models.ShipModel;


public class HPBarEntity extends BarEntity {

	private PlayerEntity player;
	private float value = 1f;
	
	public HPBarEntity(AbstractScene scene, PlayerEntity player) {
		super(scene, player.position, 200, 10, false);
		this.player = player;
	}

	@Override
	public void render(ShapeRenderer sr) {
		sr.begin(ShapeType.Filled);
        sr.setColor(Color.RED);
        sr.rect(getX(), getY(), width, height);
        sr.end();
		sr.begin(ShapeType.Filled);
        sr.setColor(Color.GREEN);
        sr.rect(getX(), getY(), width * value, height);
        sr.end();
	}

	@Override
	public void update(float dt) {
		player.getModel();
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
