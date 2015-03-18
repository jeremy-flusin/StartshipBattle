package com.jflusin.starshipbattle.backend.game.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class TexturedSprite {

	private Sprite sprite;
	
	public TexturedSprite(String texturePath, float width, float height) {
		Texture texture = new Texture(Gdx.files.internal(texturePath));
		sprite = new Sprite(texture);
		sprite.setSize(width, height);
	}
	
	public Sprite getSprite() {
		return sprite;
	}

}
