package com.jflusin.starshipbattle.backend.game.entities;

import java.util.Random;

import com.badlogic.gdx.math.Vector2;
import com.jflusin.starshipbattle.backend.engine.main.Game;
import com.jflusin.starshipbattle.backend.engine.views.AbstractScene;

public class DumbIAEntity extends ShipEntity {

	public int time = 0;
	private AbstractEntity target;
	
	public DumbIAEntity(AbstractScene scene, AbstractEntity player) {
		super(scene, "res/enemy.png", new Vector2(Game.V_WIDTH / 2, Game.V_HEIGHT / 2));
		this.target = player;
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
			scene.addEntity(new LaserEntity(scene, new Vector2(position.x, position.y), target.position , this));
		}
		if(r.nextBoolean() && r.nextBoolean() && r.nextBoolean()){
			scene.addEntity(new FireEntity(scene, new Vector2(position.x, position.y), target.position , this));
		}
		
	}
	
}
