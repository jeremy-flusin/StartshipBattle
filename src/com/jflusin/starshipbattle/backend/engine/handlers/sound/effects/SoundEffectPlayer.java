package com.jflusin.starshipbattle.backend.engine.handlers.sound.effects;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundEffectPlayer {

	private ArrayList<Sound> lasers;
	private ArrayList<Sound> hits;
	private ArrayList<Sound> explosions;
	private ArrayList<Sound> powerUps;

	private Sound fxBonusLaser1;
	private Sound fxBonusLaser2;
	private Sound fxBonusLaser3;

	private Random r;

	private float volume = 0.3f;

	public SoundEffectPlayer() {
		r = new Random();
		lasers = new ArrayList<Sound>();
		hits = new ArrayList<Sound>();
		explosions = new ArrayList<Sound>();
		powerUps = new ArrayList<Sound>();

		lasers.add(Gdx.audio.newSound(Gdx.files.local(SoundEffectFiles.LASER1)));
		lasers.add(Gdx.audio.newSound(Gdx.files.local(SoundEffectFiles.LASER2)));
		lasers.add(Gdx.audio.newSound(Gdx.files.local(SoundEffectFiles.LASER3)));

		hits.add(Gdx.audio.newSound(Gdx.files.local(SoundEffectFiles.HIT1)));
		hits.add(Gdx.audio.newSound(Gdx.files.local(SoundEffectFiles.HIT2)));

		explosions.add(Gdx.audio.newSound(Gdx.files
				.local(SoundEffectFiles.EXPLOSION1)));
		explosions.add(Gdx.audio.newSound(Gdx.files
				.local(SoundEffectFiles.EXPLOSION2)));
		explosions.add(Gdx.audio.newSound(Gdx.files
				.local(SoundEffectFiles.EXPLOSION3)));
		explosions.add(Gdx.audio.newSound(Gdx.files
				.local(SoundEffectFiles.EXPLOSION4)));
		explosions.add(Gdx.audio.newSound(Gdx.files
				.local(SoundEffectFiles.EXPLOSION5)));
		explosions.add(Gdx.audio.newSound(Gdx.files
				.local(SoundEffectFiles.EXPLOSION6)));
		explosions.add(Gdx.audio.newSound(Gdx.files
				.local(SoundEffectFiles.EXPLOSION7)));

		powerUps.add(Gdx.audio.newSound(Gdx.files
				.local(SoundEffectFiles.POWER_UP1)));
		powerUps.add(Gdx.audio.newSound(Gdx.files
				.local(SoundEffectFiles.POWER_UP2)));
		powerUps.add(Gdx.audio.newSound(Gdx.files
				.local(SoundEffectFiles.POWER_UP3)));
		powerUps.add(Gdx.audio.newSound(Gdx.files
				.local(SoundEffectFiles.POWER_UP4)));
		powerUps.add(Gdx.audio.newSound(Gdx.files
				.local(SoundEffectFiles.POWER_UP5)));
		powerUps.add(Gdx.audio.newSound(Gdx.files
				.local(SoundEffectFiles.POWER_UP6)));
		powerUps.add(Gdx.audio.newSound(Gdx.files
				.local(SoundEffectFiles.POWER_UP7)));

		fxBonusLaser1 = Gdx.audio.newSound(Gdx.files
				.local(SoundEffectFiles.MISC1));
		fxBonusLaser2 = Gdx.audio.newSound(Gdx.files
				.local(SoundEffectFiles.MISC2));
		fxBonusLaser3 = Gdx.audio.newSound(Gdx.files
				.local(SoundEffectFiles.MISC3));

	}

	public void playRandom(SoundType type) {
		ArrayList<Sound> sounds = null;
		if (SoundType.LASER.equals(type)) {
			sounds = lasers;
		} else if (SoundType.HITS.equals(type)) {
			sounds = hits;
		} else if (SoundType.EXPLOSION.equals(type)) {
			sounds = explosions;
		} else if (SoundType.POWER_UP.equals(type)) {
			sounds = powerUps;
		}
		Sound toPlay = sounds.get(r.nextInt(sounds.size()));
		toPlay.play(volume);
	}

	public void setVolume(float volume) {
		this.volume = volume;
	}

	public void playLaserBonusSound() {
		fxBonusLaser1.play(volume);
		fxBonusLaser2.play(volume);
		fxBonusLaser3.play(volume);
	}

}
