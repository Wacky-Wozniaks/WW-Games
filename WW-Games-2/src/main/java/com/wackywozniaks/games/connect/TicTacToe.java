package com.wackywozniaks.games.connect;

import java.util.Observer;

import com.wackywozniaks.games.Move;
import com.wackywozniaks.games.Player;

/**
 * A game of tic tac toe.
 * 
 * @author WackyWozniaks Company
 * @version 04/07/2017
 */
public class TicTacToe extends Connect
{
	public TicTacToe(Player[] players, Observer o)
	{
		super(3, 3, 3, o, "Tic-Tac-Toe", players);
		
	}

	@Override
	public boolean doMove(Move m)
	{
		ConnectMove move = (ConnectMove) m;
		return insert(move.getRow(), move.getCol(), move.getPlayer());
	}
}