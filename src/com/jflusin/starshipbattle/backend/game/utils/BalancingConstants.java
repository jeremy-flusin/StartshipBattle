package com.jflusin.starshipbattle.backend.game.utils;

public abstract class BalancingConstants {

	public static final int NEXUS_MAX_HP = 20000;
	public static final int PLAYER_MAX_HP = 2000;

	public static final int NEXUS_COOLDOWN_FRAMES_BETWEEN_SHOOTS = 10;
	public static final int NEXUS_HEAL_BONUS_VALUE = 4000;

	public static final float ENERGY_VELOCITY = 15f;
	public static final float SHIP_VELOCITY = 10f;

	public static final int MAX_ENERGY_HIT = 500;
	public static final int LASER_HIT_PER_FRAME = 300;

	public static final int TURBO_MAX_DURATION_FRAMES = 1500;
	public static final float TURBO_COEFF = 2f;

	public static final int SHIELD_MAX_DURATION_FRAMES = 1000;
	public static final int SHIELD_COEFF = 3;
	public static final int INVINCIBLE_BONUS_MAX_DURATION_FRAMES = 1000;
}
