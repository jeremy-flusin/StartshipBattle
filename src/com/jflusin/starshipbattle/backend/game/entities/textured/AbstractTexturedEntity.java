package com.jflusin.starshipbattle.backend.game.entities.textured;

import com.badlogic.gdx.math.Vector2;
import com.jflusin.starshipbattle.backend.engine.views.AbstractScene;
import com.jflusin.starshipbattle.backend.game.entities.AbstractEntity;
import com.jflusin.starshipbattle.backend.game.sprite.TexturedSprite;

public abstract class AbstractTexturedEntity extends AbstractEntity {

	private TexturedSprite texturedSprite;
	
	public AbstractTexturedEntity(AbstractScene scene, String texturePath, Vector2 initPosition, float width, float height,
			boolean collidable) {
		super(scene, initPosition, width, height, collidable);
		texturedSprite = new TexturedSprite(texturePath, width, height);
	}

	@Override
	public void update(float dt) {
		super.update(dt);
		texturedSprite.getSprite().setPosition(position.x, position.y);
		texturedSprite.getSprite().setRotation((float)Math.toDegrees(angle));
	}
	
	public TexturedSprite getTexturedSprite() {
		return texturedSprite;
	}
}
