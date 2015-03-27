package com.jflusin.starshipbattle.backend.game.utils;

import com.jflusin.starshipbattle.backend.game.enums.Team;

public abstract class Constants {

	public static final String REVIVE_EVENT = "Réapparition de tous les joueurs !";

	public static final String ASTEROIDS_INCOMMING = "Comètes en approche !";
	
	public static final String BONUS_APPERANCE = "Un bonus apparaît !";
	
	public static String NEXUS_VULNERABLE(Team team) {
		if (Team.BLUE.equals(team)) {
			return "Ace pour l'équipe ROUGE ! Le Nexus BLEU est vulnérable !";
		} else if (Team.RED.equals(team)) {
			return "Ace pour l'équipe BLEUE ! Le Nexus ROUGE est vulnérable !";
		} else {
			return null;
		}
	}

}
