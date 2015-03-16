package com.jflusin.starshipbattle.backend.engine.views.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.jflusin.starshipbattle.backend.engine.main.Game;
import com.jflusin.starshipbattle.backend.engine.utils.SceneManager;
import com.jflusin.starshipbattle.backend.engine.views.AbstractScene;
import com.jflusin.starshipbattle.backend.game.entities.AmmoEntity;
import com.jflusin.starshipbattle.backend.game.entities.FireEntity;
import com.jflusin.starshipbattle.backend.game.entities.FixedEntity;
import com.jflusin.starshipbattle.backend.game.entities.ShipEntity;

public class TestScene extends AbstractScene {
	
	private FixedEntity background;
	private ShipEntity ship;
	private ShipEntity enemy;
	
	public TestScene(SceneManager sm) {
		super(sm);
	}
	
	@Override
	public void loadContent() {
		background = new FixedEntity(world, "res/background.jpg", new Vector2(0,0), 1920, 1080);
		ship = new ShipEntity(world, "res/starship_right.png", new Vector2(0, 100));
		enemy = new ShipEntity(world, "res/enemy.png", 
				new Vector2((float)Math.random() * 1000, ((float)Math.random() * 1000)));
	}

	@Override
	public void handleInput() {
		ship.handleInput();
	}

	@Override
	public void update(float dt) {
		super.update(dt);
		ship.update();
		enemy.update();
	}

	@Override
	public void render() {
		Gdx.gl20.glClearColor(0, 0, 0, 0);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sb.begin();
		sb.draw(background.getSprite().getTexture(), 0, 0, Game.V_WIDTH, Game.V_HEIGHT);
		ship.getSprite().draw(sb);
		enemy.getSprite().draw(sb);
		for(AmmoEntity ammo: ship.getAmmos()){
			ammo.getSprite().draw(sb);
		}
		
		for(FireEntity fire: ship.getFires()){
			fire.getSprite().draw(sb);
		}
		
		sb.end();
		sb.setProjectionMatrix(cam.combined);
		b2dr.render(world, b2dcam.combined);
	}

	@Override
	public void dispose() {
		
	}

	@Override
	public void manageColliders() {
		contactHandler.registerEntity(ship);
		contactHandler.registerEntity(enemy);
	}
}
