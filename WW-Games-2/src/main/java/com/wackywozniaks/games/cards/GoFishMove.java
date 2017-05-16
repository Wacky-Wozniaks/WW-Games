package com.wackywozniaks.games.cards;

import com.wackywozniaks.games.Move;

/**
 * A move for the game Go Fish.
 * 
 * @author WackyWozniaks Company
 * @version 05/04/2017
 */
public class GoFishMove extends Move
{
	private String val;
	private int count;
	
	/**
	 * Constructs a new move with the player number and the value of the cards the player is requesting.
	 * @param player The player asking for cards.
	 * @param val The value of the cards requested.
	 */
	public GoFishMove(int player, String val, int count)
	{
		super(player);
		this.val = val;
		this.count = count;
	}
	
	/**
	 * Returns the value requested in this move.
	 * @return The card value.
	 */
	public String getVal()
	{
		return val;
	}
	
	/**
	 * Returns the number of this card already in this player's hand.
	 * @return The count of these cards.
	 */
	public int getCount()
	{
		return count;
	}
}