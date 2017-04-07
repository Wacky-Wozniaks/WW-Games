package com.wackywozniaks.games.connect;

import com.wackywozniaks.games.Move;
import com.wackywozniaks.games.Player;

public class ConnectMove extends Move
{
	private int row, col;
	
	public ConnectMove(Player p, int row, int col)
	{
		super(p);
		this.row = row;
		this.col = col;
	}
	
	public int getRow()
	{
		return row;
	}
	
	public int getCol()
	{
		return col;
	}
}