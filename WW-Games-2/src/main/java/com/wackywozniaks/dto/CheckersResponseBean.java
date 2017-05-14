package com.wackywozniaks.dto;

import com.wackywozniaks.games.checkers2.CheckersMove;

/**
 * The response sent to the game of checkers
 * 
 * @author WackyWozniaks Company
 * @version 05/13/2017
 */
public class CheckersResponseBean {
	
	private boolean won;
	private int winner;
	private CheckersMove move;
	
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
	 * @return the move
	 */
	public CheckersMove getMove() {
		return move;
	}

	/**
	 * @param move the move to set
	 */
	public void setMove(CheckersMove move) {
		this.move = move;
	}

}
