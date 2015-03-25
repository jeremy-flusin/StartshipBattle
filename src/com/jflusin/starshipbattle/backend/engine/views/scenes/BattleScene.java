package com.jflusin.starshipbattle.backend.engine.views.scenes;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.collections4.MultiMap;
import org.apache.commons.collections4.map.MultiValueMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.jflusin.starshipbattle.backend.engine.handlers.inputs.DefaultPlayerOneKeyMapping;
import com.jflusin.starshipbattle.backend.engine.handlers.inputs.DefaultPlayerTwoKeyMapping;
import com.jflusin.starshipbattle.backend.engine.main.Game;
import com.jflusin.starshipbattle.backend.engine.utils.SceneManager;
import com.jflusin.starshipbattle.backend.engine.views.AbstractScene;
import com.jflusin.starshipbattle.backend.game.entities.rendered.AbstractRenderedEntity;
import com.jflusin.starshipbattle.backend.game.entities.textured.AbstractTexturedEntity;
import com.jflusin.starshipbattle.backend.game.entities.textured.fixed.impl.BackgroundEntity;
import com.jflusin.starshipbattle.backend.game.entities.textured.nexus.NexusEntity;
import com.jflusin.starshipbattle.backend.game.entities.textured.nexus.impl.NexusBlueEntity;
import com.jflusin.starshipbattle.backend.game.entities.textured.nexus.impl.NexusRedEntity;
import com.jflusin.starshipbattle.backend.game.entities.textured.player.impl.ShipPlayerEntity;
import com.jflusin.starshipbattle.backend.game.enums.Team;

public class BattleScene extends AbstractScene {
	private ShipPlayerEntity playerBlue;
	private ShipPlayerEntity playerBlue2;
	private ShipPlayerEntity playerRed;
	private NexusEntity nexusBlue;
	private NexusEntity nexusRed;
	private BackgroundEntity background;
	private MultiMap<Team, ShipPlayerEntity> players;
	private BattleSceneWatcher bsw;
	private BitmapFont font;

	public BattleScene(SceneManager sm, SceneData sd) {
		super(sm, sd);
	}

	@Override
	public void loadContent() {
		background = new BackgroundEntity(this);
		nexusBlue = new NexusBlueEntity(this);
		nexusRed = new NexusRedEntity(this);
		playerBlue = new ShipPlayerEntity(this, Team.BLUE, new Vector2(300, 500), new DefaultPlayerOneKeyMapping());
		playerBlue2 = new ShipPlayerEntity(this, Team.BLUE, new Vector2(250, 300), new DefaultPlayerOneKeyMapping());
		playerRed = new ShipPlayerEntity(this, Team.RED, new Vector2(1500, 500), new DefaultPlayerTwoKeyMapping());
		players = new MultiValueMap<Team, ShipPlayerEntity>();
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
	}

	@Override
	public void handleInput() {
		playerBlue.handleInput();
		playerRed.handleInput();
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
			entity.render(sr);
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
		font.draw(sb, tw.getMinutes() + ":" + tw.getSeconds(), Game.V_WIDTH / 2 - 9, Game.V_HEIGHT - 50);
		sb.end();
	}

	@Override
	public void update(float dt) {
		super.update(dt);
		bsw.watch();
		tw.watch();
	}

	@Override
	public void dispose() {
	}

	@SuppressWarnings("unchecked")
	// TODO: is this really necessary ?
	public ArrayList<ShipPlayerEntity> getPlayers(Team team) {
		return new ArrayList<ShipPlayerEntity>((Collection<ShipPlayerEntity>) players.get(team));
	}

	public NexusEntity getNexusBlue() {
		return nexusBlue;
	}

	public NexusEntity getNexusRed() {
		return nexusRed;
	}
}
