package com.jflusin.starshipbattle.backend.engine.views.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.jflusin.starshipbattle.backend.engine.utils.SceneManager;
import com.jflusin.starshipbattle.backend.engine.views.AbstractScene;

public class EndBattleScene extends AbstractScene {

	private String message;
	private BitmapFont font;
	
	public EndBattleScene(SceneManager sm, SceneData sd) {
		super(sm, sd);
		font = new BitmapFont();
	}

	@Override
	public void loadContent() {
		message = "Winners: " + sd.getData("winningTeam") + " Team";
	}

	@Override
	public void handleInput() {

	}

	@Override
	public void render() {
		Gdx.gl20.glClearColor(0, 0, 0, 0);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sb.begin();
		font.draw(sb, message, 1000, 1000);
		sb.end();
	}

	@Override
	public void dispose() {

	}

}
