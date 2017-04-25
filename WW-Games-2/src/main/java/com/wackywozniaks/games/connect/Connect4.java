package com.wackywozniaks.games.connect;

import java.util.LinkedList;

import com.wackywozniaks.games.Move;

/**
 * A game of Connect 4.
 * 
 * @author WackyWozniaks Company
 * @version 04/20/2017
 */
public class Connect4 extends Connect
{
	public Connect4()
	{
		super(7, 6, 4, "Connect4");
	}
	
	public Connect4(int[][] board)
	{
		super(7, 6, 4, "Connect4", board);
	}
	
	private Connect4(int[][] board, int count, int turn)
	{
		super(board, 4, "Connect4", count, turn);
	}

	@Override
	public Connect4 doMove(Move m)
	{
		ConnectMove move = (ConnectMove) m;
		return drop(move.getCol());
	}
	
	protected Connect4 drop(int col)
	{
		int[][] board = getBoard();
		for(int r = board.length - 1; r >= 0; r--)
		{
			if(board[r][col] == EMPTY)
			{
				board[r][col] = (getPlayerTurn() == 0 ? PLAYER1 : PLAYER2);
				return new Connect4(board, getCount() + 1, getPlayerTurn() + 1);
			}
		}
		return null;
	}

	@Override
	public LinkedList<Move> getLegalActions()
	{
		int[][] board = getBoard();
		LinkedList<Move> list = new LinkedList<Move>();
		for(int col = 0; col < board[0].length; col++)
		{
			for(int row = board.length - 1; row >= 0; row--)
			{
				if(board[row][col] == EMPTY)
				{
					list.add(new ConnectMove(row, col));
					break;
				}
			}
		}
		return list;
	}
}