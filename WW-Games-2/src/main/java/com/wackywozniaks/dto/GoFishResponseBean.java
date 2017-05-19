package com.wackywozniaks.dto;

import java.util.ArrayList;
import java.util.LinkedList;

import com.wackywozniaks.games.Move;
import com.wackywozniaks.games.cards.Card;

/**
 * Sends data to client for Data
 * 
 * @author WackyWoznaiks Company
 * @version 05/18/2017
 */
public class GoFishResponseBean
{
	private int step; //1 = before player moves, 2 = after computer responds, 3 = after computer move
	private ArrayList<Card> hand1, hand2, oldHand1, oldHand2, transfer;
	private LinkedList<Card> deck;
	private int[] books;
	private LinkedList<Move> possible;
	private boolean goFish, goAgain, gameOver;
	private String requested;
	private int winner;
	
	/**
	 * @return the step
	 */
	public int getStep() {
		return step;
	}
	
	/**
	 * @param step the step to set
	 */
	public void setStep(int step) {
		this.step = step;
	}

	/**
	 * @return the oldHand2
	 */
	public ArrayList<Card> getOldHand2() {
		return oldHand2;
	}

	/**
	 * @param oldHand2 the oldHand2 to set
	 */
	public void setOldHand2(ArrayList<Card> oldHand2) {
		this.oldHand2 = oldHand2;
	}

	/**
	 * @return the transfer
	 */
	public ArrayList<Card> getTransfer() {
		return transfer;
	}

	/**
	 * @param transfer the transfer to set
	 */
	public void setTransfer(ArrayList<Card> transfer) {
		this.transfer = transfer;
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
	 * @return the oldHand1
	 */
	public ArrayList<Card> getOldHand1() {
		return oldHand1;
	}

	/**
	 * @param oldHand1 the oldHand1 to set
	 */
	public void setOldHand1(ArrayList<Card> oldHand1) {
		this.oldHand1 = oldHand1;
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
	 * @return the possible
	 */
	public LinkedList<Move> getPossible() {
		return possible;
	}

	/**
	 * @param possible the possible to set
	 */
	public void setPossible(LinkedList<Move> possible) {
		this.possible = possible;
	}

	/**
	 * @return the goFish
	 */
	public boolean isGoFish() {
		return goFish;
	}

	/**
	 * @param goFish the goFish to set
	 */
	public void setGoFish(boolean goFish) {
		this.goFish = goFish;
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
	 * @return the goAgain
	 */
	public boolean isGoAgain() {
		return goAgain;
	}

	/**
	 * @param goAgain the goAgain to set
	 */
	public void setGoAgain(boolean goAgain) {
		this.goAgain = goAgain;
	}

	/**
	 * @return the requested
	 */
	public String getRequested() {
		return requested;
	}

	/**
	 * @param requested the requested to set
	 */
	public void setRequested(String requested) {
		this.requested = requested;
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

}
