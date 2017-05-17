package com.wackywozniaks.dto;

/**
 * The data sent from a game of checkers
 * 
 * @author WackyWozniaks Company
 * @version 05/13/2017
 */
public class CheckersBean {
	
	private int[][] boardState;
	private int difficulty;

	/**
	 * @return the boardState
	 */
	public int[][] getBoardState() {
		return boardState;
	}

	/**
	 * @param boardState the boardState to set
	 */
	public void setBoardState(int[][] boardState) {
		this.boardState = boardState;
	}

	/**
	 * @return the difficulty
	 */
	public int getDifficulty() {
		return difficulty;
	}

	/**
	 * @param difficulty the difficulty to set
	 */
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

}
