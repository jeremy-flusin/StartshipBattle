package com.jflusin.starshipbattle.backend.game.entities.textured.player;

import com.badlogic.gdx.math.Vector2;
import com.jflusin.starshipbattle.backend.engine.views.AbstractScene;
import com.jflusin.starshipbattle.backend.game.entities.textured.ship.ShipEntity;

public abstract class AbstractShipPlayerEntity extends ShipEntity {

	public AbstractShipPlayerEntity(AbstractScene scene, Vector2 initPosition) {
		super(scene, initPosition);
	}

}
