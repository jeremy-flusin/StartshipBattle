package com.jflusin.starshipbattle.backend.engine.handlers.sound.music;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class MusicPlayer {

	private static final int SONGS_COUNT = 3;

	private Map<String, Sound> tracks;
	private Sound playingSong = null;

	private float volume = 1f;

	private boolean playing = false;

	public MusicPlayer() {
		// Load files
		tracks = new HashMap<String, Sound>();
		tracks.put(MusicFiles.LENSKO_CIRCLES,
				Gdx.audio.newSound(Gdx.files.local(MusicFiles.LENSKO_CIRCLES)));
		tracks.put(MusicFiles.LENSKO_LETS_GO,
				Gdx.audio.newSound(Gdx.files.local(MusicFiles.LENSKO_LETS_GO)));
		tracks.put(MusicFiles.LENSKO_SARVAGON, Gdx.audio.newSound(Gdx.files
				.local(MusicFiles.LENSKO_SARVAGON)));
	}

	public void playTrack(String file) {
		playingSong = tracks.get(file);
		playingSong.play(volume);
		playing = true;

	}

	public void stopMusic() {
		playingSong.stop();
		playing = false;
	}

	public void setVolume(float volume) {
		this.volume = volume;
	}

	public void playRandomTrack() {
		Random r = new Random();
		switch (r.nextInt(SONGS_COUNT)) {
		case 0:
			playTrack(MusicFiles.LENSKO_CIRCLES);
			break;
		case 1:
			playTrack(MusicFiles.LENSKO_LETS_GO);
			break;
		case 2:
			playTrack(MusicFiles.LENSKO_SARVAGON);
			break;
		default:
			throw new RuntimeException("Song file not loaded !");
		}
	}

	public boolean isPlaying() {
		return playing;
	}

}
