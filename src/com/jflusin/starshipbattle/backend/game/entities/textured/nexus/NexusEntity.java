package com.jflusin.starshipbattle.backend.game.entities.textured.nexus;

import com.badlogic.gdx.math.Vector2;
import com.jflusin.starshipbattle.backend.engine.handlers.sound.effects.SoundType;
import com.jflusin.starshipbattle.backend.engine.views.scenes.BattleScene;
import com.jflusin.starshipbattle.backend.game.entities.AbstractEntity;
import com.jflusin.starshipbattle.backend.game.entities.rendered.info.bars.impl.NexusHPBarEntity;
import com.jflusin.starshipbattle.backend.game.entities.textured.AbstractTexturedEntity;
import com.jflusin.starshipbattle.backend.game.entities.textured.ammo.AmmoEntity;
import com.jflusin.starshipbattle.backend.game.entities.textured.ammo.impl.NexusShootEntity;
import com.jflusin.starshipbattle.backend.game.entities.textured.player.impl.PlayerEntity;
import com.jflusin.starshipbattle.backend.game.enums.ShootTypes;
import com.jflusin.starshipbattle.backend.game.interfaces.Fighter;
import com.jflusin.starshipbattle.backend.game.interfaces.IsSolid;
import com.jflusin.starshipbattle.backend.game.models.impl.NexusModel;
import com.jflusin.starshipbattle.backend.game.utils.TeamUtils;

public abstract class NexusEntity extends AbstractTexturedEntity implements IsSolid, Fighter{
	
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
			Fighter shooter = (Fighter) ammo.getShooter();
			if (!getTeam().equals(shooter.getTeam())) {
				getModel().takeDamage(ammo.getCurrentPower());
				getScene().getSFX().playRandom(SoundType.HITS);
				if(getModel().getCurrentHP() <= 0){
					destroy();
				}
			}
		}
	}

	@Override
	public void handleInput() {
		for (PlayerEntity player : getScene().getPlayers(TeamUtils.getOppositeTeam(getTeam()))) {
			
			if (getMinXToWatch() < player.getX() && player.getX() <= getMaxXToWatch()) {
				if(getModel().canShoot()){
					if(player.getModel().isAlive()){
						shootTargetTrack(ShootTypes.PRIMARY, player);
					}
				}
			}
		}
	}
	
	protected abstract float getMinXToWatch();
	protected abstract float getMaxXToWatch();

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
