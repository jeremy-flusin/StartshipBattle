package com.jflusin.starshipbattle.backend.game.entities.textured.ammo.impl;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.jflusin.starshipbattle.backend.engine.views.AbstractScene;
import com.jflusin.starshipbattle.backend.game.entities.textured.ammo.AmmoEntity;
import com.jflusin.starshipbattle.backend.game.entities.textured.ship.ShipEntity;

public class EnergyEntity extends AmmoEntity {

	public EnergyEntity(AbstractScene scene, Vector2 position, Vector2 target,
			ShipEntity shooter) {
		super(scene, "res/energy.png", 30, 15, position, target, shooter);
		setX(shooter.getX() + ShipEntity.WIDTH / 2);
		setY(shooter.getY() + ShipEntity.HEIGHT / 2);
	}

	@Override
	public Body createBody() {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.x = this.position.x;
		bodyDef.position.y = this.position.y;
		Body body = scene.getWorld().createBody(bodyDef);
		CircleShape circle = new CircleShape();
		circle.setRadius(0.10f);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = circle;
		body.createFixture(fixtureDef);
		circle.dispose();
		return body;
	}

}
