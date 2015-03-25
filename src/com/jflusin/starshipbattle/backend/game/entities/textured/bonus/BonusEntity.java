package com.jflusin.starshipbattle.backend.game.entities.textured.bonus;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.jflusin.starshipbattle.backend.engine.main.Game;
import com.jflusin.starshipbattle.backend.engine.utils.B2DVars;
import com.jflusin.starshipbattle.backend.engine.views.AbstractScene;
import com.jflusin.starshipbattle.backend.game.entities.AbstractEntity;
import com.jflusin.starshipbattle.backend.game.entities.textured.AbstractTexturedEntity;
import com.jflusin.starshipbattle.backend.game.entities.textured.ship.ShipEntity;
import com.jflusin.starshipbattle.backend.game.enums.BonusType;

public abstract class BonusEntity extends AbstractTexturedEntity {

	public BonusEntity(AbstractScene scene, String texturePath) {
		super(scene, texturePath, new Vector2((Game.V_WIDTH / 2) - 20, (Game.V_HEIGHT / 2) - 20), 20, 20, true);
	}

	@Override
	public void handleInput() {
		// TODO Auto-generated method stub
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
	public void onContact(AbstractEntity other) {
		if(other instanceof ShipEntity){
			destroy();
		}
	}

	public abstract BonusType getType();
	
}
