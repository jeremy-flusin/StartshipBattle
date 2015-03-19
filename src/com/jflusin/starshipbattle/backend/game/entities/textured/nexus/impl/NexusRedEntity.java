package com.jflusin.starshipbattle.backend.game.entities.textured.nexus.impl;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.jflusin.starshipbattle.backend.engine.main.Game;
import com.jflusin.starshipbattle.backend.engine.utils.B2DVars;
import com.jflusin.starshipbattle.backend.engine.views.scenes.BattleScene;
import com.jflusin.starshipbattle.backend.game.entities.rendered.info.bars.impl.NexusRedHPBarEntity;
import com.jflusin.starshipbattle.backend.game.entities.textured.nexus.NexusEntity;
import com.jflusin.starshipbattle.backend.game.enums.Team;

//FIXME: Dirty hacks because of sprite, should be one class
public class NexusRedEntity extends NexusEntity {

	public static Vector2 position = new Vector2(Game.V_WIDTH - 300, 300);
	
	public NexusRedEntity(BattleScene scene) {
		super(scene, "res/nexus-left.png", position, 300, 600, true);
		getTexturedSprite().getSprite().setPosition(position.x, position.y);
		getTexturedSprite().getSprite().setColor(Color.RED);
		hpBar = new NexusRedHPBarEntity(scene, this);
		scene.addRenderedEntity(hpBar);
	}

	@Override
	public Body createBody() {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.x = NexusBlueEntity.position.x;
		bodyDef.position.y = NexusBlueEntity.position.y;
		Body body = scene.getWorld().createBody(bodyDef);
		CircleShape circle = new CircleShape();
		circle.setRadius(2.5f);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = circle;
		body.createFixture(fixtureDef);
		circle.dispose();
		return body;
	}

	@Override
	public void update(float dt) {
		super.update(dt);
		body.setTransform(new Vector2(((position.x + width / 2) + 120) / B2DVars.PPM, ((position.y + height / 2) - 40)
				/ B2DVars.PPM), (float) Math.PI);
	}

	@Override
	public Team getTeam() {
		return Team.RED;
	}

	@Override
	protected float getMinXToWatch() {
		return  Game.V_WIDTH * 75 / 100;
	}

	@Override
	protected float getMaxXToWatch() {
		return Game.V_WIDTH;
	}
	
}
