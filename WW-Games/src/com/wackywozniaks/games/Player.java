package com.wackywozniaks.games;

public abstract class Player
{
	private String name;
	
	public abstract Move move(Game g);
}