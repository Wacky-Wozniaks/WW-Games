package com.wackywozniaks.games.connect;

import com.wackywozniaks.games.Move;

/**
 * A move for a connect game, which has the index of the selected cell.
 * 
 * @author WackyWozniaks Company
 * @version 04/20/2017
 */
public class ConnectMove extends Move
{
	private int row, col;
	
	public ConnectMove(int row, int col)
	{
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