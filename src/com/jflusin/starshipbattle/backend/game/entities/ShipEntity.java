package com.jflusin.starshipbattle.backend.game.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.jflusin.starshipbattle.backend.engine.handlers.inputs.InputHandler;
import com.jflusin.starshipbattle.backend.engine.views.AbstractScene;
import com.jflusin.starshipbattle.backend.game.models.ShipModel;
import com.jflusin.starshipbattle.backend.game.utils.AngleUtils;

public class ShipEntity extends AbstractEntity {
	
	public static float WIDTH = 80;
	public static float HEIGHT = 67;

	public ShipEntity(AbstractScene scene, Vector2 initPosition){
		super(scene, "res/ship.png", initPosition, WIDTH, HEIGHT, true);
		this.model = new ShipModel();
	}
	
	@Override
	public void handleInput() {
		
		
	}
	
	@Override
	public void update(float dt) {
		super.update(dt);
		setAngle(AngleUtils.getRadAngle(position, InputHandler.getMousePosition()));
	}
	
	@Override
	public Body createBody() {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.x = this.position.x;
		bodyDef.position.y = this.position.y;
		Body body = scene.getWorld().createBody(bodyDef);
		CircleShape circle = new CircleShape();
		circle.setRadius(0.4f);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = circle;
		body.createFixture(fixtureDef);
		circle.dispose();
		return body;
	}

	@Override
	public void onContact(AbstractEntity other) {
		if(other instanceof ShipEntity){
			this.acceleration.x = - this.acceleration.x;
			this.acceleration.y = - this.acceleration.y;
		}else if(other instanceof AmmoEntity){
			AmmoEntity ammo = (AmmoEntity) other;
			if(!ammo.getShooter().equals(this)){
				getModel().takeDamage(ammo.getCurrentPower());
			}
		}
	}

	public ShipModel getModel() {
		return (ShipModel)super.getModel();
	}
}
