package org.osll.roboracing.server.gui;

import javax.swing.JFrame;

import org.osll.roboracing.server.connector.DefaultOptions;
import org.osll.roboracing.server.connector.tcp.LoginServer;
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
		//GameBoard board = new GameBoard(new DummyGameController());
		GameBoard board = new GameBoard();
		f.setContentPane(board);
		f.setSize(500, 500);
		f.setVisible(true);
		
		new Thread(new LoginServer(DefaultOptions.getTcpPort())).start();
		new Thread(new org.osll.roboracing.server.connector.corba.LoginServer()).start();
		new Thread(new org.osll.roboracing.server.connector.rmi.LoginServer()).start();
		new Thread(new org.osll.roboracing.server.connector.udp.LoginServer()).start();

	}
}
