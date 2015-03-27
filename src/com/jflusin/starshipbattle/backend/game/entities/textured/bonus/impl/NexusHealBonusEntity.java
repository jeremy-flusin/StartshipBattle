package com.jflusin.starshipbattle.backend.game.entities.textured.bonus.impl;

import com.jflusin.starshipbattle.backend.engine.views.AbstractScene;
import com.jflusin.starshipbattle.backend.engine.views.scenes.BattleScene;
import com.jflusin.starshipbattle.backend.game.entities.AbstractEntity;
import com.jflusin.starshipbattle.backend.game.entities.textured.bonus.BonusEntity;
import com.jflusin.starshipbattle.backend.game.enums.BonusType;
import com.jflusin.starshipbattle.backend.game.enums.Team;
import com.jflusin.starshipbattle.backend.game.interfaces.Fighter;
import com.jflusin.starshipbattle.backend.game.utils.BalancingConstants;

public class NexusHealBonusEntity extends BonusEntity {

	private static final int HEAL = BalancingConstants.NEXUS_HEAL_BONUS_VALUE;
	private boolean hasHealed = false;
	
	public NexusHealBonusEntity(AbstractScene scene) {
		super(scene, "res/bonus-nexus-heal.png", false);
	}

	@Override
	public BonusType getType() {
		return BonusType.NEXUS_HEAL;
	}
	
	@Override
	public void onContact(AbstractEntity other) {
		super.onContact(other);
		if(!hasHealed){
			if(other instanceof Fighter){
				Fighter fighter = (Fighter)other;
				healNexus(fighter.getTeam());
				hasHealed = true;
			}
		}
	}

	private void healNexus(Team team) {
		AbstractScene aScene = getScene();
		if(aScene instanceof BattleScene){
			BattleScene scene = (BattleScene) aScene;
			if(Team.RED.equals(team)){
				scene.getNexusRed().getModel().heal(HEAL);
			}else if(Team.BLUE.equals(team)){ 
				scene.getNexusBlue().getModel().heal(HEAL);
			}
		}
		
	}

}