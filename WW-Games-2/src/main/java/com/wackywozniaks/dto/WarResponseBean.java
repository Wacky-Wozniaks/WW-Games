package com.wackywozniaks.dto;

import java.util.LinkedList;

import com.wackywozniaks.games.cards.Card;

public class WarResponseBean {
	
	private Object[] draw;
	private LinkedList<Card> player1;
	private LinkedList<Card> player2;
	private int type;
	private boolean gameOver;
	private int winner;
	private int[] counts; 
	
	/**
	 * @return the draw
	 */
	public Object[] getDraw() {
		return draw;
	}
	
	/**
	 * @param draw the draw to set
	 */
	public void setDraw(Object[] draw) {
		this.draw = draw;
	}

	/**
	 * @return the player1
	 */
	public LinkedList<Card> getPlayer1() {
		return player1;
	}

	/**
	 * @param player1 the player1 to set
	 */
	public void setPlayer1(LinkedList<Card> player1) {
		this.player1 = player1;
	}

	/**
	 * @return the player2
	 */
	public LinkedList<Card> getPlayer2() {
		return player2;
	}

	/**
	 * @param player2 the player2 to set
	 */
	public void setPlayer2(LinkedList<Card> player2) {
		this.player2 = player2;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return the gameOver
	 */
	public boolean isGameOver() {
		return gameOver;
	}

	/**
	 * @param gameOver the gameOver to set
	 */
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	/**
	 * @return the winner
	 */
	public int isWinner() {
		return winner;
	}

	/**
	 * @param winner the winner to set
	 */
	public void setWinner(int winner) {
		this.winner = winner;
	}

	/**
	 * @return the counts
	 */
	public int[] getCounts() {
		return counts;
	}

	/**
	 * @param counts the counts to set
	 */
	public void setCounts(int[] counts) {
		this.counts = counts;
	}
	
}
