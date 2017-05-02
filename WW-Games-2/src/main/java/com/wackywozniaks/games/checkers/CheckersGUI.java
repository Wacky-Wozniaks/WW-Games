package com.wackywozniaks.games.checkers;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author Hyun Choi, Ted Pyne, Patrick Forelli
 */
public class CheckersGUI extends javax.swing.JFrame  {
    //keeps track of a Board, a 2d array of JLabels to represent each tile, and JPanel to store the tiles
    public Board board; 
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
    public CheckersGUI() {
    	selected = false;
        board = new Board();
        GUIboard = new JLabel[8][8];
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                //if(board.getPiece(i,j) != null)
                GUIboard[i][j] = new JLabel();

            }
        }

        entireGUI = new JPanel(); //outer JPanel to store the boardGUI and the textual information
        entireGUI.setLayout(new BoxLayout(entireGUI, BoxLayout.X_AXIS));

        aiActive = false; //by default, AI is inactive

        text = new JPanel(); //inner JPanel to hold textual information
        text.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        
        initializeBoardGUI(); //initalizes board side of gui
        
        initializeText(); //initializes text side of gui
        currentSelected = new int[2][2];
        
        panel = new JPanel(); //enclose GridLayout within JPanel on the JFrame
        panel.add(boardGUI);
        renderBoard(); //render board on the GUI

    }

    public void renderBoard() //method to arrange images to form the board
    {

        boolean previousColorIsWhite = false; //for arrangement

        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                if (board.getPiece(i,j) != null)    //Get the piece at that space in the board
                {
                    if (board.getPiece(i,j).getIsWhite())//if the piece is white
                    {
                        if (board.getPiece(i,j).getIsKing())
                            GUIboard[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/whitewithwhiteking.png")));
                        else 
                            GUIboard[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/whitewithwhite.png")));

                    }
                    else //so that means it's a red
                    {
                        if (board.getPiece(i,j).getIsKing())
                            GUIboard[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/whitewithredking.png")));
                        else 
                            GUIboard[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/whitewithred.png")));
                    }

                    previousColorIsWhite=true;
                }
                else //if no piece, then blank tile (white or green)
                {
                    if (previousColorIsWhite)
                        GUIboard[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/greentile.png")));
                    else
                        GUIboard[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/whitetile.png")));

                    previousColorIsWhite = !previousColorIsWhite;
                }
                boardGUI.add(GUIboard[i][j]);
            }
            previousColorIsWhite=!previousColorIsWhite;
        }

        refreshText(); //update the text fields
        //combine the two components of the GUI
        entireGUI.add(panel);
        entireGUI.add(text);

        setResizable(false); //window cannot be resized

        //make it visible
        pack();
        this.setContentPane(entireGUI);
        setVisible(true);
    }
    
    public void initializeBoard()
    {
        board = new Board();
        if(ai!=null) ai = new AI(board);
        GUIboard = new JLabel[8][8];
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                //if(board.getPiece(i,j) != null)
                GUIboard[i][j] = new JLabel();

            }
        }
        initializeBoardGUI();
        
        entireGUI.remove(panel);
        
        panel = new JPanel();
        panel.add(boardGUI);
        renderBoard();
    }
    
    
    public void initializeBoardGUI()
    {
        boardGUI = new JPanel();
        boardGUI.setLayout(new GridLayout(8,8)); //tiles in a GridLayout of 8x8
        boardGUI.addMouseListener(new MouseAdapter() { //essence of the GUI's click detection

                public void mouseClicked(MouseEvent e) {
                	if (!selected) //if nothing is selected
                    {
                        currentSelected[0]=arrayCoord(pressed(e)); //store coordinates of the press in array
                        selected = true;
                        //if invalid selection, revert
                        if(!board.isValidSelection(currentSelected[0][1], currentSelected[0][0])){
                            currentSelected = new int[2][2];
                            selected=false;
                        }
                        else {
                            //If a valid selection has been made, highlight the piece to the user
                            int i = currentSelected[0][1]; 
                            int j = currentSelected[0][0];
                            if (board.getPiece(i,j).getIsWhite())//if the piece is white
                            {
                                if (board.getPiece(i,j).getIsKing())
                                    GUIboard[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/whitewithwhitekingselected.png")));
                                else 
                                    GUIboard[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/whitewithwhiteselected.png")));

                            }
                            else //so that means it's a red
                            {
                                if (board.getPiece(i,j).getIsKing())
                                    GUIboard[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/whitewithredkingselected.png")));
                                else 
                                    GUIboard[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/whitewithredselected.png")));
                            }  
                        }
                    }
                    else if (selected) //Target tile
                    {
                        //using the coordinates, make a move and render the board on the GUI
                        currentSelected[1]=arrayCoord(pressed(e));
                        TurnProcessor turnProc = new TurnProcessor(currentSelected[0][1], currentSelected[0][0], currentSelected[1][1], currentSelected[1][0], board);
                        if(currentSelected[1][1]==currentSelected[0][1] && currentSelected[0][0] == currentSelected[1][0]){ //If the player clicked on their first selection, deselect it
                            currentSelected = new int[2][2];
                            selected=false;
                            renderBoard();
                        }
                        else if(!turnProc.isValidTurn()){   //If the selection is invalid, wait for a valid one
                            selected = true;
                        } else{         //If a valid selection, do the move
                            move(currentSelected);
                            renderBoard();
                            //revert to original state
                            currentSelected = new int[2][2];
                            selected=false;
                        }
                        makeAllAIMoves();
                    }

                }
            });
    }
    
    
    private void makeAllAIMoves(){
        if(ai!=null)
        while(!board.isWhiteTurn() && board.gameIsWon()==null){
            ai.makeMove();
            renderBoard();
        }
    }

    private void initializeText()
    {
        c.ipady=80;  
        
        final JLabel VICTORY = new JLabel ("VICTORY");  //victory text
        c.gridx=0;
        c.gridy=0;
        text.add(VICTORY, c);
        
        victoryStatus = new JLabel();  //victory status
        c.gridx=1;
        c.gridy=0;
        text.add(victoryStatus, c);

        final JLabel TURN = new JLabel ("TURN");
        c.gridx=0;
        c.gridy=1;
        text.add(TURN, c);
        
        turnStatus = new JLabel();
        c.gridx=1;
        c.gridy=1;
        text.add(turnStatus, c);    

        final JLabel AI = new JLabel ("AI STATUS");
        c.gridx=0;
        c.gridy=2;
        text.add(AI, c);
        
        aiToggle = new JButton("AI INACTIVE");
        FontMetrics fm = aiToggle.getFontMetrics(aiToggle.getFont());
        int w = fm.stringWidth("AI INACTIVE    ");
        int h = fm.getHeight();
        Dimension size = new Dimension (w,h);
        aiToggle.setMinimumSize(size);
        aiToggle.setPreferredSize(size);
        c.gridx=1;
        c.gridy=2;
        c.ipady=40;
        c.ipadx=40;
        text.add(aiToggle, c);
        
        aiToggle.addActionListener(new ActionListener() { //button for toggling AI activation status

                public void actionPerformed(ActionEvent e)
                {
                    aiActive = !aiActive;
                    if (aiActive)
                    {
                        ai = new AI3(board);
                        aiToggle.setText("AI ACTIVE  ");
                        aiMenuToggle();
                        makeAllAIMoves();
                    }
                    else
                    {
                        aiToggle.setText("AI INACTIVE");
                        ai = null;
                        aiMenuToggle();
                    }
                }

            });
            
            
        newGame = new JButton ("PLAY NEW GAME");
        c.gridx=0;
        c.gridy=5;
        c.gridwidth=2;
        c.fill = GridBagConstraints.HORIZONTAL;
        newGame.addActionListener(new ActionListener() { //button to reset game
            public void actionPerformed(ActionEvent e)
            {
                initializeBoard();
            }
        });
        
        text.add(newGame,c);
        
            
        final JLabel name = new JLabel ("PCCheckers");
        name.setFont(new Font("Courier New", Font.ITALIC, 16));
        c.gridx=0;
        c.gridy=6;
        c.gridwidth=2;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;
        c.ipady=0;
        text.add(name,c);
        
        final JLabel copyright = new JLabel ("\u00a9" + "PC Software Solutions");
        copyright.setFont(new Font("Courier New", Font.ITALIC, 16));
        c.gridx=0;
        c.gridy=7;
        c.gridwidth=2;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;
        text.add(copyright,c);

    }

    private void aiMenuToggle()
    {
        if (aiActive)
        {
            aiDifficulty = new JLabel ("AI DIFFICULTY");
            c.gridx=0;
            c.gridy=3;
            text.add(aiDifficulty, c); 
            
            difficulty = new JSlider(JSlider.HORIZONTAL, 1, 8, 4); //slider for AI aggression level
            difficulty.setMajorTickSpacing(1);
            difficulty.setPaintTicks(true);//ticks
            difficulty.setPaintLabels(true);//numbers at ticks
            
            difficulty.addChangeListener(new ChangeListener(){
                public void stateChanged(ChangeEvent e){
                    JSlider source = (JSlider) e.getSource();
                    if (!source.getValueIsAdjusting()) {
                        int newValue = source.getValue()-1;
                        ((AI3)ai).setDifficulty(newValue);
                        System.out.println(newValue);
                    }
                }
            });
            c.gridx=1;
            c.gridy=3;
            text.add(difficulty, c);
            
            aiDepth = new JLabel ("AI DEPTH");
            c.gridx=0;
            c.gridy=4;
            text.add(aiDepth, c); 
            
            lookAhead = new JSlider(JSlider.HORIZONTAL, 1, 5, 4); //slider for AI aggression level
            lookAhead.setMajorTickSpacing(1);
            lookAhead.setPaintTicks(true);//ticks
            lookAhead.setPaintLabels(true);//numbers at ticks
            lookAhead.setSnapToTicks(true);
            
            lookAhead.addChangeListener(new ChangeListener(){
                public void stateChanged(ChangeEvent e){
                    JSlider source = (JSlider) e.getSource();
                    if (!source.getValueIsAdjusting()) {
                        int newValue = source.getValue();
                        AI3.setRecur(newValue);
                        System.out.println(newValue);
                    }
                }
            });
            c.gridx=1;
            c.gridy=4;
            text.add(lookAhead, c);           
            
        }
        else
        {
            text.remove(aiDifficulty);
            text.remove(difficulty);
            text.remove(aiDepth);
            text.remove(lookAhead);
        }   
    }
    
    
    private void refreshText()
    {
        if (board.gameIsWon()!=null) //set victor if there is one
        {
            if (board.gameIsWon().getIsWhite())  
            {    
                victoryStatus.setText("WHITE");
            }
            else
            {
                victoryStatus.setText("RED");
            }
        }
        else
        {
            victoryStatus.setText("???");
        }

        if (board.isWhiteTurn()) //display turn
            turnStatus.setText("WHITE");
        else
            turnStatus.setText("RED");

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

    private void move(int[][] currentSelected) //moves the pieces in the Board variable
    {
        board.makeMove(currentSelected[0][1],currentSelected[0][0],currentSelected[1][1],currentSelected[1][0]);
    }

    public static void main (String[] args) //Run the game!
    {
        CheckersGUI gui = new CheckersGUI();
        gui.renderBoard();
    }

}
