package com.jflusin.starshipbattle.backend.engine.handlers.inputs;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;

public class DefaultKeyMapping extends PlayerKeyMapping {
	
	public DefaultKeyMapping() {
		super();
		map.put(Actions.UP, Keys.Z);
		map.put(Actions.DOWN, Keys.S);
		map.put(Actions.LEFT, Keys.Q);
		map.put(Actions.RIGHT, Keys.D);
		map.put(Actions.SHOOT_PRIMARY, Buttons.LEFT);
		map.put(Actions.SHOOT_SECONDARY, Buttons.RIGHT);
		map.put(Actions.SHIELD, Keys.E);
		map.put(Actions.TURBO, Keys.SPACE);
	}
	
}
