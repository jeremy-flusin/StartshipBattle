package com.jflusin.starshipbattle.backend.game.entities;

import com.badlogic.gdx.math.Vector2;
import com.jflusin.starshipbattle.backend.engine.views.scenes.BattleScene;
import com.jflusin.starshipbattle.backend.game.enums.ShootTypes;
import com.jflusin.starshipbattle.backend.game.interfaces.CanShoot;
import com.jflusin.starshipbattle.backend.game.interfaces.IsSolid;
import com.jflusin.starshipbattle.backend.game.models.NexusModel;

public abstract class NexusEntity extends AbstractEntity implements IsSolid, CanShoot{
	
	public NexusEntity(BattleScene scene, String texturePath,
			Vector2 initPosition, float width, float height, boolean collidable) {
		super(scene, texturePath, initPosition, width, height, collidable);
		model = new NexusModel();
	}
	
	@Override
	public void onContact(AbstractEntity other) {
		
	}

	public BattleScene getScene() {
		return (BattleScene)super.getScene();
	}
	
	@Override
	public void shoot(ShootTypes type, Vector2 target) {
		scene.addEntity(new NexusShootEntity(
				scene, new Vector2(0, 0), target, this));
		getModel().setShootCooldown(0);
	}
	
	@Override
	public NexusModel getModel() {
		return (NexusModel)super.getModel();
	}
	
	@Override
	public void update(float dt) {
		getModel().updateCooldown();
	}
}
