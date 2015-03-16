package com.jflusin.starshipbattle.backend.game.entities;

import java.util.ArrayList;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.jflusin.starshipbattle.backend.engine.handlers.inputs.InputHandler;

public class ShipEntity extends AbstractEntity {

	public static float MAX_VELOCITY = 10f;
	public static float ACCELERATION = 0.8f;
	public static float DECELERATION = 0.5f;
	public static float WIDTH = 32;
	public static float HEIGHT = 32;

	ArrayList<AmmoEntity> ammos;
	ArrayList<FireEntity> fires;
	
	public ShipEntity(World world, String texturePath, 
			Vector2 initPosition){
		super(world, texturePath, initPosition, WIDTH, HEIGHT);
		ammos = new ArrayList<AmmoEntity>();
		fires = new ArrayList<FireEntity>();
	}
	
	@Override
	public void handleInput() {
		
		float accelerationX = getAccelerationX();
		float accelerationY = getAccelerationY();
		
		if(InputHandler.isDown(Input.Keys.UP)){
			accelerationY += ACCELERATION;
			if(accelerationY >= MAX_VELOCITY){
				accelerationY =  MAX_VELOCITY;
			}
		}else if(InputHandler.isDown(Input.Keys.DOWN)){
			accelerationY -= ACCELERATION;
			if(accelerationY <= - MAX_VELOCITY){
				accelerationY = - MAX_VELOCITY;
			}
		}else{
			if(accelerationY > 0){
				accelerationY -= DECELERATION;
				if(accelerationY <= 0){
					accelerationY = 0;
				}
			}if(accelerationY < 0){
				accelerationY += DECELERATION;
				if(accelerationY >= 0){
					accelerationY = 0;
				}
			}
		}
		
		setAccelerationY(accelerationY);
		setY(getY() + accelerationY); 
		
		
		if(InputHandler.isDown(Input.Keys.RIGHT)){
			accelerationX += ACCELERATION;
			if(accelerationX >= MAX_VELOCITY){
				accelerationX = MAX_VELOCITY;
			}
		}else if(InputHandler.isDown(Input.Keys.LEFT)){
			accelerationX -= ACCELERATION;
			if(accelerationX <= - MAX_VELOCITY){
				accelerationX = - MAX_VELOCITY;
			}
		}else{
			if(accelerationX > 0){
				accelerationX -= DECELERATION;
				if(accelerationX <= 0){
					accelerationX = 0;
				}
			}if(accelerationX < 0){
				accelerationX += DECELERATION;
				if(accelerationX >= 0){
					accelerationX = 0;
				}
			}
		}
		
		setAccelerationX(accelerationX);
		setX(getX() + accelerationX); 
		
		if(InputHandler.isClicked(Input.Buttons.LEFT)){
			ammos.add(new AmmoEntity(world, 
					new Vector2(this.position.x, this.position.y), 
					getMouseEntityRadAngle()));
		};
		if(InputHandler.isClicked(Input.Buttons.RIGHT)){
			fires.add(new FireEntity(world, 
					new Vector2(this.position.x, this.position.y), 
					getMouseEntityRadAngle()));
		};
	}
	
	@Override
	public void update() {
		super.update();
		for (AmmoEntity ammoEntity : ammos) {
			ammoEntity.update();
		}
		for (FireEntity fireEntity : fires) {
			fireEntity.update();
		}
		setAngle(getMouseEntityRadAngle());
	}

	private float getMouseEntityRadAngle() {
		double angleRad = Math.atan2(InputHandler.mouseY - getY() , InputHandler.mouseX - getX());
		return (float)angleRad;
	}
	
	public ArrayList<AmmoEntity> getAmmos() {
		return ammos;
	}
	
	public ArrayList<FireEntity> getFires() {
		return fires;
	}
	
	@Override
	public Body createBody() {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.x = this.position.x;
		bodyDef.position.y = this.position.y;
		Body body = world.createBody(bodyDef);
		CircleShape circle = new CircleShape();
		circle.setRadius(0.5f);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = circle;
		body.createFixture(fixtureDef);
		circle.dispose();
		return body;
	}
}
