package com.wackywozniaks.games.connect;

import java.util.Observer;

import com.wackywozniaks.games.Move;
import com.wackywozniaks.games.Player;

/**
 * A game of Connect 4.
 * 
 * @author WackyWozniaks Company
 * @version 04/07/2017
 */
public class Connect4 extends Connect
{
	public Connect4(Player[] players, Observer o)
	{
		super(7, 6, 4, o, "Connect 4", players);
	}

	@Override
	public boolean doMove(Move m)
	{
		ConnectMove move = (ConnectMove) m;
		return drop(move.getCol(), move.getPlayer());
	}
}