package com.wackywozniaks.dto;

import java.util.LinkedList;

import com.wackywozniaks.games.cards.Card;

public class WarBean {
	
	private LinkedList<Card> player1;
	private LinkedList<Card> player2;
	
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
	
}
