package com.wackywozniaks.games;

import java.util.Observable;

public abstract class Game extends Observable
{
	protected Player winner;
	
	/**
	 * Calls on the next player to take their turn. Should only be called if gameOver returns false.
	 */
	public abstract void play();
	
	/**
	 * Determines if the game has reached its ending point.
	 * @return If the game is over.
	 */
	public abstract boolean gameOver();
	
	/**
	 * Determines who has won the game. Should only be called if gameOver returns true.
	 * @return The winner of the game, or null if there isn't one.
	 */
	public Player winner()
	{
		return winner;
	}
}