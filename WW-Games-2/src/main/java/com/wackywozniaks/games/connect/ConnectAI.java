package com.wackywozniaks.games.connect;

import com.wackywozniaks.games.Game;
import com.wackywozniaks.games.PlayerAI;

/**
 * An AI for Connect games
 * 
 * @author WackyWozniaks Company
 * @version 04/29/2017
 */
public class ConnectAI extends PlayerAI
{
	public static final int RANDOM = 0, MINIMAX = 1;
	
	public static ConnectMove chooseMove(Game g, int difficulty)
	{
		if(difficulty == RANDOM) return (ConnectMove)random(g);
		else if(difficulty == MINIMAX) return (ConnectMove)minimax(g, 3);
		else return null;
	}
}