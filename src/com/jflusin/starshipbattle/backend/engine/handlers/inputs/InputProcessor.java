package com.jflusin.starshipbattle.backend.engine.handlers.inputs;

import com.badlogic.gdx.InputAdapter;
import com.jflusin.starshipbattle.backend.engine.main.Game;

public class InputProcessor extends InputAdapter {

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		InputHandler.setClick(button, screenX, screenY, true);
		return true;
	}
	
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		InputHandler.setClick(button, screenX, screenY, false);
		return true;
	}
	
	@Override
	public boolean keyDown(int k){
		InputHandler.setKey(k, true);
		return true;
	}

	@Override
	public boolean keyUp(int k){
		InputHandler.setKey(k, false);
		return true;
	}
	
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		InputHandler.mouseX = screenX;
		InputHandler.mouseY = Game.V_HEIGHT - screenY;
		return true;
	}
}
