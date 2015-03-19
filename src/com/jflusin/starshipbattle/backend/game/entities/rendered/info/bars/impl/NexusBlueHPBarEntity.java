package com.jflusin.starshipbattle.backend.game.entities.rendered.info.bars.impl;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.physics.box2d.Body;
import com.jflusin.starshipbattle.backend.engine.main.Game;
import com.jflusin.starshipbattle.backend.engine.views.AbstractScene;
import com.jflusin.starshipbattle.backend.game.entities.AbstractEntity;
import com.jflusin.starshipbattle.backend.game.entities.textured.nexus.NexusEntity;


public class NexusBlueHPBarEntity extends NexusHPBarEntity {

	public NexusBlueHPBarEntity(AbstractScene scene, NexusEntity nexus) {
		super(scene, nexus);
	}

	@Override
	public void render(ShapeRenderer sr) {
		//Black background
		sr.begin(ShapeType.Filled);
        sr.setColor(Color.BLACK);
        sr.rect(getX(), getY(), width, height);
        sr.end();
        //Hp bar
		sr.begin(ShapeType.Filled);
        sr.setColor(new Color(0,0.6f,0.6f,1));
        sr.rect(getX(), getY(), width * getValue(), height);
        sr.end();
        //White delimitator
        sr.begin(ShapeType.Filled);
        sr.setColor(Color.WHITE);
        sr.rect(Game.V_WIDTH / 2 - 2, Game.V_HEIGHT - height, 2, height);
        sr.end();
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
