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
	public Connect4(Player p1, Player p2, Observer o)
	{
		super(7, 6, 4, o, "Connect 4", p1, p2);
	}

	@Override
	public boolean doMove(Move m)
	{
		ConnectMove move = (ConnectMove) m;
		return drop(move.getCol(), move.getPlayer());
	}
}