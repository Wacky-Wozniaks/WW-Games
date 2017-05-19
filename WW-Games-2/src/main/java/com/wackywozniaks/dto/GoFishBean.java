package com.wackywozniaks.dto;

import java.util.ArrayList;
import java.util.LinkedList;

import com.wackywozniaks.games.cards.Card;

/**
 * Transfers Data for Go Fish
 * 
 * @author WackyWozniaks Company
 * @version 05/18/2017
 */
public class GoFishBean
{
	private int type;
	private ArrayList<Card> hand1;
	private ArrayList<Card> hand2;
	private LinkedList<Card> deck;
	private int[] books;
	private String move;
	
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
	 * @return the hand1
	 */
	public ArrayList<Card> getHand1() {
		return hand1;
	}

	/**
	 * @param hand1 the hand1 to set
	 */
	public void setHand1(ArrayList<Card> hand1) {
		this.hand1 = hand1;
	}

	/**
	 * @return the hand2
	 */
	public ArrayList<Card> getHand2() {
		return hand2;
	}

	/**
	 * @param hand2 the hand2 to set
	 */
	public void setHand2(ArrayList<Card> hand2) {
		this.hand2 = hand2;
	}

	/**
	 * @return the deck
	 */
	public LinkedList<Card> getDeck() {
		return deck;
	}

	/**
	 * @param deck the deck to set
	 */
	public void setDeck(LinkedList<Card> deck) {
		this.deck = deck;
	}

	/**
	 * @return the books
	 */
	public int[] getBooks() {
		return books;
	}

	/**
	 * @param books the books to set
	 */
	public void setBooks(int[] books) {
		this.books = books;
	}

	/**
	 * @return the move
	 */
	public String getMove() {
		return move;
	}

	/**
	 * @param move the move to set
	 */
	public void setMove(String move) {
		this.move = move;
	}
	
}