package com.jflusin.starshipbattle.backend.game.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.jflusin.starshipbattle.backend.engine.main.Game;
import com.jflusin.starshipbattle.backend.engine.utils.B2DVars;
import com.jflusin.starshipbattle.backend.engine.views.AbstractScene;

//FIXME: Dirty hacks because of sprite
public class RedNexusEntity extends NexusEntity {

	public static Vector2 position = new Vector2(Game.V_WIDTH - 300, 300);
	
	public RedNexusEntity(AbstractScene scene) {
		super(scene, "res/nexus-left.png", position, 300, 600, true);
		sprite.setPosition(position.x, position.y);
	}

	@Override
	public void handleInput() {
		
	}

	@Override
	public Body createBody() {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.x = BlueNexusEntity.position.x;
		bodyDef.position.y = BlueNexusEntity.position.y;
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
		body.setTransform(new Vector2(
					((position.x + width/2) + 120) / B2DVars.PPM,
					((position.y + height/2) - 40) / B2DVars.PPM), (float)Math.PI);
	}

	@Override
	public void onContact(AbstractEntity other) {
		
	}

}
