package com.jflusin.starshipbattle.backend.engine.views.scenes;

import java.util.HashMap;
import java.util.Map;

public class SceneData {

	private Map<String, Object> data;
	
	public SceneData() {
		data = new HashMap<String, Object>();
	}
	
	public void putData (String data, Object value){
		this.data.put(data, value);
	}
	
	public void removeData (Object data){
		this.data.remove(data);
	}
	
	public Object getData (String data){
		return this.data.get(data);
	}
}
