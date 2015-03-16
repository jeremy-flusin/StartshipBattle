package com.jflusin.starshipbattle.backend.engine.views;

import static com.jflusin.starshipbattle.backend.engine.utils.B2DVars.PPM;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.jflusin.starshipbattle.backend.engine.handlers.contact.ContactHandler;
import com.jflusin.starshipbattle.backend.engine.main.Game;
import com.jflusin.starshipbattle.backend.engine.utils.ContentManager;
import com.jflusin.starshipbattle.backend.engine.utils.SceneManager;

public abstract class AbstractScene {
	
	protected static int TOTAL_ROOMS = 8;
	
	protected Box2DDebugRenderer b2dr;
	protected OrthographicCamera b2dcam;

	protected ContactHandler contactHandler;
	
	protected SceneManager sm;
	protected Game game;
	protected SpriteBatch sb;
	protected OrthographicCamera cam;
	protected ContentManager cm;
	
	protected World world;

	public AbstractScene(SceneManager sm) {
		this.sm = sm;
		game = sm.getGame();
		sb = game.getSpriteBatch();
		cam = game.getCamera();
		cm = new ContentManager();
		
		// Debug initializations
		b2dr = new Box2DDebugRenderer();
		b2dcam = new OrthographicCamera();
		b2dcam.setToOrtho(false, Game.V_WIDTH / PPM, Game.V_HEIGHT / PPM);

		// Initializations
		contactHandler = new ContactHandler();
		world = new World(new Vector2(0f, 0f), true);
		world.setContactListener(contactHandler);
		loadContent();
	}

	public abstract void loadContent();
	public abstract void handleInput();
	public abstract void render();
	public abstract void dispose();
	public abstract void manageColliders();
	
	public void update(float dt){
		world.step(dt, 6, 2);
		handleInput();
	}
	
	public ContentManager getContent(){
		return cm;
	}
}
