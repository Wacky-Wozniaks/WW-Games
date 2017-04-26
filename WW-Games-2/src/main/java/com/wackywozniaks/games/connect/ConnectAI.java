package com.wackywozniaks.games.connect;

import com.wackywozniaks.games.Game;
import com.wackywozniaks.games.PlayerAI;

/**
 * An AI for Connect games
 * 
 * @author WackyWozniaks Company
 * @version 04/26/2017
 */
public class ConnectAI extends PlayerAI
{
	public static ConnectMove chooseMove(Game g)
	{
		return (ConnectMove)minimax(g, 3);
	}
}