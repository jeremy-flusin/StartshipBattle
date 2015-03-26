package com.jflusin.starshipbattle.backend.game.entities.rendered.info.bars.impl;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.physics.box2d.Body;
import com.jflusin.starshipbattle.backend.engine.main.Game;
import com.jflusin.starshipbattle.backend.engine.views.AbstractScene;
import com.jflusin.starshipbattle.backend.game.entities.AbstractEntity;
import com.jflusin.starshipbattle.backend.game.entities.textured.nexus.NexusEntity;
import com.jflusin.starshipbattle.backend.game.models.impl.NexusModel;


public class NexusRedHPBarEntity extends NexusHPBarEntity {

	public NexusRedHPBarEntity(AbstractScene scene, NexusEntity nexus) {
		super(scene, nexus);
		setX(Game.V_WIDTH /2);
	}

	@Override
	public void render(ShapeRenderer sr, SpriteBatch sb) {
		//Black background
		sr.begin(ShapeType.Filled);
        sr.setColor(Color.BLACK);
        sr.rect(Game.V_WIDTH /2, getY(), width, height);
        sr.end();
        //HpBar
		sr.begin(ShapeType.Filled);
        sr.setColor(new Color(0.6f,0,0,1));
        sr.rect(getX(), getY(), width * getValue(), height);
        sr.end();
        //White delimitator
        sr.begin(ShapeType.Filled);
        sr.setColor(Color.WHITE);
        sr.rect(Game.V_WIDTH / 2, Game.V_HEIGHT - height, 2, height);
        sr.end();
        //Life display
		sb.begin();
		nexus.getModel();
		font.draw(sb, nexus.getModel().getCurrentHP() + "/" + NexusModel.MAX_HP, Game.V_WIDTH - 100, Game.V_HEIGHT - 6);
		sb.end();
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
	
	@Override
	public float getX() {
		return  - (getValue() * Game.V_WIDTH / 2) + Game.V_WIDTH; 
	}

}
