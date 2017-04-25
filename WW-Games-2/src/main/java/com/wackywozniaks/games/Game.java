package com.wackywozniaks.games;

import java.util.LinkedList;

/**
 * An abstract instance of a game.
 * 
 * @author WackyWozniaks Company
 * @version 04/20/2017
 */
public abstract class Game
{
	private String name;
	protected int winner;
	protected int numPlayers, playerTurn;
	
	protected Game(String name, int numPlayers, int playerTurn)
	{
		this.name = name;
		this.numPlayers = numPlayers;
		if(playerTurn >= numPlayers) playerTurn %= numPlayers;
		this.playerTurn = playerTurn;
	}
	
	/**
	 * Returns the game's name.
	 * @return The name of the game.
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Returns the number of players in the game.
	 * @return The number of players
	 */
	public int getNumPlayers()
	{
		return numPlayers;
	}
	
	/**
	 * Updates the game based on the given move.
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
	
	protected int getPlayerTurn()
	{
		return playerTurn;
	}
}