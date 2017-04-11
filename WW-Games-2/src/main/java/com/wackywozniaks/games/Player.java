package com.wackywozniaks.games;

/**
 * An abstract instance of a player of a game.
 * 
 * @author WackyWozniaks Company
 * @version 04/11/2017
 */
public abstract class Player
{
	private String name;
	
	/**
	 * Creates a player with the given name.
	 * @param name The name of the player.
	 */
	public Player(String name)
	{
		this.name = name;
	}
	
	/**
	 * Returns the player's name.
	 * @return The name of this player.
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Determines the next move the player will take.
	 * @param g The game the player is in.
	 * @return The player's next move.
	 */
	public abstract Move move(Game g);
}