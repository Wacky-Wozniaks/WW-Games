package com.wackywozniaks.dto;

import java.util.ArrayList;
import java.util.Map;

import com.wackywozniaks.games.checkers.CheckersMove;

/**
 * The response sent to the game of checkers
 * 
 * @author WackyWozniaks Company
 * @version 05/14/2017
 */
public class CheckersResponseBean {
	
	private boolean won;
	private int winner;
	private CheckersMove move;
	private Map<String, ArrayList<CheckersMove>> legalMoves;
	
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

	/**
	 * @return the legalMoves
	 */
	public Map<String, ArrayList<CheckersMove>> getLegalMoves() {
		return legalMoves;
	}

	/**
	 * @param legalMoves the legalMoves to set
	 */
	public void setLegalMoves(Map<String, ArrayList<CheckersMove>> legalMoves) {
		this.legalMoves = legalMoves;
	}

}
