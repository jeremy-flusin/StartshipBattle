package com.jflusin.starshipbattle.backend.engine.views.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.jflusin.starshipbattle.backend.engine.main.Game;
import com.jflusin.starshipbattle.backend.engine.utils.SceneManager;
import com.jflusin.starshipbattle.backend.engine.views.AbstractScene;
import com.jflusin.starshipbattle.backend.game.entities.AbstractEntity;
import com.jflusin.starshipbattle.backend.game.entities.DumbIAEntity;
import com.jflusin.starshipbattle.backend.game.entities.FixedEntity;
import com.jflusin.starshipbattle.backend.game.entities.ShipEntity;

public class TestScene extends AbstractScene {
	
	private AbstractEntity player;
	private AbstractEntity ia;
	
	public TestScene(SceneManager sm) {
		super(sm);
	}
	
	@Override
	public void loadContent() {
		addEntity(new FixedEntity(this, "res/background.jpg", new Vector2(0,0), 1920, 1080));
		player = new ShipEntity(this, "res/starship_right.png", new Vector2(0, 100));
		addEntity(player);
		ia = new DumbIAEntity(this, player);
		addEntity(ia);
	}

	@Override
	public void handleInput() {
		player.handleInput();
		ia.handleInput();
	}

	@Override
	public void render() {
		Gdx.gl20.glClearColor(0, 0, 0, 0);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sb.begin();
		
		for (AbstractEntity entity : entities) {
			entity.getSprite().draw(sb);
		}
		
		sb.end();
		sb.setProjectionMatrix(cam.combined);
		if (Game.IS_DEBUG){
			b2dr.render(world, b2dcam.combined);
		}
	}

	@Override
	public void dispose() {
		
	}

}
