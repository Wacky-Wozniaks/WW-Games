package com.wackywozniaks.dto;

/**
 * Sends data from client to server about connect games.
 * 
 * @author WackyWozniaks Company
 * @version 05/16/2017
 */
public class ConnectBean {
	
	private int[][] boardState;
	private int row;
	private int col;
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
	 * @return the row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * @param row the row to set
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * @return the col
	 */
	public int getCol() {
		return col;
	}

	/**
	 * @param col the col to set
	 */
	public void setCol(int col) {
		this.col = col;
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
