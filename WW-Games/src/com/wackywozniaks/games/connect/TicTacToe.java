package com.wackywozniaks.games.connect;

import com.wackywozniaks.games.Player;

public class TicTacToe extends Connect
{
	public TicTacToe(Player p1, Player p2)
	{
		super(3, 3, 3, p1, p2);
		
	}

	@Override
	public boolean update(int row, int col)
	{
		return insert(row, col);
	}
}