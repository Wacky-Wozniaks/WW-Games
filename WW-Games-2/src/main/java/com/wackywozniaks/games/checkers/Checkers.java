package com.wackywozniaks.games.checkers;

import javax.swing.*;
import javax.swing.event.*;

import com.wackywozniaks.games.Game;
import com.wackywozniaks.games.Move;

import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

/**
 *
 * @author Hyun Choi, Ted Pyne, Patrick Forelli
 */
public class Checkers extends Game  {
	//keeps track of a Board, a 2d array of JLabels to represent each tile, and JPanel to store the tiles
	public Board board; 

	/*
	private JLabel[][] GUIboard;
	//JPanel entireGUI for the enclosure of both the board and the text
	private JPanel entireGUI;

	//outer JPanel panel for the outer board panel, boardGUI for the inner board panel
	private JPanel panel;
	private JPanel boardGUI;

	//JPanel for textual info; JLabels/JButton for information and toggling
	private JPanel text;
	GridBagConstraints c;
	private JLabel victoryStatus;
	private JLabel turnStatus;
	private JButton aiToggle;
	private JLabel aiDifficulty;
	private JButton newGame;
	private JLabel aiDepth;
	 */



	//AI implementation
	private MoveAI ai;
	private boolean aiActive;
	private JSlider difficulty;
	private JSlider lookAhead;

	private boolean selected = false; //if a piece is selected or not
	private int[][] currentSelected; //coordinates of the selected piece and the target area
	private final int MULTIPLIER = 62; //width of one tile

	/**
	 * Creates new form CheckersGUI
	 */
	public Checkers() {
		selected = false;
		board = new Board();
		
	}

	private void makeAllAIMoves(){
		if(ai!=null)
			while(!board.isWhiteTurn() && board.gameIsWon()==null){
				ai.makeMove();
				//renderBoard();
			}
	}

	private int[] pressed(MouseEvent e) //returns pixel coordinates where clicked
	{
		int[] coordinates = new int[2]; //[x,y]
		coordinates[0] = e.getX();
		coordinates[1] = e.getY();
		return coordinates;
	}

	private int[] arrayCoord(int[] pixelCoord) //returns coordinates within the checkerboard, limited to [0,0] to [7,7]
	{

		for (int i=0; i<2; i++)
			pixelCoord[i] /= MULTIPLIER;        //Divide the pixel by the width of each piece

		return pixelCoord;
	}

	/*
	private void move(int[][] currentSelected) //moves the pieces in the Board variable
	{
		board.makeMove(currentSelected[0][1],currentSelected[0][0],currentSelected[1][1],currentSelected[1][0]);
	}
	*/

	public static void main (String[] args) //Run the game!
	{
		CheckersGUI gui = new CheckersGUI();
		gui.renderBoard();
	}

	/* (non-Javadoc)
	 * @see com.wackywozniaks.games.Game#doMove(com.wackywozniaks.games.Move)
	 */
	@Override
	public Game doMove(Move m) {
		CheckersMove move = (CheckersMove) m;
		board.makeMove(move.getX(), move.getY(), move.getNewX(), move.getNewY());
		return this;
	}

	/* (non-Javadoc)
	 * @see com.wackywozniaks.games.Game#getLegalActions(int)
	 */
	@Override
	public LinkedList<Move> getLegalActions(int player) {
		return board.getValidMoves();
	}

	/* (non-Javadoc)
	 * @see com.wackywozniaks.games.Game#evaluate()
	 */
	@Override
	public int evaluate() {
		Piece won = board.gameIsWon();
		if (won == null)
			return 0;
		else if (won.getIsWhite())
			return Integer.MAX_VALUE;
		else
			return Integer.MIN_VALUE;
		// TODO improve
	}

	/* (non-Javadoc)
	 * @see com.wackywozniaks.games.Game#gameOver()
	 */
	@Override
	public boolean gameOver() {
		return (board.gameIsWon() != null);
	}

}
