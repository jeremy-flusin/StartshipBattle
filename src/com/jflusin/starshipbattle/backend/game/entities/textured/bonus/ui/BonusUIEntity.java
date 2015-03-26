package com.jflusin.starshipbattle.backend.game.entities.textured.bonus.ui;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.jflusin.starshipbattle.backend.engine.utils.B2DVars;
import com.jflusin.starshipbattle.backend.engine.views.AbstractScene;
import com.jflusin.starshipbattle.backend.game.entities.AbstractEntity;
import com.jflusin.starshipbattle.backend.game.entities.textured.AbstractTexturedEntity;
import com.jflusin.starshipbattle.backend.game.entities.textured.player.impl.PlayerEntity;

public abstract class BonusUIEntity extends AbstractTexturedEntity {

	protected PlayerEntity player;
	
	public BonusUIEntity(AbstractScene scene, PlayerEntity player, String texturePath) {
		super(scene, texturePath, new Vector2(player.getPosition()), 20, 20, false);
		this.player = player;
		setVisible(false);
	}

	@Override
	public void handleInput() {
	}

	@Override
	public Body createBody() {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.x = this.position.x;
		bodyDef.position.y = this.position.y;
		Body body = scene.getWorld().createBody(bodyDef);
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(10 / B2DVars.PPM, 10 / B2DVars.PPM);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		body.createFixture(fixtureDef);
		shape.dispose();
		return body;
	}

	@Override
	public void update(float dt) {
		setX(player.getX() + getPixelOffset());
		setY(player.getY() + 100);
		super.update(dt);
	}
	
	@Override
	public void onContact(AbstractEntity other) {

	}

	public abstract float getPixelOffset();
}
