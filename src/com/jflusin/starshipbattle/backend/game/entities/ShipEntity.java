package com.jflusin.starshipbattle.backend.game.entities;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.jflusin.starshipbattle.backend.engine.handlers.inputs.InputHandler;
import com.jflusin.starshipbattle.backend.engine.views.AbstractScene;

public class ShipEntity extends AbstractEntity {

	public static int MAX_LIFE = 50;
	public int currentLife = MAX_LIFE;
	
	public static float MAX_VELOCITY = 10f;
	public static float ACCELERATION = 0.8f;
	public static float DECELERATION = 0.5f;
	public static float WIDTH = 32;
	public static float HEIGHT = 32;

	public ShipEntity(AbstractScene scene, String texturePath, 
			Vector2 initPosition){
		super(scene, texturePath, initPosition, WIDTH, HEIGHT, true);
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
			scene.addEntity(new LaserEntity(scene, 
					new Vector2(this.position.x, this.position.y), 
					new Vector2(InputHandler.mouseX, InputHandler.mouseY), this));
		};
		if(InputHandler.isClicked(Input.Buttons.RIGHT)){
			scene.addEntity(new FireEntity(scene, 
					new Vector2(this.position.x, this.position.y), 
					new Vector2(InputHandler.mouseX, InputHandler.mouseY), this));
		};
	}
	
	@Override
	public void update(float dt) {
		super.update(dt);
		setAngle(getMouseEntityRadAngle());
	}

	private float getMouseEntityRadAngle() {
		double angleRad = Math.atan2(InputHandler.mouseY - getY() , InputHandler.mouseX - getX());
		return (float)angleRad;
	}
	
	@Override
	public Body createBody() {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.x = this.position.x;
		bodyDef.position.y = this.position.y;
		Body body = scene.getWorld().createBody(bodyDef);
		CircleShape circle = new CircleShape();
		circle.setRadius(0.5f);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = circle;
		body.createFixture(fixtureDef);
		circle.dispose();
		return body;
	}

	@Override
	public void onContact(AbstractEntity other) {
		if(other instanceof ShipEntity){
			this.acceleration.x = - this.acceleration.x;
			this.acceleration.y = - this.acceleration.y;
		}else if(other instanceof AmmoEntity){
			AmmoEntity ammo = (AmmoEntity) other;
			if(!ammo.getShooter().equals(this)){
				currentLife -= ammo.getCurrentPower();
				System.out.println("Ship shot with power: " + ammo.getCurrentPower());
				if (currentLife <= 0){
					destroy();
				}
			}
		}
	}
	
	public int getCurrentLife() {
		return currentLife;
	}
}
