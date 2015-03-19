package com.jflusin.starshipbattle.backend.game.entities.textured.nexus;

import com.badlogic.gdx.math.Vector2;
import com.jflusin.starshipbattle.backend.engine.views.scenes.BattleScene;
import com.jflusin.starshipbattle.backend.game.entities.AbstractEntity;
import com.jflusin.starshipbattle.backend.game.entities.rendered.info.bars.impl.NexusHPBarEntity;
import com.jflusin.starshipbattle.backend.game.entities.textured.AbstractTexturedEntity;
import com.jflusin.starshipbattle.backend.game.entities.textured.ammo.AmmoEntity;
import com.jflusin.starshipbattle.backend.game.entities.textured.ammo.impl.NexusShootEntity;
import com.jflusin.starshipbattle.backend.game.enums.ShootTypes;
import com.jflusin.starshipbattle.backend.game.interfaces.CanShoot;
import com.jflusin.starshipbattle.backend.game.interfaces.IsSolid;
import com.jflusin.starshipbattle.backend.game.models.impl.NexusModel;

public abstract class NexusEntity extends AbstractTexturedEntity implements IsSolid, CanShoot{
	
	protected NexusHPBarEntity hpBar;
	
	public NexusEntity(BattleScene scene, String texturePath,
			Vector2 initPosition, float width, float height, boolean collidable) {
		super(scene, texturePath, initPosition, width, height, collidable);
		model = new NexusModel();
	}
	
	@Override
	public void onContact(AbstractEntity other) {
		if (other instanceof AmmoEntity) {
			AmmoEntity ammo = (AmmoEntity) other;
			CanShoot shooter = (CanShoot) ammo.getShooter();
			if (!getTeam().equals(shooter.getTeam())) {
				getModel().takeDamage(ammo.getCurrentPower());
				if(getModel().getCurrentHP() <= 0){
					destroy();
				}
			}
		}
	}

	public BattleScene getScene() {
		return (BattleScene)super.getScene();
	}
	
	@Override
	public void shoot(ShootTypes type, Vector2 target) {

	}
	
	@Override
	public void shootTargetTrack(ShootTypes type, AbstractEntity target) {
		scene.addTexturedEntity(new NexusShootEntity(
				scene, new Vector2(getX(), getY() + height/2 - 40), target, this));
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
	
	@Override
	public void destroy() {
		super.destroy();
	}
}
