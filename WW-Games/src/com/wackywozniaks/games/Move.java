package com.wackywozniaks.games;

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