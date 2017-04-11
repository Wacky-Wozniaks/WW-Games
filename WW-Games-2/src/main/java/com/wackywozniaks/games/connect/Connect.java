package com.wackywozniaks.games.connect;

import java.util.Arrays;
import java.util.Observer;

import com.wackywozniaks.games.Game;
import com.wackywozniaks.games.Player;

/**
 * An abstract framework for games like Connect4 and Tic Tac Toe.
 * 
 * @author WackyWozniaks Company
 * @version 04/11/2017
 */
public abstract class Connect extends Game
{
	public static final int EMPTY = 0, PLAYER1 = 1, PLAYER2 = 2;
	
	private int[][] board, highlight;
	private int connection;
	private int count;
	
	protected Connect(int length, int width, int connection, Observer o, String name, Player[] players)
	{
		super(name, o, players);
		board = new int[length][width];
		this.connection = connection;
		count = 0;
	}
	
	/**
	 * If the game has a winner, the highlight is all of the indices of the winning chain.
	 * @return The highlight, or null if the game isn't over.
	 */
	public int[][] getHighlight()
	{
		return highlight;
	}
	
	protected boolean insert(int row, int col, Player p)
	{
		if(board[row][col] != EMPTY) return false;
		board[row][col] = p == players[0] ? PLAYER1 : PLAYER2;
		setChanged();
		notifyObservers();
		count++;
		return true;
	}
	
	protected boolean drop(int col, Player p)
	{
		for(int r = board.length - 1; r >= 0; r--)
		{
			if(board[r][col] == EMPTY)
			{
				insert(r, col, p);
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
					
					boolean row = r <= board.length - connection, col = c <= board[0].length - connection, reverse = c >= connection - 1, gameOver = false;
					
					if(row && col)
					{
						highlight = new int[connection][2];
						for(int rt = r, ct = c, count = 0; count < connection; count++, rt++, ct++)
						{
							if(board[rt][ct] == i)
							{
								highlight[count][0] = rt;
								highlight[count][1] = ct;
							}
							else
							{
								highlight = null;
								break;
							}
						}
						if(highlight != null) gameOver = true;
					}
					if(!gameOver && row && reverse)
					{
						highlight = new int[connection][2];
						for(int rt = r, ct = c, count = 0; count < connection; count++, rt++, ct--)
						{
							if(board[rt][ct] == i)
							{
								highlight[count][0] = rt;
								highlight[count][1] = ct;
							}
							else
							{
								highlight = null;
								break;
							}
						}
						if(highlight != null) gameOver = true;
					}
					if(!gameOver && row)
					{
						highlight = new int[connection][2];
						for(int rt = r, count = 0; count < connection; count++, rt++)
						{
							if(board[rt][c] == i)
							{
								highlight[count][0] = rt;
								highlight[count][1] = c;
							}
							else
							{
								highlight = null;
								break;
							}
						}
						if(highlight != null) gameOver = true;
					}
					if(!gameOver && col)
					{
						highlight = new int[connection][2];
						for(int ct = c, count = 0; count < connection; count++, ct++)
						{
							if(board[r][ct] == i)
							{
								highlight[count][0] = r;
								highlight[count][1] = ct;
							}
							else
							{
								highlight = null;
								break;
							}
						}
						if(highlight != null) gameOver = true;
					}
					
					if(gameOver)
					{
						winner = i == PLAYER1 ? players[0] : players[1];
						setChanged();
						notifyObservers();
						return true;
					}
				}
			}
		}
		if(count == board.length * board[0].length) return true;
		else return false;
	}
	
	/**
	 * Makes a copy of the current configuration of the board.
	 * @return The current state of the board.
	 */
	public int[][] getBoard()
	{
		return Arrays.copyOf(board, board.length);
	}
}