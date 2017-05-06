package com.wackywozniaks.games.checkers;
import java.util.ArrayList;

import com.wackywozniaks.games.Move;


public class AI3 extends MoveAI{

	private static int baseRecur = 4;
	private double aggression;       //Higher values result in a more defensive AI
	private static final double[] aggrList = {.25,1.25,1,1.5,1.75,2.0,.5,.75};

	private ArrayList<CheckersMove> moves = new ArrayList<CheckersMove>();

	public AI3(Board board, boolean isWhite) {
		super(board, isWhite);
		aggression = 2;
	}

	public AI3(Board board){
		this(board,false);
	}

	public Move makeMove() {
		if(board.isWhiteTurn()!=isWhite) return null;
		Node<Board> moveTree = new Node<Board>(board);
		makeTree(baseRecur, moveTree);
		System.out.println("Done making tree!");
		setTreeVal(moveTree);
		Move move = chooseMove(moveTree).getBoard().getLastMove();
		board.makeMove(move);
		return move;
		
	}
	
	public void setTreeVal(Node<Board> moveTree){
		int size = moveTree.getChildNum();
		if(size==0){
			double score = moveTree.getBoard().getBoardScore(isWhite,aggression);
			moveTree.setScore(score);
		}
		else{
			double scoreAvg=0;
			double worst = Integer.MAX_VALUE;
			for(int i = 0; i < size; i++){
				Node<Board> child = moveTree.getChild(i);
				setTreeVal(child);
				
				double score = child.getScore();
				scoreAvg+= score;
				if(score<worst) worst = score;
			}
			
			moveTree.setScore((scoreAvg+worst)/(size+1));
		}
	}

	public Node<Board> chooseMove(Node<Board> moveTree) {
		int size = moveTree.getChildNum();
		if(size==0) return null;
		
		double highest = Integer.MIN_VALUE;
		Node<Board> best = null;
		
		for(int i = 0; i< size; i++){
			Node<Board> child = moveTree.getChild(i);
			double score = child.getScore();
			if(score > highest){
				best = child;
				highest = score;
			}
			System.out.println("Choice " + i + ":" + score);		
		}
		return best;
	}

	public void makeTree(int recurLeft, Node<Board> node){
		if(recurLeft==0){ 
		return;
		}
		
		addChildren(node);
		int size = node.getChildNum();
		for(int i = 0; i < size; i++)
			makeTree(recurLeft-1, node.getChild(i));
		return;
	}

	public boolean addChildren(Node<Board> node) {
		Board board = node.getBoard();
		moves = findPosMoves(board);
		if(moves.size()==0) return false;
		for(CheckersMove move: moves){
			Board testBoard = new Board(board);
			testBoard.makeMove(move);
			node.addChild(testBoard); 
		}
		return true;
	}

	private ArrayList<CheckersMove> findPosMoves(Board board){
		ArrayList<CheckersMove> moves = new ArrayList<CheckersMove>();
		for(int x = 0; x < 8; x++){                   //Find possible captures
			for(int y = 0; y < 8; y++){
				if(board.isValidSelection(x,y)){
					int[] newX = {x+2, x-2};        //Generate possible moves
					int[] newY = {y+2, y-2};
					for(int tryX: newX)
						for(int tryY: newY){
							if(validTarget(tryX, tryY)){
								Board newMove = new Board(board);
								if(newMove.makeMove(x,y,tryX,tryY)){
									moves.add(new CheckersMove(1, x,y,tryX,tryY));
								}
							}
						}
					newX[0] = x + 1; newX[1] = x - 1;
					newY[0] = y + 1; newY[1] = y - 1;
					for(int tryX: newX)
						for(int tryY: newY)
							if(validTarget(tryX, tryY)){
								Board newMove = new Board(board);
								if(newMove.makeMove(x,y,tryX,tryY)){ 
									moves.add(new CheckersMove(1, x,y,tryX,tryY));
								}
							}
				}
			}
		}
		return moves;
	}

	private boolean validTarget(int x, int y){      //Bounds checking for move and capture targets
		return x > -1 && x < 8 && y > -1 && y < 8;
	}

	public void setDifficulty(int difficulty) {
		aggression = aggrList[difficulty];
	}

	public void setAggression(double aggression){
		this.aggression = aggression;
	}
	
	public static void setRecur(int newDepth) {
		baseRecur = newDepth;
	}

}
