package com.wackywozniaks.games.checkers;

import com.wackywozniaks.games.Game;
import com.wackywozniaks.games.PlayerAI;

/**
 * The AI for Checkers
 * 
 * @author WackyWozniaks Company
 * @version 05/17/2017
 */
public class CheckersAI extends PlayerAI {
	
	public static final int MINIMAX1 = 0;
	public static final int MINIMAX2 = 1;
	public static final int MINIMAX3 = 2;
	
	public static CheckersMove chooseMove(Game g, int difficulty){
		if(difficulty == MINIMAX1) return (CheckersMove)minimax(g, 1);
		else if(difficulty == MINIMAX2) return (CheckersMove)minimax(g, 2);
		else if(difficulty == MINIMAX3) return (CheckersMove)minimax(g, 4);
		else return null;
	}

}
