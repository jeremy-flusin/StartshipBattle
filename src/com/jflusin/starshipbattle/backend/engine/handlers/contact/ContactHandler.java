package com.jflusin.starshipbattle.backend.engine.handlers.contact;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.jflusin.starshipbattle.backend.game.entities.AbstractEntity;

public class ContactHandler implements ContactListener {
	
	
	public ContactHandler() {
	}
	
	@Override
	public void beginContact(Contact contact) {

	}

	@Override
	public void endContact(Contact contact) {

	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {

	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {

	}
	
	public void addCollider(Class<? extends AbstractEntity> classA, 
			Class<? extends AbstractEntity> classB, EntityCollider collider){
		
	}
	
}
