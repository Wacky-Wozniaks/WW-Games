package com.wackywozniaks.games.connect;

import com.wackywozniaks.games.Game;
import com.wackywozniaks.games.Player;

public abstract class Connect extends Game
{
	private static final int EMPTY = 0, PLAYER1 = 1, PLAYER2 = 2;
	
	private int[][] board;
	private Player p1, p2;
	private int connection;
	private boolean turn1;
	
	protected Connect(int length, int width, int connection, Player p1, Player p2)
	{
		board = new int[length][width];
		this.connection = connection;
		this.p1 = p1;
		this.p2 = p2;
		turn1 = true;
	}

	@Override
	public void play()
	{
		if(turn1) p1.move(this);
		else p2.move(this);
	}
	
	/**
	 * 
	 * @param row The selected row
	 * @param col The selected column
	 */
	public abstract boolean update(int row, int col);
	
	protected boolean insert(int row, int col)
	{
		if(board[row][col] != EMPTY) return false;
		board[row][col] = turn1 ? PLAYER1 : PLAYER2;
		return true;
	}
	
	protected boolean drop(int col)
	{
		for(int r = board.length - 1; r >= 0; r++)
		{
			if(board[r][col] != EMPTY)
			{
				insert(r, col);
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean gameOver()
	{
		for(int r = 0; r < board.length; r++)
		{
			for(int c = 0; c < board[0].length; c++)
			{
				if(board[r][c] != EMPTY)
				{
					int i = board[r][c];
					boolean row =  r < board.length - connection, col = c < board[0].length - connection, complete = false;;
					
					if(row && col)
					{
						for(int rt = r, ct = c, count = 0; count < connection; count++, rt++, ct++)
						{
							if(board[rt][ct] != i) break;
						}
					}
					else if(row)
					{
						for(int rt = r, count = 0; count < connection; count++, rt++)
						{
							if(board[rt][c] != i) break;
						}
					}
					else if(col)
					{
						for(int ct = c, count = 0; count < connection; count++, ct++)
						{
							if(board[r][ct] != i) break;
						}
					}
					
					if(complete)
					{
						winner = i == PLAYER1 ? p1 : p2;
						return true;
					}
				}
			}
		}
		return false;
	}	
}