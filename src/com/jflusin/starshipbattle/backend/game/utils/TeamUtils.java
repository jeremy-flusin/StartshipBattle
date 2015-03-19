package com.jflusin.starshipbattle.backend.game.utils;

import com.jflusin.starshipbattle.backend.game.enums.Team;

public class TeamUtils {

	public static Team getOppositeTeam(Team team){
		if(Team.RED.equals(team)){
			return Team.BLUE;
		}else{
			return Team.RED;
		}
	}
	
}
