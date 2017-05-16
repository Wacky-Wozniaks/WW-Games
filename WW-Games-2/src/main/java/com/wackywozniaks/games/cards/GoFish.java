package com.wackywozniaks.games.cards;

import java.util.ArrayList;
import java.util.LinkedList;

import com.wackywozniaks.games.Move;

/**
 * The game Go Fish.
 * 
 * @author WackyWozniaks Company
 * @version 05/14/2017
 */
public class GoFish
{
	private LinkedList<Card> deck;
	private ArrayList<Card>[] hands;
	private int[] books;
	private int winner;
	private ArrayList<Card> transfer;
	
	/**
	 * Creates a new game of go fish.
	 */
	public GoFish()
	{
		deck = Card.createDeck();
		Card.shuffle(deck);
		hands = Card.deal(7, deck);
		Card.sortRank(hands[0]);
		Card.sortRank(hands[1]);
		books = new int[2];
	}
	
	/**
	 * Creates an already begun game of go fish.
	 * @param deck The current deck.
	 * @param hand1 Player 1's current hand.
	 * @param hand2 Player 2's current hand.
	 * @param books The number of books each player has.
	 */
	public GoFish(LinkedList<Card> deck, ArrayList<Card> hand1, ArrayList<Card> hand2, int[] books)
	{
		this.deck = deck;
		this.hands = (ArrayList<Card>[])(new ArrayList[]{hand1, hand2});
		this.books = books;
	}
	
	/**
	 * Determines whether the asked player has the requested card.
	 * @param m The move made.
	 * @return True if the player has the card and must give up their cards, false if the other player must go fish.
	 */
	public boolean otherHas(GoFishMove m)
	{
		for(Card c: hands[1 - m.getPlayer()])
		{
			if(c.getRankText().equals(m.getVal())) return true;
		}
		return false;
	}
	
	/**
	 * Draws a card from the deck and puts it in the player's hand.
	 * @param player The player drawing a card.
	 * @param m The move the player had made
	 * @return Whether the player draws the card they had requested.
	 */
	public boolean goFish(int player, GoFishMove m)
	{
		player--;
		Card c = Card.draw(deck);
		transfer = new ArrayList<Card>();
		transfer.add(c);
		hands[player].add(c);
		Card.sortRank(hands[player]);
		if(c.getRankText().equals(m.getVal())) return true;
		else return false;
	}
	
	/**
	 * Moves the cards of the right rank from one player to another.
	 * @param receiving The player getting the cards.
	 * @param m The move made.
	 * @return Whether a book was formed.
	 */
	public void transfer(int receiving, GoFishMove m)
	{
		receiving--;
		transfer = new ArrayList<Card>();
		for(int i = 0; i < hands[1 - receiving].size(); i++)
		{
			Card c = hands[1 - receiving].get(i);
			if(c.getRankText().equals(m.getVal()))
			{
				hands[1 - receiving].remove(i);
				i--;
				hands[receiving].add(c);
				transfer.add(c);
			}
		}
		Card.sortRank(hands[receiving]);
	}
	
	/**
	 * Removes the cards that form a book.
	 * @param player The player to form a book for.
	 */
	public void formBook(int player)
	{
		player--;
		if(hands[player].size() == 0) return;
		String val = hands[player].get(0).getRankText();
		for(int i = 0, count = 0; i < hands[player].size(); i++)
		{
			if(!val.equals(hands[player].get(i).getRankText()))
			{
				count = 0;
				if(i < hands[player].size() - 1) val = hands[player].get(i + 1).getRankText();
			}
			else
			{
				count++;
				if(count == Card.SUITS.length)
				{
					for(int j = 0; j < Card.SUITS.length; j++)
					{
						hands[player].remove(i - Card.SUITS.length + 1);
					}
					books[player]++;
					return;
				}
			}
		}
	}
	
	/**
	 * If the player's hand is empty, adds a card to the hand.
	 * @param player The player to add cards to.
	 * @return True if the hand was empty before adding a card, false otherwise.
	 */
	public boolean wasEmpty(int player)
	{
		player--;
		if(hands[player].isEmpty())
		{
			hands[player].add(Card.draw(deck));
			return true;
		}
		else return false;
	}
	
	/**
	 * Returns the list of ranks the player can request.
	 * @param player The player moving.
	 * @return A list of the moves the player can make.
	 */
	public LinkedList<Move> getLegalActions(int player)
	{
		player--;
		LinkedList<Move> list = new LinkedList<Move>();
		if(hands[player].size() == 0) return list;
		
		String last = hands[player].get(0).getRankText();
		int count = 0;
		for(int i = 0; i < hands[player].size(); i++)
		{
			String rank = hands[player].get(0).getRankText();
			if(!last.equals(rank))
			{
				list.add(new GoFishMove(player, last, count));
				count = 1;
				last = rank;
			}
			else count++;
		}
		list.add(new GoFishMove(player, last, count));
		return list;
	}
	
	/**
	 * Returns a list of the cards transferred from one hand to the other in the most recent move.
	 * @return The transferred cards.
	 */
	public ArrayList<Card> getTransfer()
	{
		return transfer;
	}
	
	/**
	 * Returns the current state of the deck.
	 * @return The deck
	 */
	public LinkedList<Card> getDeck()
	{
		return deck;
	}
	
	/**
	 * Returns player 1's hand.
	 * @return Player 1's hand
	 */
	public ArrayList<Card> getHand1()
	{
		return hands[0];
	}
	
	/**
	 * Returns player 2's hand.
	 * @return Player 2's hand
	 */
	public ArrayList<Card> getHand2()
	{
		return hands[1];
	}
	
	/**
	 * Returns how many books each player has.
	 * @return The number of books.
	 */
	public int[] getBooks()
	{
		return books;
	}

	/**
	 * Determines whether the game is over.
	 * @return True if the game is over, false otherwise.
	 */
	public boolean gameOver()
	{
		if(books[0] + books[1] == Card.RANKS.length)
		{
			winner = books[0] > books[1] ? 1 : books[0] < books[1] ? 2 : 0;
			return true;
		}
		return false;
	}
	
	/**
	 * Returns the winner of the game.
	 * @return 1 if the player won, 2 if the computer won, or 0 if they tied or the game is still ongoing.
	 */
	public int getWinner()
	{
		return winner;
	}
}