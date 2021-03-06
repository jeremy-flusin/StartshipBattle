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
import com.jflusin.starshipbattle.backend.game.entities.rendered.info.bars.impl.NexusBlueHPBarEntity;
import com.jflusin.starshipbattle.backend.game.entities.textured.nexus.NexusEntity;
import com.jflusin.starshipbattle.backend.game.enums.Team;

//FIXME: Dirty hacks because of sprite, should be one class
public class NexusBlueEntity extends NexusEntity {

	public static Vector2 position = new Vector2(0, 240);
	
	public NexusBlueEntity(BattleScene scene) {
		super(scene, "res/nexus-right.png", position, 300, 600, true);
		getTexturedSprite().getSprite().setPosition(position.x, position.y);
		getTexturedSprite().getSprite().setColor(Color.CYAN);
		hpBar = new NexusBlueHPBarEntity(scene, this);
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
		body.setTransform(new Vector2(
					(position.x) / B2DVars.PPM,
					((position.y + height/2) + 40) / B2DVars.PPM), angle);
	}
	
	@Override
	public Team getTeam() {
		return Team.BLUE;
	}

	@Override
	protected float getMinXToWatch() {
		return 0;
	}

	@Override
	protected float getMaxXToWatch() {
		return Game.V_WIDTH * 25 / 100;
	}
	
}
