package com.jflusin.starshipbattle.backend.engine.utils;



public class TimeWatcher {
	
	private int frames;
	private static final int GAME_SPEED = 5;
	
	public TimeWatcher() {
		
	}
	
	public void watch() {
		frames += GAME_SPEED;
	}
	
	public String getSecondsString() {
		int value = (frames / 60) % 60;
		if(value < 10){
			return "0 " +value;
		}else{
			return "" + value;
		}
	}
	
	public String getMinutesString() {
		int value = (frames / 3600) % 60;
		if(value < 10){
			return "0 " +value;
		}else{
			return "" + value;
		}
	}

	public int getSeconds(){
		return (frames / 60) % 60;
	}
	
	public int getMinutes(){
		return (frames / 3600) % 60;
	}
	
	public int getFrames() {
		return frames;
	}
}
