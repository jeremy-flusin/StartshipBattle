package com.jflusin.starshipbattle.backend.game.entities.textured.ship;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.jflusin.starshipbattle.backend.engine.handlers.inputs.InputHandler;
import com.jflusin.starshipbattle.backend.engine.views.AbstractScene;
import com.jflusin.starshipbattle.backend.game.entities.AbstractEntity;
import com.jflusin.starshipbattle.backend.game.entities.textured.AbstractTexturedEntity;
import com.jflusin.starshipbattle.backend.game.entities.textured.ammo.AmmoEntity;
import com.jflusin.starshipbattle.backend.game.entities.textured.ammo.impl.FireEntity;
import com.jflusin.starshipbattle.backend.game.entities.textured.ammo.impl.LaserEntity;
import com.jflusin.starshipbattle.backend.game.enums.ShootTypes;
import com.jflusin.starshipbattle.backend.game.interfaces.CanShoot;
import com.jflusin.starshipbattle.backend.game.interfaces.IsSolid;
import com.jflusin.starshipbattle.backend.game.models.impl.ShipModel;
import com.jflusin.starshipbattle.backend.game.utils.AngleUtils;

public abstract class ShipEntity extends AbstractTexturedEntity implements IsSolid,
		CanShoot {

	public static float WIDTH = 80;
	public static float HEIGHT = 67;

	public ShipEntity(AbstractScene scene, Vector2 initPosition) {
		super(scene, "res/ship.png", initPosition, WIDTH, HEIGHT, true);
		this.model = new ShipModel();
	}

	@Override
	public void handleInput() {

	}

	@Override
	public void update(float dt) {
		super.update(dt);
		setAngle(AngleUtils.getRadAngle(position,
				InputHandler.getMousePosition()));
		getModel().updateShield();
		getModel().updateTurboLevel();
		if (getModel().isShieldActivated()
				&& getModel().getCurrentShieldPower() > 0) {
			// FIXME: does not work
			getTexturedSprite().getSprite().setColor(Color.MAGENTA);
		} else {
			getTexturedSprite().getSprite().setColor(Color.WHITE);
		}
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
		if (other instanceof AmmoEntity) {
			AmmoEntity ammo = (AmmoEntity) other;
			if (!ammo.getShooter().getTeam().equals(getTeam())) {
				getModel().takeDamage(ammo.getCurrentPower());
				if (getModel().getCurrentLife() <= 0) {
					destroy();
				}
			}
		}
	}

	public ShipModel getModel() {
		return (ShipModel) super.getModel();
	}

	@Override
	public void shoot(ShootTypes type, Vector2 target) {
		if (ShootTypes.PRIMARY.equals(type)) {
			scene.addTexturedEntity(new LaserEntity(scene, new Vector2(this.position.x,
					this.position.y), target, this));
		} else if (ShootTypes.SECONDARY.equals(type)) {
			scene.addTexturedEntity(new FireEntity(scene, new Vector2(this.position.x,
					this.position.y), target, this));
		}
	}
	
	@Override
	public void shootTargetTrack(ShootTypes type, AbstractEntity target) {
		
	}
}
