package com.wos.tictactoe.model;

public enum GameState {
	
	NEW, PLAYING, PAUSED, FINISHED; 
	
	public boolean isNew() {
		return this == NEW;
	}
	public boolean isPlaying() {
		return this == PLAYING;
	}
	public boolean isPaused() {
		return this == PAUSED;
	}
	public boolean isFinished() {
		return this == FINISHED;
	}
}
