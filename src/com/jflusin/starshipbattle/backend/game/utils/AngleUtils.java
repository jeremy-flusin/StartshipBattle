package com.jflusin.starshipbattle.backend.game.utils;

import com.badlogic.gdx.math.Vector2;

public class AngleUtils {

	public static float getRadAngle(Vector2 source, Vector2 target){
		double angleRad = Math.atan2(target.y - source.y, target.x - source.x);
		return (float)angleRad;
	}
	
	public static float getDegAngle(Vector2 source, Vector2 target){
		return (float)Math.toDegrees(getRadAngle(source, target));
	}
}
