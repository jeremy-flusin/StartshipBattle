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
		if (secondsEqualTo(0) || secondsEqualTo(30)) {
			scene.eventRevivePlayers();
		}
		if (secondsEqualTo(15)) {
			scene.eventAsteroids();
		}
		if (secondsEqualTo(45)) {
			scene.eventBonus();
		}
	}

	private boolean secondsEqualTo(int seconds) {
		return tw.getSeconds() == seconds;
	}
}
