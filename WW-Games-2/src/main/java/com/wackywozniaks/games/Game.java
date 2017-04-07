package com.wackywozniaks.games;

import java.util.Observable;
import java.util.Observer;

public abstract class Game extends Observable
{
	private String name;
	
	protected Game(String name, Observer o)
	{
		this.name = name;
		addObserver(o);
	}
	
	public abstract boolean doMove(Move m);
	
	/**
	 * Determines if the game has reached its ending point.
	 * @return The winner, or null if the game is not over.
	 */
	public abstract Player gameOver();
}