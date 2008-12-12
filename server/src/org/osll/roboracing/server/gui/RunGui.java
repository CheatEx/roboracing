package org.osll.roboracing.server.gui;

import javax.swing.JFrame;

import org.osll.roboracing.server.game.controller.DummyGameController;

/**
 * From here you can run game board panel
 * 
 * @author oakjumper
 * 
 */
public class RunGui {

	public static final String TITLE = "Roboracing";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame f = new JFrame(TITLE);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GameBoard board = new GameBoard(new DummyGameController());
		f.setContentPane(board);
		f.setSize(500, 500);
		f.setVisible(true);
		SwingAnimator(board);
	}
	public static void SwingAnimator(GameBoard board){
		Thread th = new Thread();
	    try{
	    	for(;;)
	    	{
	    		board.update();
	    		th.sleep(500);
	    	}
	    }
	    catch(InterruptedException e){}
	  }

}
