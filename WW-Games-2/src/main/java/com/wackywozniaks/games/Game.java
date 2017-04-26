package com.wackywozniaks.games;

import java.util.LinkedList;

/**
 * An abstract instance of a game.
 * 
 * @author WackyWozniaks Company
 * @version 04/26/2017
 */
public abstract class Game
{
	protected int winner;
	
	/**
	 * Updates the game based on the given move. Should only be called by the AI.
	 * @param m The move made.
	 * @return The new state of the game, or null if the move was unsuccessful.
	 */
	public abstract Game doMove(Move m);
	
	public abstract LinkedList<Move> getLegalActions();
	
	public abstract int evaluate();
	
	/**
	 * Determines if the game has reached its ending point.
	 * @return Whether the game is over.
	 */
	public abstract boolean gameOver();
	
	/**
	 * Returns the winner of the game, or null if the game is not over or ended in a tie.
	 * @return The winner
	 */
	public int getWinner()
	{
		return winner;
	}
}