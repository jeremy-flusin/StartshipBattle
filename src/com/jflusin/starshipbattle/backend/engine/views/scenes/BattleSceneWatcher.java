package com.jflusin.starshipbattle.backend.engine.views.scenes;

import java.util.ArrayList;

import com.jflusin.starshipbattle.backend.engine.utils.SceneManager;
import com.jflusin.starshipbattle.backend.game.entities.textured.player.impl.ShipPlayerEntity;
import com.jflusin.starshipbattle.backend.game.enums.Team;


public class BattleSceneWatcher {
	
	private int frames;
	private int seconds;
	private int minutes;
	
	private SceneManager sm;
	private BattleScene scene;
	
	public BattleSceneWatcher(SceneManager sm, BattleScene scene) {
		this.sm = sm;
		this.scene = scene;
	}
	
	public void watch() {
		
		handleTime();
		
		//Nexus vulnerability
		boolean blueNexusVulnerable = true;
		ArrayList<ShipPlayerEntity> bluePlayers = scene.getPlayers(Team.BLUE);
		for (ShipPlayerEntity bluePlayer : bluePlayers) {
			if(bluePlayer.getModel().isAlive()){
				blueNexusVulnerable = false;
			}
		}
		scene.getNexusBlue().getModel().setVulnerable(blueNexusVulnerable);
		boolean redNexusVulnerable = true;
		ArrayList<ShipPlayerEntity> redPlayers = scene.getPlayers(Team.RED);
		for (ShipPlayerEntity redPlayer : redPlayers) {
			if(redPlayer.getModel().isAlive()){
				redNexusVulnerable = false;
			}
		}
		scene.getNexusRed().getModel().setVulnerable(redNexusVulnerable);
		
		//Winning game
		if(scene.getNexusBlue().getModel().getCurrentHP() <= 0){
			endGame(Team.RED);
		}else if(scene.getNexusRed().getModel().getCurrentHP() <= 0){
			endGame(Team.BLUE);
		}
	}
	
	private void handleTime() {
		frames++;
		seconds = frames / 60;
		minutes = seconds / 60;
		System.out.println("Time elapsed: " + minutes + "min " + seconds + "sec");
	}

	private void endGame(Team winningTeam) {
		SceneData sd = new SceneData();
		sd.putData("winningTeam", winningTeam);
		sm.setState(SceneManager.END_BATTLE_SCENE, sd);
	}
	
}
