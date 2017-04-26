package com.wackywozniaks.games.connect;

import java.util.LinkedList;

import com.wackywozniaks.games.Move;

/**
 * A game of Connect 4.
 * 
 * @author WackyWozniaks Company
 * @version 04/26/2017
 */
public class Connect4 extends Connect
{
	/**
	 * Constructor to be called from the front end after it implements its move.
	 * @param board The current state of the game.
	 */
	public Connect4(int[][] board)
	{
		super(7, 6, 4, "Connect4", board);
	}

	@Override
	public Connect4 doMove(Move m)
	{
		ConnectMove move = (ConnectMove) m;
		return drop(move.getCol(), move.getPlayer());
	}
	
	protected Connect4 drop(int col, int player)
	{
		int[][] board = getBoard();
		for(int r = board.length - 1; r >= 0; r--)
		{
			if(board[r][col] == EMPTY)
			{
				board[r][col] = player;
				return new Connect4(board);
			}
		}
		return null;
	}

	@Override
	public LinkedList<Move> getLegalActions(int player)
	{
		int[][] board = getBoard();
		LinkedList<Move> list = new LinkedList<Move>();
		for(int col = 0; col < board[0].length; col++)
		{
			for(int row = board.length - 1; row >= 0; row--)
			{
				if(board[row][col] == EMPTY)
				{
					list.add(new ConnectMove(row, col, player));
					break;
				}
			}
		}
		return list;
	}
}