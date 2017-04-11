package com.wackywozniaks.games;

import java.util.Observable;
import java.util.Observer;

/**
 * An abstract instance of a game.
 * 
 * @author WackyWozniaks Company
 * @version 04/11/2017
 */
public abstract class Game extends Observable
{
	private String name;
	protected Player[] players;
	protected Player winner;
	
	protected Game(String name, Observer o, Player[] players)
	{
		this.name = name;
		addObserver(o);
		this.players = players;
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
		return players.length;
	}
	
	/**
	 * Updates the game based on the given move.
	 * @param m The move made.
	 * @return If the move was successful.
	 */
	public abstract boolean doMove(Move m);
	
	/**
	 * Determines if the game has reached its ending point.
	 * @return Whether the game is over.
	 */
	public abstract boolean gameOver();
	
	/**
	 * Returns the winner of the game, or null if the game is not over or ended in a tie.
	 * @return The winner
	 */
	public Player getWinner()
	{
		return winner;
	}
}