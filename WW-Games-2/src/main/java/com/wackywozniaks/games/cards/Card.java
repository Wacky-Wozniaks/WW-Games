package com.wackywozniaks.games.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * Represents a playing card, with methods to deal and sort hands.
 * 
 * @author WackyWozniaks Company
 * @version 05/02/2017
 */
public class Card implements Comparable<Card>
{
	public static final String[] SUITS = {"Clubs", "Diamonds", "Spades", "Hearts"};
	public static final String[] RANKS = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
	
	private int suit, rank;
	
	private Card(int suit, int rank)
	{
		this.suit = suit;
		this.rank = rank;
	}
	
	public String getSuit()
	{
		return SUITS[suit];
	}
	
	private int getSuitNum()
	{
		return suit;
	}
	
	public String getRank()
	{
		return RANKS[rank];
	}
	
	private int getRankNum()
	{
		return rank;
	}
	
	@Override
	public int compareTo(Card other)
	{
		if(other.getRankNum() < this.getRankNum()) return 1;
		else if(other.getRankNum() > this.getRankNum()) return -1;
		else return 0;
	}
	
	/**
	 * Creates a deck of cards, one with each suit and rank combination.
	 * @return The complete deck.
	 */
	public static LinkedList<Card> createDeck()
	{
		LinkedList<Card> deck = new LinkedList<Card>();
		for(int i = 0; i < SUITS.length; i++)
		{
			for(int j = 0; j < RANKS.length; j++)
			{
				deck.add(new Card(i, j));
			}
		}
		return deck;
	}
	
	/**
	 * Deals the given number of cards to two players, or as much as possible until the deck is empty.
	 * @param num The number of cards to give each players
	 * @return The players' hands
	 */
	public static ArrayList<Card>[] deal(int num, LinkedList<Card> deck)
	{
		ArrayList[] temp = {new ArrayList<Card>(), new ArrayList<Card>()};
		ArrayList<Card>[] hands = (ArrayList<Card>[])temp;
		
		for(int i = 0; i < num; i++)
		{
			if(!deck.isEmpty()) hands[0].add(deck.remove());
			else break;
			if(!deck.isEmpty()) hands[1].add(deck.remove());
			else break;
		}
		return hands;
	}
	
	/**
	 * Splits the cards into two hands, each with half of the deck.
	 * @param deck The deck to deal.
	 * @return The two halves of the deck.
	 */
	public static LinkedList<Card>[] split(LinkedList<Card> deck)
	{
		LinkedList[] temp = {new LinkedList<Card>(), new LinkedList<Card>()};
		LinkedList<Card>[] hands = (LinkedList<Card>[])temp;
		
		for(boolean first = true; !deck.isEmpty(); first = !first)
		{
			if(first) hands[0].add(deck.remove());
			else hands[1].add(deck.remove());
		}
		return hands;
	}
	
	/**
	 * Shuffles the cards in the deck into a random order.
	 * @param deck The deck to shuffle.
	 */
	public static void shuffle(LinkedList<Card> deck)
	{
		Collections.shuffle(deck);
	}
	
	/**
	 * Draws the next card on the top of the deck.
	 * @param deck The drawn card, or null if the deck is empty.
	 * @return
	 */
	public static Card draw(LinkedList<Card> deck)
	{
		if(deck.isEmpty()) return null;
		return deck.remove();
	}
	
	/**
	 * Adds the given card to the back of the deck.
	 * @param deck The deck to add to.
	 * @param card The card to add.
	 */
	public static void addCard(LinkedList<Card> deck, Card card)
	{
		deck.add(card);
	}
	
	/**
	 * Sorts the cards in the hand by their rank.
	 * @param hand The cards to sort.
	 */
	public static void sortRank(ArrayList<Card> hand)
	{
		Comparator<Card> c = new Comparator<Card>()
		{
			@Override
			public int compare(Card arg0, Card arg1)
			{
				if(arg0.getRankNum() > arg1.getRankNum()) return 1;
				else if(arg0.getRankNum() < arg1.getRankNum()) return -1;
				else return 0;
			}
		};
		hand.sort(c);
	}
	
	/**
	 * Checks whether the deck of cards is empty.
	 * @param deck The deck to check.
	 * @return True if the deck is empty, false otherwise.
	 */
	public static boolean deckEmpty(LinkedList<Card> deck)
	{
		return deck.isEmpty();
	}
}