package com.jflusin.starshipbattle.backend.engine.views.scenes;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.collections4.MultiMap;
import org.apache.commons.collections4.map.MultiValueMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.jflusin.starshipbattle.backend.engine.handlers.inputs.DefaultPlayerOneKeyMapping;
import com.jflusin.starshipbattle.backend.engine.handlers.inputs.DefaultPlayerTwoKeyMapping;
import com.jflusin.starshipbattle.backend.engine.main.Game;
import com.jflusin.starshipbattle.backend.engine.utils.SceneManager;
import com.jflusin.starshipbattle.backend.engine.views.AbstractScene;
import com.jflusin.starshipbattle.backend.game.entities.AbstractEntity;
import com.jflusin.starshipbattle.backend.game.entities.BackgroundEntity;
import com.jflusin.starshipbattle.backend.game.entities.BlueNexusEntity;
import com.jflusin.starshipbattle.backend.game.entities.NexusEntity;
import com.jflusin.starshipbattle.backend.game.entities.PlayerEntity;
import com.jflusin.starshipbattle.backend.game.entities.RedNexusEntity;
import com.jflusin.starshipbattle.backend.game.enums.Team;

public class BattleScene extends AbstractScene {

	private PlayerEntity playerBlue;
	private PlayerEntity playerRed;
	private NexusEntity nexusRed;
	private NexusEntity nexusBlue;
	
	private MultiMap<Team, PlayerEntity> players;

	public BattleScene(SceneManager sm) {
		super(sm);
	}

	@Override
	public void loadContent() {
		addEntity(new BackgroundEntity(this));
//		nexusBlue = new BlueNexusEntity(this);
		nexusRed = new RedNexusEntity(this);
		playerBlue = new PlayerEntity(this, Team.BLUE, new Vector2(300, 500), new DefaultPlayerOneKeyMapping());
		playerRed = new PlayerEntity(this, Team.RED, new Vector2(1500, 500), new DefaultPlayerTwoKeyMapping());
		players = new MultiValueMap<Team, PlayerEntity>();
		players.put(Team.BLUE, playerBlue);
		players.put(Team.RED, playerRed);
		addEntity(playerBlue);
		addEntity(playerRed);
//		addEntity(nexusBlue);
		addEntity(nexusRed);
	}

	@Override
	public void handleInput() {
		playerBlue.handleInput();
		playerRed.handleInput();
//		nexusBlue.handleInput();
		nexusRed.handleInput();
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
		if (Game.IS_DEBUG) {
			b2dr.render(world, b2dcam.combined);
		}
	}

	@Override
	public void dispose() {

	}

	@SuppressWarnings("unchecked")
	//TODO: is this really necessary ?
	public ArrayList<PlayerEntity> getPlayers(Team team) {
		return new ArrayList<PlayerEntity>((Collection<PlayerEntity>) players.get(team));
	}

}
