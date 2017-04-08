package com.wackywozniaks.games.connect;

import java.util.Scanner;

import com.wackywozniaks.games.Game;
import com.wackywozniaks.games.Player;

/**
 * A player for a connect game.
 * 
 * @author WackyWozniaks Company
 * @version 04/07/2017
 */
public class ConnectPlayer extends Player
{

	@Override
	public ConnectMove move(Game g)
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter your next move.");
		int row = scan.nextInt();
		int col = scan.nextInt();
		ConnectMove m = new ConnectMove(this, row, col);
		return m;
	}
}