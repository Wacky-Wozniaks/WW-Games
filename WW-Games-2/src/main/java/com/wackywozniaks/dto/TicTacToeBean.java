package com.wackywozniaks.dto;

public class TicTacToeBean {
	
	private String[] gameState;
	private Boolean win;
	
	public String[] getGameState() {
		return gameState;
	}
	
	public void setGameState(String[] gameState) {
		this.gameState = gameState;
	}
	
	public Boolean isWin() {
		return win;
	}
	
	public void setWin(Boolean win) {
		this.win = win;
	}

}
