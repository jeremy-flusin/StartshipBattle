package com.jflusin.starshipbattle.backend.engine.utils;



public class TimeWatcher {
	
	private int frames;
	
	public TimeWatcher() {
		
	}
	
	public void watch() {
		frames += 1;
	}
	
	public String getSeconds() {
		int value = (frames / 60) % 60;
		if(value < 10){
			return "0 " +value;
		}else{
			return "" + value;
		}
	}
	
	public String getMinutes() {
		int value = (frames / 3600) % 60;
		if(value < 10){
			return "0 " +value;
		}else{
			return "" + value;
		}
	}
}
