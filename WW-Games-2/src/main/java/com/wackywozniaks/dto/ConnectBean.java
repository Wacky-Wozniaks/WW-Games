package com.wackywozniaks.dto;

public class ConnectBean {
	
	private int[][] boardState;
	
	private int row;
	
	private int col;

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
	
}
