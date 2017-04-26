package com.wackywozniaks.games;

/**
 * An abstract instance of a player's move on a game.
 * 
 * @author WackyWozniaks Company
 * @version 04/07/2017
 */
public abstract class Move
{
	private int player;
	
	public Move(int player)
	{
		this.player = player;
	}
	
	public int getPlayer()
	{
		return player;
	}
}