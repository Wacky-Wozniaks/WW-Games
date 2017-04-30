package com.wackywozniaks.games.cards;

import java.util.LinkedList;

/**
 * Represents the card game War.
 * 
 * @author WackyWozniaks Company
 * @version 04/30/2017
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
		Object[] newCards = new Object[cards.length + 4];
		for(int i = 0; i < (cards.length - 1) / 2; i++)
		{
			newCards[i]= cards[i];
		}
		newCards[(cards.length - 1) / 2] = Card.draw(hands[0]);
		newCards[(cards.length - 1) / 2 + 1] = Card.draw(hands[0]);
		for(int i = (cards.length - 1) / 2, j = i + 2; i < cards.length - 1; i++, j++)
		{
			newCards[j] = cards[i];
		}
		newCards[cards.length + 1] = Card.draw(hands[1]);
		newCards[cards.length + 2] = Card.draw(hands[1]);
		
		int comp = ((Card)newCards[(cards.length - 1) / 2 + 1]).compareTo((Card)newCards[cards.length]);
		if(comp == 0) newCards[newCards.length - 1] = DRAW;
		else if(comp > 0) newCards[newCards.length - 1] = PLAYER1;
		else newCards[newCards.length - 1] = PLAYER2;
		return newCards;
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