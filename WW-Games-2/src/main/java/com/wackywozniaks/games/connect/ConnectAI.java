package com.wackywozniaks.games.connect;

import com.wackywozniaks.games.Game;
import com.wackywozniaks.games.Move;
import com.wackywozniaks.games.PlayerAI;

/**
 * An AI for Connect games
 * 
 * @author WackyWozniaks Company
 * @version 04/20/2017
 */
public class ConnectAI extends PlayerAI
{
	public ConnectAI()
	{
		super(9);
	}

	@Override
	public Move chooseMove(Game g)
	{
		return minimax(g);
	}
}