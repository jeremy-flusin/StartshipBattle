package com.jflusin.starshipbattle.backend.engine.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.jflusin.starshipbattle.backend.engine.handlers.inputs.InputHandler;
import com.jflusin.starshipbattle.backend.engine.handlers.inputs.InputProcessor;
import com.jflusin.starshipbattle.backend.engine.utils.SceneManager;
import com.jflusin.starshipbattle.backend.engine.views.scenes.SceneData;

public class Game implements ApplicationListener {
	public static final boolean IS_DEBUG = false;
	public static final String TITLE = "Starship Battle";
	public static final String VERSION = "0.0.1-Alpha";
	public static final int V_WIDTH = 1920;
	public static final int V_HEIGHT = 1080;
	public static final int SCALE = 1;
	public static final float STEP = 1 / 60f;
	public static final boolean FULLSCREEN = false;
	public static final boolean VSYNC = true;
	public static boolean PAUSE = false;
	private float accum;
	protected SpriteBatch sb;
	protected ShapeRenderer sr;
	protected OrthographicCamera cam;
	private SceneManager sm;

	@Override
	public void create() {
		// Main camera
		cam = new OrthographicCamera();
		cam.setToOrtho(false, V_WIDTH, V_HEIGHT);
		// Initializations
		sb = new SpriteBatch();
		sr = new ShapeRenderer();
		sm = new SceneManager(this);
		sm.setState(SceneManager.BATTLE_SCENE, new SceneData());
		Gdx.input.setInputProcessor(new InputProcessor());
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void render() {
		accum += Gdx.graphics.getDeltaTime();
		while (accum >= STEP) {
			accum -= STEP;
			sm.update(STEP);
			sm.render();
			InputHandler.update();
		}
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}

	public SpriteBatch getSpriteBatch() {
		return sb;
	}

	public OrthographicCamera getCamera() {
		return cam;
	}

	public ShapeRenderer getShapeRenderer() {
		return sr;
	}
}