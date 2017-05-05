package com.wackywozniaks.games.cards;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Represents the card game War.
 * 
 * @author WackyWozniaks Company
 * @version 05/05/2017
 */
public class War
{
	public static final int PLAYER1 = 1, PLAYER2 = 2, DRAW = 0;
	
	private LinkedList<Card>[] hands;
	
	/**
	 * Makes the game with the already existing hands, or a new game if the hands are null;
	 * @param player1 Player 1's hand
	 * @param player2 Player 2's hand
	 */
	public War(LinkedList<Card> player1, LinkedList<Card> player2)
	{
		if(player1 == null)
		{
			LinkedList<Card> deck = Card.createDeck();
			Card.shuffle(deck);
			hands = Card.split(deck);
		}
		else
		{
			hands = (LinkedList<Card>[])new LinkedList[2];
			hands[0] = player1;
			hands[1] = player2;
		}
	}
	
	/**
	 * Draws the next card from each deck
	 * @return an array with three elements: the card drawn by player 1, the card drawn by player 2, and an integer constant representing which player won the cards.
	 */
	public Object[] next()
	{
		ArrayList<Card> a1 = new ArrayList<Card>(), a2 = new ArrayList<Card>();
		a1.add(Card.draw(hands[0]));
		a2.add(Card.draw(hands[1]));
		int comp = a1.get(0).compareTo(a2.get(0));
		if(comp > 0)
		{
			Card.addCard(hands[0], a1.get(0));
			Card.addCard(hands[0], a2.get(0));
			return new Object[]{a1, a2, PLAYER1};
		}
		else if(comp < 0)
		{
			Card.addCard(hands[1], a1.get(0));
			Card.addCard(hands[1], a2.get(0));
			return new Object[]{a1, a2, PLAYER2};
		}
		else return new Object[]{a1, a2, DRAW};
	}
	
	/**
	 * Draws two more cards from each hand in a war.
	 * @param cards The set of cards returned by next or war.
	 * @return The new set of cards
	 */
	public Object[] war(Object[] cards)
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
	 * Returns how many cards each player has in their hand.
	 * @return A 2 cell array with the number of cards each player has.
	 */
	public int[] getCounts()
	{
		return new int[]{hands[0].size(), hands[1].size()};
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