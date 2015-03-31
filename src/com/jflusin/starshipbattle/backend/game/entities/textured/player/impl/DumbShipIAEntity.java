package com.jflusin.starshipbattle.backend.game.entities.textured.player.impl;

import java.util.Random;

import com.badlogic.gdx.math.Vector2;
import com.jflusin.starshipbattle.backend.engine.main.Game;
import com.jflusin.starshipbattle.backend.engine.views.AbstractScene;
import com.jflusin.starshipbattle.backend.game.entities.AbstractEntity;
import com.jflusin.starshipbattle.backend.game.entities.textured.ammo.impl.EnergyEntity;
import com.jflusin.starshipbattle.backend.game.entities.textured.player.AbstractPlayerEntity;
import com.jflusin.starshipbattle.backend.game.enums.Team;

public class DumbShipIAEntity extends AbstractPlayerEntity {

	public int time = 0;
	private AbstractEntity target;
	private Team team;
	
	public DumbShipIAEntity(AbstractScene scene, AbstractEntity target, Team team) {
		super(scene, new Vector2(Game.V_WIDTH / 2, Game.V_HEIGHT / 2));
		this.target = target;
		this.team = team;
	}

	@Override
	public void update(float dt) {
		super.update(dt);
	}
	
	@Override
	public void handleInput() {
		Random r = new Random();
		time++;
		if(time >= Integer.MAX_VALUE - 1) time = Integer.MIN_VALUE;
		if(r.nextBoolean()){
			setX(getX() + 10);
		}
		if(r.nextBoolean()){
			setY(getY() + 10);
		}
		if(r.nextBoolean()){
			setX(getX() - 10);
		}
		if(r.nextBoolean()){
			setY(getY() - 10);
		}
		
		if(r.nextBoolean() && r.nextBoolean() && r.nextBoolean()){
			scene.addTexturedEntity(new EnergyEntity(scene, new Vector2(position.x, position.y), target.getPosition() , this));
		}
	}
	
	@Override
	public Team getTeam() {
		return this.team;
	}
}
