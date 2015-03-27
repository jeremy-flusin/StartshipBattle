package com.jflusin.starshipbattle.backend.engine.views;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jflusin.starshipbattle.backend.engine.main.Game;

public class MessageHandler {

	private BitmapFont font;
	private String message;
	private int duration = 0;
	public MessageHandler() {
		font = new BitmapFont();
		font.setScale(2f);
	}

	public void shout(String message, int duration) {
		this.message = message;
		this.duration = duration;
	}

	public void render(SpriteBatch sb) {
		if (message != null) {
			sb.begin();
			font.draw(sb, message, Game.V_HEIGHT / 2 - message.length() / 2,
					Game.V_HEIGHT - 100);
			sb.end();
		}
	}

	public void update() {
		duration--;
		if (duration < 0) {
			duration = 0;
			message = null;
		}
	}

}
