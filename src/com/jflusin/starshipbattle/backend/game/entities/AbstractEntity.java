package com.jflusin.starshipbattle.backend.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class AbstractEntity {

	private Texture texture;
	private Sprite sprite;
	
	public AbstractEntity(String texturePath) {
		texture = new Texture(Gdx.files.internal(texturePath));
		sprite = new Sprite(texture);
	}
	
	public Sprite getSprite() {
		return sprite;
	}

	public abstract void handleInput();
}
