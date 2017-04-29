package com.wackywozniaks.dto;

import com.wackywozniaks.games.connect.ConnectMove;

public class ConnectResponseBean {
	
	private boolean won;
	private ConnectMove move;
	private int winner;
	private int[][] highlight;
	
	/**
	 * @return the won
	 */
	public boolean isWon() {
		return won;
	}
	
	/**
	 * @param won the won to set
	 */
	public void setWon(boolean won) {
		this.won = won;
	}

	/**
	 * @return the move
	 */
	public ConnectMove getMove() {
		return move;
	}

	/**
	 * @param move the move to set
	 */
	public void setMove(ConnectMove move) {
		this.move = move;
	}

	/**
	 * @return the winner
	 */
	public int getWinner() {
		return winner;
	}

	/**
	 * @param winner the winner to set
	 */
	public void setWinner(int winner) {
		this.winner = winner;
	}

	/**
	 * @return the highlight
	 */
	public int[][] getHighlight() {
		return highlight;
	}

	/**
	 * @param highlight the highlight to set
	 */
	public void setHighlight(int[][] highlight) {
		this.highlight = highlight;
	}
	
}
