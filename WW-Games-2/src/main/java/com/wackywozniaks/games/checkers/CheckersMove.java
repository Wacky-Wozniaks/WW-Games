/**
 * Hyun Choi CS62 ASSIGNMENT NAME
 * Due DUEDATE
 * Discussion with 
 */
package com.wackywozniaks.games.checkers;

public class CheckersMove extends com.wackywozniaks.games.Move {
	private int x, y, newX, newY;
	
	
	/**
	 * @param player
	 */
	public CheckersMove(int player, int x, int y, int newX, int newY) {
		super(player); //0 is white, 1 is red
		this.x=x; this.y=y; this.newX = newX; this.newY = newY;
		// TODO Auto-generated constructor stub
	}
	
	
	public int getX(){return x;}
	public int getY(){ return y;}
	public int getNewX(){return newX;}
	public int getNewY(){return newY;}

}
