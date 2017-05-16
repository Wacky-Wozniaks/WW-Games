package com.wackywozniaks.dto;

import java.util.ArrayList;
import java.util.LinkedList;

import com.wackywozniaks.games.Move;
import com.wackywozniaks.games.cards.Card;

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
	
	public ArrayList<Card> getHand1()
	{
		return hand1;
	}
	
	public void setHand1(ArrayList<Card> hand1)
	{
		this.hand1 = hand1;
	}
	
	public ArrayList<Card> getHand2()
	{
		return hand2;
	}
	
	public void setHand2(ArrayList<Card> hand2)
	{
		this.hand2 = hand2;
	}
	
	public LinkedList<Card> getDeck()
	{
		return deck;
	}
	
	public void setDeck(LinkedList<Card> deck)
	{
		this.deck = deck;
	}
	
	public int[] getBooks()
	{
		return books;
	}
	
	public void setBooks(int[] books)
	{
		this.books = books;
	}

	public int getStep()
	{
		return step;
	}

	public void setStep(int step)
	{
		this.step = step;
	}

	public ArrayList<Card> getOldHand1()
	{
		return oldHand1;
	}

	public void setOldHand1(ArrayList<Card> oldHand1)
	{
		this.oldHand1 = oldHand1;
	}

	public ArrayList<Card> getOldHand2()
	{
		return oldHand2;
	}

	public void setOldHand2(ArrayList<Card> oldHand2)
	{
		this.oldHand2 = oldHand2;
	}

	public ArrayList<Card> getTransfer()
	{
		return transfer;
	}

	public void setTransfer(ArrayList<Card> transfer)
	{
		this.transfer = transfer;
	}

	public LinkedList<Move> getPossible()
	{
		return possible;
	}

	public void setPossible(LinkedList<Move> possible)
	{
		this.possible = possible;
	}

	public boolean isGoFish()
	{
		return goFish;
	}

	public void setGoFish(boolean goFish)
	{
		this.goFish = goFish;
	}

	public boolean isGoAgain()
	{
		return goAgain;
	}

	public void setGoAgain(boolean goAgain)
	{
		this.goAgain = goAgain;
	}

	public String getRequested()
	{
		return requested;
	}

	public void setRequested(String requested)
	{
		this.requested = requested;
	}

	public boolean isGameOver()
	{
		return gameOver;
	}

	public void setGameOver(boolean gameOver)
	{
		this.gameOver = gameOver;
	}

	public int getWinner()
	{
		return winner;
	}

	public void setWinner(int winner)
	{
		this.winner = winner;
	}
}