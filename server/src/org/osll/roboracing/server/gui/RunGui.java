package org.osll.roboracing.server.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.SwingWorker;
import javax.swing.Timer;

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
	}
}
