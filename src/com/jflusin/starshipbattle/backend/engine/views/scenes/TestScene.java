package com.jflusin.starshipbattle.backend.engine.views.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.jflusin.starshipbattle.backend.engine.utils.SceneManager;
import com.jflusin.starshipbattle.backend.engine.views.AbstractScene;
import com.jflusin.starshipbattle.backend.game.entities.AmmoEntity;
import com.jflusin.starshipbattle.backend.game.entities.FixedEntity;
import com.jflusin.starshipbattle.backend.game.entities.ShipEntity;

public class TestScene extends AbstractScene {
	
	private FixedEntity background;
	private ShipEntity ship;
	
	public TestScene(SceneManager sm) {
		super(sm);
	}
	
	@Override
	public void loadContent() {
		background = new FixedEntity("res/background.png");
		ship = new ShipEntity("res/starship_right.png");
	}

	@Override
	public void handleInput() {
		ship.handleInput();
	}

	@Override
	public void update(float dt) {
		super.update(dt);
		ship.update();
	}

	@Override
	public void render() {
		Gdx.gl20.glClearColor(0, 0, 0, 0);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sb.begin();
		background.getSprite().draw(sb);
		ship.getSprite().draw(sb);

		for(AmmoEntity ammo: ship.getAmmos()){
			ammo.getSprite().draw(sb);
		}
		
		sb.end();
		sb.setProjectionMatrix(cam.combined);
		b2dr.render(world, b2dcam.combined);
	}

	@Override
	public void dispose() {
		
	}
	
}
