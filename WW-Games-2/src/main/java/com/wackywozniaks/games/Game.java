package com.wackywozniaks.games;

import java.util.Observable;
import java.util.Observer;

/**
 * An abstract instance of a game.
 * 
 * @author WackyWozniaks Company
 * @version 04/07/2017
 */
public abstract class Game extends Observable
{
	public static final int NOT_OVER = -1, TIE = 0, WIN1 = 1, WIN2 = 2;
	private String name;
	
	protected Game(String name, Observer o)
	{
		this.name = name;
		addObserver(o);
	}
	
	public abstract boolean doMove(Move m);
	
	/**
	 * Determines if the game has reached its ending point.
	 * @return One of the integer constants representing the state of the game.
	 */
	public abstract int gameOver();
}