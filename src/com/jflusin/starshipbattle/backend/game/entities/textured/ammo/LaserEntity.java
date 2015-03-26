package com.jflusin.starshipbattle.backend.game.entities.textured.ammo;

import java.util.ArrayList;

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
import com.jflusin.starshipbattle.backend.game.interfaces.Fighter;
import com.jflusin.starshipbattle.backend.game.interfaces.FighterModel;
import com.jflusin.starshipbattle.backend.game.models.AbstractModel;

public class LaserEntity extends AbstractTexturedEntity {

	private ArrayList<Fighter> touchedEntities;
	private ShipEntity shooter;
	private int duration = 200;
	
	public LaserEntity(AbstractScene scene, Vector2 initPosition, ShipEntity shooter) {
		super(scene, "res/laser.png", initPosition, Game.V_WIDTH, 50, true);
		this.shooter = shooter;
		touchedEntities = new ArrayList<Fighter>();
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
		shape.setAsBox(960 / B2DVars.PPM, 37 / B2DVars.PPM);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		body.createFixture(fixtureDef);
		shape.dispose();
		return body;
	}

	@Override
	public void onContact(AbstractEntity other) {
		if(other instanceof Fighter && !shooter.equals(other)){
			touchedEntities.add((Fighter)other);
		}
	}
	
	@Override
	public void endContact(AbstractEntity other) {
		if(other instanceof Fighter){
			touchedEntities.remove((Fighter)other);
		}
	};
	
	@Override
	public void update(float dt) {
		super.update(dt);
		setX(shooter.getX());
		setY(shooter.getY() + 10);
		duration -= 1;
		if(duration <= 0){
			destroy();
		}
		for (Fighter fighter : touchedEntities) {
			AbstractModel fighterModel = fighter.getModel();
			if(fighterModel instanceof FighterModel){
				FighterModel fm = (FighterModel) fighterModel;
				fm.takeDamage(200);
			}
		}
	}

}
