package com.jflusin.starshipbattle.backend.engine.handlers.contact;

import java.util.ArrayList;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.jflusin.starshipbattle.backend.game.entities.AbstractEntity;

public class ContactHandler implements ContactListener {
	
	ArrayList<AbstractEntity> registeredEntities;
	
	public ContactHandler() {
		registeredEntities = new ArrayList<AbstractEntity>();
	}
	
	@Override
	public void beginContact(Contact contact) {
		boolean entitiesRegistred = false;
		AbstractEntity entityA = null;
		AbstractEntity entityB = null;
		for (AbstractEntity entity : registeredEntities) {
			if(contact.getFixtureA().getBody().equals(entity.getBody())){
				entityA = entity;
				for (AbstractEntity entityBis  : registeredEntities){
					if (contact.getFixtureB().getBody().equals(entityBis.getBody())){
						entityB = entityBis;
						entitiesRegistred = true;
					}
				}
			}
		}
		if(entitiesRegistred){
			entityA.onContact(entityB);
			entityB.onContact(entityA);
		}
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
	
	public void registerEntity(AbstractEntity entity){
		registeredEntities.add(entity);
	}
}
