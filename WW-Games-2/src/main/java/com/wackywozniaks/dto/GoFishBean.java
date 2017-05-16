package com.wackywozniaks.dto;

import java.util.ArrayList;
import java.util.LinkedList;

import com.wackywozniaks.games.cards.Card;

public class GoFishBean
{
	private int type;
	private ArrayList<Card> hand1, hand2;
	private LinkedList<Card> deck;
	private int[] books;
	private String move;
	
	public int getType()
	{
		return type;
	}

	public void setType(int type)
	{
		this.type = type;
	}

	public String getMove()
	{
		return move;
	}

	public void setMove(String move)
	{
		this.move = move;
	}
	
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
}