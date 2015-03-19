package com.jflusin.starshipbattle.backend.engine.utils;

import java.util.Stack;

import com.jflusin.starshipbattle.backend.engine.main.Game;
import com.jflusin.starshipbattle.backend.engine.views.AbstractScene;
import com.jflusin.starshipbattle.backend.engine.views.scenes.BattleScene;
import com.jflusin.starshipbattle.backend.engine.views.scenes.EndBattleScene;
import com.jflusin.starshipbattle.backend.engine.views.scenes.SceneData;

public class SceneManager {
	
	private Game game;
	
	private Stack<AbstractScene> scenes;

	public static final int BATTLE_SCENE = 1;
	public static final int END_BATTLE_SCENE = 2;
	
	public Game getGame() {
		return game;
	}
	
	public SceneManager(Game game) {
		this.game = game;
		scenes = new Stack<>();
		pushScene(BATTLE_SCENE, new SceneData());
	}
	
	public void update(float dt){
		scenes.peek().update(dt);
	}
	
	public void render(){
		scenes.peek().render();
	}
	
	private AbstractScene getState(int scene, SceneData sd){
		if (scene == BATTLE_SCENE){
			return new BattleScene(this, sd);
		} else if (scene == END_BATTLE_SCENE){
			return new EndBattleScene(this, sd);
		}
		return null;
	}
	
	public void setState(int scene, SceneData sd){
		popScene();
		pushScene(scene, sd);
	}
	
	public void pushScene(int scene, SceneData sd){
		scenes.push(getState(scene, sd));
	}
	
	public void popScene(){
		AbstractScene s = scenes.pop();
		s.dispose();
	}
}
