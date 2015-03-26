package com.jflusin.starshipbattle.backend.game.entities.rendered.info.bars.impl;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.jflusin.starshipbattle.backend.engine.main.Game;
import com.jflusin.starshipbattle.backend.engine.views.AbstractScene;
import com.jflusin.starshipbattle.backend.game.entities.AbstractEntity;
import com.jflusin.starshipbattle.backend.game.entities.rendered.info.bars.BarEntity;
import com.jflusin.starshipbattle.backend.game.entities.textured.nexus.NexusEntity;
import com.jflusin.starshipbattle.backend.game.models.impl.NexusModel;


public abstract class NexusHPBarEntity extends BarEntity {

	protected NexusEntity nexus;
	private float value = 1f;
	protected BitmapFont font;
	
	public NexusHPBarEntity(AbstractScene scene, NexusEntity nexus) {
		super(scene, new Vector2(0,Game.V_HEIGHT - 30), Game.V_WIDTH/2, 30, false);
		this.nexus = nexus;
		font = new BitmapFont();
		font.setScale(1.2f);
	}

	
	@Override
	public void update(float dt) {
		value = (float)nexus.getModel().getCurrentHP() / (float)NexusModel.MAX_HP;
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

	public float getValue() {
		return value;
	}
	
}
