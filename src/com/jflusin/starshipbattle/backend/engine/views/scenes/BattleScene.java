package com.jflusin.starshipbattle.backend.engine.views.scenes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import org.apache.commons.collections4.MultiMap;
import org.apache.commons.collections4.map.MultiValueMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.jflusin.starshipbattle.backend.engine.handlers.inputs.DefaultPlayerOneKeyMapping;
import com.jflusin.starshipbattle.backend.engine.handlers.inputs.DefaultPlayerTwoKeyMapping;
import com.jflusin.starshipbattle.backend.engine.main.Game;
import com.jflusin.starshipbattle.backend.engine.utils.EventsHandler;
import com.jflusin.starshipbattle.backend.engine.utils.SceneManager;
import com.jflusin.starshipbattle.backend.engine.views.AbstractScene;
import com.jflusin.starshipbattle.backend.game.entities.rendered.AbstractRenderedEntity;
import com.jflusin.starshipbattle.backend.game.entities.textured.AbstractTexturedEntity;
import com.jflusin.starshipbattle.backend.game.entities.textured.asteroid.AsteroidEntity;
import com.jflusin.starshipbattle.backend.game.entities.textured.bonus.impl.LaserBonusEntity;
import com.jflusin.starshipbattle.backend.game.entities.textured.bonus.impl.NexusHealBonusEntity;
import com.jflusin.starshipbattle.backend.game.entities.textured.bonus.impl.TeamShieldBonusEntity;
import com.jflusin.starshipbattle.backend.game.entities.textured.fixed.impl.BackgroundEntity;
import com.jflusin.starshipbattle.backend.game.entities.textured.nexus.NexusEntity;
import com.jflusin.starshipbattle.backend.game.entities.textured.nexus.impl.NexusBlueEntity;
import com.jflusin.starshipbattle.backend.game.entities.textured.nexus.impl.NexusRedEntity;
import com.jflusin.starshipbattle.backend.game.entities.textured.player.impl.PlayerEntity;
import com.jflusin.starshipbattle.backend.game.enums.BonusSpawn;
import com.jflusin.starshipbattle.backend.game.enums.BonusType;
import com.jflusin.starshipbattle.backend.game.enums.Team;
import com.jflusin.starshipbattle.backend.game.utils.Constants;

public class BattleScene extends AbstractScene {
	private PlayerEntity playerBlue;
	private PlayerEntity playerBlue2;
	private PlayerEntity playerRed;
	private NexusEntity nexusBlue;
	private NexusEntity nexusRed;
	private BackgroundEntity background;
	private MultiMap<Team, PlayerEntity> players;
	private BattleSceneWatcher bsw;
	private BitmapFont font;
	private EventsHandler eventHandler;
	
	public BattleScene(SceneManager sm, SceneData sd) {
		super(sm, sd);
	}

	@Override
	public void loadContent() {
		background = new BackgroundEntity(this);
		nexusBlue = new NexusBlueEntity(this);
		nexusRed = new NexusRedEntity(this);
		playerBlue = new PlayerEntity(this, Team.BLUE, new Vector2(300, 500), new DefaultPlayerOneKeyMapping());
		playerBlue2 = new PlayerEntity(this, Team.BLUE, new Vector2(250, 300), new DefaultPlayerOneKeyMapping());
		playerRed = new PlayerEntity(this, Team.RED, new Vector2(1500, 500), new DefaultPlayerTwoKeyMapping());
		players = new MultiValueMap<Team, PlayerEntity>();
		players.put(Team.BLUE, playerBlue);
		players.put(Team.BLUE, playerBlue2);
		players.put(Team.RED, playerRed);
		addTexturedEntity(playerBlue);
		addTexturedEntity(playerBlue2);
		addTexturedEntity(playerRed);
		addTexturedEntity(nexusBlue);
		addTexturedEntity(nexusRed);
		bsw = new BattleSceneWatcher(sm, this);
		font = new BitmapFont();
		font.setScale(1.2f);
		eventHandler = new EventsHandler(tw, this);
	}

	@Override
	public void handleInput() {
		if(playerBlue.getModel().isAlive()){
			playerBlue.handleInput();
		}
		if(playerRed.getModel().isAlive()){
			playerRed.handleInput();
		}
		nexusBlue.handleInput();
		nexusRed.handleInput();
	}

	@Override
	public void render() {
		Gdx.gl20.glClearColor(0, 0, 0, 0);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sb.begin();
		background.getTexturedSprite().getSprite().draw(sb);
		sb.end();
		for (AbstractRenderedEntity entity : renderedEntities) {
			if (entity.isVisible()) {
				entity.render(sr, sb);
			}
		}
		sr.setProjectionMatrix(cam.combined);
		sb.begin();
		for (AbstractTexturedEntity entity : texturedEntities) {
			if (entity.isVisible()) {
				entity.getTexturedSprite().getSprite().draw(sb);
			}
		}
		sb.end();
		sb.setProjectionMatrix(cam.combined);
		if (Game.IS_DEBUG) {
			b2dr.render(world, b2dcam.combined);
		}
		sb.begin();
		font.draw(sb, tw.getMinutesString() + ":" + tw.getSecondsString(), Game.V_WIDTH / 2 - 9, Game.V_HEIGHT - 50);
		sb.end();
		messageHandler.render(sb);
	}

	@Override
	public void update(float dt) {
		super.update(dt);
		bsw.watch();
		tw.watch();
		eventHandler.watch();
		if (!jukebox.isPlaying()) {
			jukebox.playRandomTrack();
		}
		messageHandler.update();
	}

	@Override
	public void dispose() {
	}

	@SuppressWarnings("unchecked")
	// TODO: is this really necessary ?
	public ArrayList<PlayerEntity> getPlayers(Team team) {
		return new ArrayList<PlayerEntity>((Collection<PlayerEntity>) players.get(team));
	}

	public NexusEntity getNexusBlue() {
		return nexusBlue;
	}

	public NexusEntity getNexusRed() {
		return nexusRed;
	}
	
	public void eventAsteroids(){
		addTexturedEntity(new AsteroidEntity(this));
		messageHandler.shout(Constants.ASTEROIDS_INCOMMING);
	}

	public void eventBonus() {
		Random r = new Random();
		BonusType type = BonusType.values()[r.nextInt(BonusType.values().length)];
		BonusSpawn spawn = BonusSpawn.values()[r
				.nextInt(BonusSpawn.values().length)];
		if(BonusType.LASER.equals(type)){
			addTexturedEntity(new LaserBonusEntity(this, spawn));
		}else if (BonusType.NEXUS_HEAL.equals(type)){
			addTexturedEntity(new NexusHealBonusEntity(this, spawn));
		} else if (BonusType.TEAM_PROTECTION.equals(type)) {
			addTexturedEntity(new TeamShieldBonusEntity(this, spawn));
		}
		messageHandler.shout(Constants.BONUS_APPERANCE);
	}

	public void eventRevivePlayers() {
		for (Object player : players.values()) {
			PlayerEntity playerEntity = (PlayerEntity)player;
			if (!playerEntity.getModel().isAlive()) {
				messageHandler.shout(Constants.REVIVE_EVENT);
				playerEntity.getModel().revive();
			}
		}
	}

}
