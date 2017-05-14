package com.wackywozniaks.dto;

/**
 * The data sent from a game of checkers
 * 
 * @author WackyWozniaks Company
 * @version 05/13/2017
 */
public class CheckersBean {
	
	private int[][] boardState;

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

}
