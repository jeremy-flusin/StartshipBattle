package com.jflusin.starshipbattle.backend.engine.utils;

import com.jflusin.starshipbattle.backend.engine.views.scenes.BattleScene;

public class EventsHandler {

	private TimeWatcher tw;
	private BattleScene scene;
	private int lastSeconds = Integer.MAX_VALUE;
	
	public EventsHandler(TimeWatcher tw, BattleScene scene) {
		this.tw = tw;
		this.scene = scene;
	}
	
	public void watch(){
		int seconds = tw.getSeconds();
		if(lastSeconds != seconds){
			lastSeconds = seconds;
			handleEvents(seconds);
		}
	}
	
	private void handleEvents(int seconds){
		if(every60Seconds()){
			scene.eventBonus();
		}
		if(every30Seconds()){
			scene.eventRevivePlayers();
		}
		if(every60Seconds()){
			scene.eventAsteroids();
		}
	}

	private boolean every30Seconds() {
		int seconds = tw.getSeconds();
		return seconds == 0 || seconds == 30;
	}

	private boolean every60Seconds() {
		return tw.getSeconds() == 0;
	}

}
