package com.wackywozniaks.games.cards;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Represents the card game War.
 * 
 * @author WackyWozniaks Company
 * @version 05/02/2017
 */
public class War
{
	public static final int PLAYER1 = 1, PLAYER2 = 2, DRAW = 0;
	
	private LinkedList<Card>[] hands;
	
	/**
	 * Creates a new game of war.
	 */
	public War()
	{
		LinkedList<Card> deck = Card.createDeck();
		Card.shuffle(deck);
		hands = Card.split(deck);
	}
	
	/**
	 * Draws the next card from each deck
	 * @return an array with three elements: the card drawn by player 1, the card drawn by player 2, and an integer constant representing which player won the cards.
	 */
	public Object[] next()
	{
		Card c1 = Card.draw(hands[0]), c2 = Card.draw(hands[1]);
		int comp = c1.compareTo(c2);
		if(comp > 0)
		{
			Card.addCard(hands[0], c1);
			Card.addCard(hands[0], c2);
			return new Object[]{c1, c2, PLAYER1};
		}
		else if(comp < 0)
		{
			Card.addCard(hands[1], c1);
			Card.addCard(hands[1], c2);
			return new Object[]{c1, c2, PLAYER2};
		}
		else return new Object[]{c1, c2, DRAW};
	}
	
	/**
	 * Draws two more cards from each hand in a war.
	 * @param cards The set of cards returned by next or war.
	 * @return The new set of cards
	 */
	public Object[] war(Object[] cards)
	{
		Object[] newCards = {new ArrayList<Card>(), new ArrayList<Card>(), null};
		((ArrayList<Card>)(newCards[0])).add((Card)cards[0]);
		((ArrayList<Card>)(newCards[1])).add((Card)cards[1]);
		return continueWar(newCards);
	}
	
	/**
	 * Continues an already begun war.
	 * @param cards Two array lists of cards and an integer value which is not relevant to the method.
	 * @return The same array as passed in, but with cards added and the integer representing the winner of the war.
	 */
	public Object[] continueWar(Object[] cards)
	{
		((ArrayList<Card>)(cards[0])).add(Card.draw(hands[0]));
		((ArrayList<Card>)(cards[1])).add(Card.draw(hands[1]));
		Card c1 = Card.draw(hands[0]), c2 = Card.draw(hands[1]);
		int won;
		if(c1 == null) won = PLAYER1;
		else if(c2 == null) won = PLAYER2;
		else
		{
			((ArrayList<Card>)(cards[0])).add(c1);
			((ArrayList<Card>)(cards[1])).add(c2);
			int comp = c1.compareTo(c2);
			won = comp > 0 ? PLAYER1 : comp < 0 ? PLAYER2 : DRAW;
		}
		for(int i = 0; i < 2; i++)
		{
			ArrayList<Card> stack = (ArrayList<Card>)cards[i];
			for(Card c: stack) Card.addCard(hands[won - 1], c);
		}
		cards[2] = won;
		return cards;
	}
	
	/**
	 * Returns whether the game is over.
	 * @return If the game is over.
	 */
	public boolean gameOver()
	{
		return Card.deckEmpty(hands[0]) || Card.deckEmpty(hands[1]);
	}
	
	/**
	 * Returns the winner, or 0 if the game is not over.
	 * @return 1 if player 1 has won, 2 if player 2 has won, or 0 is the game is not over.
	 */
	public int getWinner()
	{
		if(Card.deckEmpty(hands[0])) return 1;
		else if(Card.deckEmpty(hands[1])) return 2;
		else return 0;
	}
}