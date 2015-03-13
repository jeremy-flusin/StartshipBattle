package com.jflusin.starshipbattle.backend.engine.handlers.inputs;

import com.badlogic.gdx.InputAdapter;

public class InputProcessor extends InputAdapter {

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if(button == 0){
			InputHandler.setClick(0, screenX, screenY, true);
		}
		if(button == 1){
			InputHandler.setClick(1, screenX, screenY, true);
		}
		return true;
	}
	
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if(button == 0){
			InputHandler.setClick(0, screenX, screenY, false);
		}
		if(button == 1){
			InputHandler.setClick(1, screenX, screenY, false);
		}
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
		InputHandler.mouseY = screenY;
		return true;
	}
}
