package com.wackywozniaks.games.connect;

import java.util.Observer;

import com.wackywozniaks.games.Move;
import com.wackywozniaks.games.Player;

/**
 * A game of tic tac toe.
 * 
 * @author WackyWozniaks Company
 * @version 04/11/2017
 */
public class TicTacToe extends Connect
{
	public static final int LENGTH = 3, WIDTH = 3, CONNECTION = 3, NUM_PLAYERS = 2;
	
	public TicTacToe()
	{
		super(LENGTH, WIDTH, CONNECTION, "Tic Tac Toe");
		
	}
	
	private TicTacToe(int[][] board, int count, int numPlayer)
	{
		super(board, CONNECTION, "Tic Tac Toe", count, numPlayer);
	}

	@Override
	public TicTacToe doMove(Move m)
	{
		ConnectMove move = (ConnectMove) m;
		
		//return insert(move.getRow(), move.getCol(), move.getPlayer());
		return null;
	}
	
	protected boolean insert(int row, int col)
	{
		if(board[row][col] != EMPTY) return false;
		board[row][col] = p == players[0] ? PLAYER1 : PLAYER2;
		count++;
		return true;
	}
}