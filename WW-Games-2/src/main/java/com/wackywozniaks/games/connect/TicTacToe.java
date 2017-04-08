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
	public TicTacToe(Player p1, Player p2, Observer o)
	{
		super(3, 3, 3, o, "Tic-Tac-Toe", p1, p2);
		
	}

	@Override
	public boolean doMove(Move m)
	{
		ConnectMove move = (ConnectMove) m;
		return insert(move.getRow(), move.getCol(), move.getPlayer());
	}
}