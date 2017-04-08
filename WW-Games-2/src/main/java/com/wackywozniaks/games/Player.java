package com.wackywozniaks.games;

/**
 * An abstract instance of a player of a game.
 * 
 * @author WackyWozniaks Company
 * @version 04/07/2017
 */
public abstract class Player
{
	private String name;
	
	public abstract Move move(Game g);
}