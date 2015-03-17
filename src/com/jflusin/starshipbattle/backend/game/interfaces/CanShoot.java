package com.jflusin.starshipbattle.backend.game.interfaces;

import com.badlogic.gdx.math.Vector2;
import com.jflusin.starshipbattle.backend.game.enums.ShootTypes;
import com.jflusin.starshipbattle.backend.game.enums.Team;

public interface CanShoot {

	public void shoot(ShootTypes type, Vector2 target);
	public Team getTeam();
}
