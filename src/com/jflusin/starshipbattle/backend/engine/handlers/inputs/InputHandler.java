package com.jflusin.starshipbattle.backend.engine.handlers.inputs;

import com.badlogic.gdx.math.Vector2;
import com.jflusin.starshipbattle.backend.engine.main.Game;

public class InputHandler {

	public static boolean[] clicks;
	public static boolean[] prevClicks;
	public static float mouseX;
	public static float mouseY;
	
	public static boolean[] keys;
	public static boolean[] prevkeys;
	
	public static final int NUM_KEYS = 200;
	public static final int NUM_CLICKS = 10;

	static {
		clicks = new boolean[NUM_CLICKS];
		prevClicks = new boolean[NUM_CLICKS];
		keys = new boolean[NUM_KEYS];
		prevkeys = new boolean[NUM_KEYS];
	}
	
	public static void update() {
		for (int i = 0; i < NUM_KEYS; i++) {
			prevkeys[i] = keys[i];
		}
		for (int i = 0; i < 2; i++) {
			prevClicks[i] = clicks[i];
		}
	}

	public static boolean isClicked(int i){
		return clicks[i] && !prevClicks[i];
	}
	
	public static boolean isClickedAndPressed(int i){
		return clicks[i];
	}
	
	public static void setClick(int button, int x, int y, boolean value){
		clicks[button] = value;
		if(value){
			mouseX = x;
			mouseY = Game.V_HEIGHT - y;
		}
	}
	
	public static boolean isDown(int i) {
		return keys[i];
	}
	
	public static boolean isPressed(int i) {
		return keys[i] && !prevkeys[i];
	}
	
	public static void setKey(int k, boolean value) {
		keys[k] = value;
	}
	
	public static Vector2 getMousePosition(){
		return new Vector2(mouseX, mouseY);
	}
}
