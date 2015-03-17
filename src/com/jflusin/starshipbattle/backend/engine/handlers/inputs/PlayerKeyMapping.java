package com.jflusin.starshipbattle.backend.engine.handlers.inputs;

import java.util.HashMap;
import java.util.Map;

public class PlayerKeyMapping {

	protected Map<Actions, Integer> map;
	
	public PlayerKeyMapping() {
		map = new HashMap<Actions, Integer>();
	}
	
	public Integer getKeyForAction(Actions action){
		return map.get(action);
	}
}
