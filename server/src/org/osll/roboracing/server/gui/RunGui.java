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
		f.setContentPane(new GameBoard(new DummyGameController()));
		f.setSize(100, 100);
		f.setVisible(true);
	}

}
