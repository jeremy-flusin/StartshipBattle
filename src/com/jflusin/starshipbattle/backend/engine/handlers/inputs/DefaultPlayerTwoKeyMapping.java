package com.jflusin.starshipbattle.backend.engine.handlers.inputs;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;

public class DefaultPlayerTwoKeyMapping extends PlayerKeyMapping {
	
	public DefaultPlayerTwoKeyMapping() {
		super();
		map.put(Actions.UP, Keys.UP);
		map.put(Actions.DOWN, Keys.DOWN);
		map.put(Actions.LEFT, Keys.LEFT);
		map.put(Actions.RIGHT, Keys.RIGHT);
		map.put(Actions.SHOOT_PRIMARY, Buttons.MIDDLE);
		map.put(Actions.SHOOT_SECONDARY, Buttons.MIDDLE);
		map.put(Actions.SHIELD, Keys.CONTROL_RIGHT);
		map.put(Actions.TURBO, Keys.SHIFT_RIGHT);
		map.put(Actions.PROTECT, Keys.P);
	}
	
}
