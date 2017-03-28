package com.wackywozniaks.games.connect;

import com.wackywozniaks.games.Player;

public class Connect4 extends Connect
{	
	protected Connect4(Player p1, Player p2)
	{
		super(7, 6, 4, p1, p2);
	}

	@Override
	public boolean update(int row, int col)
	{
		return drop(row);
	}	
}