package com.wackywozniaks.games;

/**
 * An abstract instance of a player's move on a game.
 * 
 * @author WackyWozniaks Company
 * @version 04/07/2017
 */
public abstract class Move
{
	private Player p;
	
	public Move(Player p)
	{
		this.p = p;
	}
	
	public Player getPlayer()
	{
		return p;
	}
}