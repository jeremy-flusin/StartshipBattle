package com.jflusin.starshipbattle.backend.engine.views;

import static com.jflusin.starshipbattle.backend.engine.utils.B2DVars.PPM;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.jflusin.starshipbattle.backend.engine.handlers.contact.ContactHandler;
import com.jflusin.starshipbattle.backend.engine.handlers.sound.effects.SoundEffectPlayer;
import com.jflusin.starshipbattle.backend.engine.handlers.sound.music.MusicPlayer;
import com.jflusin.starshipbattle.backend.engine.main.Game;
import com.jflusin.starshipbattle.backend.engine.utils.ContentManager;
import com.jflusin.starshipbattle.backend.engine.utils.SceneManager;
import com.jflusin.starshipbattle.backend.engine.utils.TimeWatcher;
import com.jflusin.starshipbattle.backend.engine.utils.UserData;
import com.jflusin.starshipbattle.backend.engine.views.scenes.SceneData;
import com.jflusin.starshipbattle.backend.game.entities.AbstractEntity;
import com.jflusin.starshipbattle.backend.game.entities.rendered.AbstractRenderedEntity;
import com.jflusin.starshipbattle.backend.game.entities.textured.AbstractTexturedEntity;

public abstract class AbstractScene {
	protected static int TOTAL_ROOMS = 8;
	protected Box2DDebugRenderer b2dr;
	protected OrthographicCamera b2dcam;
	protected ContactHandler contactHandler;
	protected SceneManager sm;
	protected Game game;
	protected SpriteBatch sb;
	protected ShapeRenderer sr;
	protected OrthographicCamera cam;
	protected ContentManager cm;
	protected World world;
	protected ArrayList<AbstractTexturedEntity> texturedEntities;
	protected ArrayList<AbstractRenderedEntity> renderedEntities;
	protected ArrayList<AbstractEntity> entities;
	protected AbstractEntity player;
	protected SceneData sd;
	protected TimeWatcher tw;
	protected MusicPlayer jukebox;
	protected SoundEffectPlayer sfx;
	protected MessageHandler messageHandler;

	public AbstractScene(SceneManager sm, SceneData sd) {
		this.sm = sm;
		this.sd = sd;
		game = sm.getGame();
		sb = game.getSpriteBatch();
		sr = game.getShapeRenderer();
		cam = game.getCamera();
		cm = new ContentManager();
		tw = new TimeWatcher();
		jukebox = new MusicPlayer();
		sfx = new SoundEffectPlayer();
		messageHandler = new MessageHandler();
		if (Game.IS_DEBUG) {
			b2dr = new Box2DDebugRenderer();
		}
		b2dcam = new OrthographicCamera();
		b2dcam.setToOrtho(false, Game.V_WIDTH / PPM, Game.V_HEIGHT / PPM);
		// Initializations
		contactHandler = new ContactHandler();
		world = new World(new Vector2(0f, 0f), true);
		world.setContactListener(contactHandler);
		// Keep trace of entities
		texturedEntities = new ArrayList<AbstractTexturedEntity>();
		renderedEntities = new ArrayList<AbstractRenderedEntity>();
		entities = new ArrayList<AbstractEntity>();
		loadContent();
	}

	public abstract void loadContent();

	public abstract void handleInput();

	public abstract void render();

	public abstract void dispose();

	public void update(float dt) {
		if(!Game.PAUSE){
			clearWorld();
			clearScene();
			world.step(dt, 6, 2);
			handleInput();
			for (AbstractEntity entity : entities) {
				entity.update(dt);
			}
		}
	}

	private void clearWorld() {
		Array<Body> bodies = new Array<Body>();
		world.getBodies(bodies);
		for (Body body : bodies) {
			if (UserData.TO_DESTROY.equals(body.getUserData())) {
				body.getFixtureList().clear();
				world.destroyBody(body);
			}
		}
	}

	private void clearScene() {
		ArrayList<AbstractEntity> toDestroy = new ArrayList<AbstractEntity>();
		for (AbstractEntity entity : entities) {
			if (UserData.TO_DESTROY.equals(entity.getUserData())) {
				toDestroy.add(entity);
			}
		}
		for (AbstractEntity entity : toDestroy) {
			removeEntity(entity);
		}
	}

	public ContentManager getContent() {
		return cm;
	}

	public World getWorld() {
		return world;
	}

	public void addTexturedEntity(AbstractTexturedEntity entity) {
		addEntity(entity);
		this.texturedEntities.add(entity);
	}

	public void removeTexturedEntity(AbstractTexturedEntity entity) {
		removeEntity(entity);
		this.texturedEntities.remove(entity);
	}

	public void addRenderedEntity(AbstractRenderedEntity entity) {
		addEntity(entity);
		this.renderedEntities.add(entity);
	}

	public void removeTexturedEntity(AbstractEntity entity) {
		removeEntity(entity);
		this.texturedEntities.remove(entity);
	}

	private void addEntity(AbstractEntity entity) {
		this.entities.add(entity);
		if (entity.isCollidable()) {
			contactHandler.registerEntity(entity);
		}
	}

	private void removeEntity(AbstractEntity entity) {
		this.entities.remove(entity);
		this.renderedEntities.remove(entity);
		this.texturedEntities.remove(entity);
		if (entity.isCollidable()) {
			contactHandler.unregisterEntity(entity);
		}
	}
	
	public SoundEffectPlayer getSFX() {
		return sfx;
	}

	public MessageHandler getMessageHandler() {
		return messageHandler;
	}
}
