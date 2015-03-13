package com.jflusin.starshipbattle.backend.engine.utils;

import java.util.Stack;

import com.jflusin.starshipbattle.backend.engine.main.Game;
import com.jflusin.starshipbattle.backend.engine.views.AbstractScene;
import com.jflusin.starshipbattle.backend.engine.views.scenes.TestScene;

public class SceneManager {
	
	private Game game;
	
	private Stack<AbstractScene> scenes;
	
	public static final int TEST_SCENE = 1;
	
	public Game getGame() {
		return game;
	}
	
	public SceneManager(Game game) {
		this.game = game;
		scenes = new Stack<>();
		pushScene(TEST_SCENE);
	}
	
	public void update(float dt){
		scenes.peek().update(dt);
	}
	
	public void render(){
		scenes.peek().render();
	}
	
	private AbstractScene getState(int scene){
		if (scene == TEST_SCENE){
			return new TestScene(this);
		}
		return null;
	}
	
	public void setState(int scene){
		popScene();
		pushScene(scene);
	}
	
	public void pushScene(int scene){
		scenes.push(getState(scene));
	}
	
	public void popScene(){
		AbstractScene s = scenes.pop();
		s.dispose();
	}
	
}
