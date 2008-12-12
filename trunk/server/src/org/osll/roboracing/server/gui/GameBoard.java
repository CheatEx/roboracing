package org.osll.roboracing.server.gui;

import java.applet.Applet;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.Timer;

import org.osll.roboracing.server.game.GameController;

/**
 * General view panel binds world map with state updater
 * 
 * @author oakjumper
 * 
 */
public class GameBoard extends JPanel {

	/** Map view */
	private WorldRound m_WorldMap;

	/** Game state holder */
	private GameController m_Game;

	/** update time interval */
	private static int DELAY = 100;

	public GameBoard(GameController game) {
		m_Game = game;
		setLayout(new GridBagLayout());
		add(m_WorldMap = new WorldRound(m_Game.getConstraints().getWorldRadius()), 
				new GridBagConstraints(0,0,
						1,1, 
						1.0, 1.0, 
						GridBagConstraints.CENTER, 
						GridBagConstraints.BOTH, 
						new Insets(5,5,5,5),
						0,0));
		m_WorldMap.setState(game.getGameState());
//		initTimer();
	}

	public void update()
	{
		m_WorldMap.setState(m_Game.getGameState());
	}
	/**
	 * launch animation
	 */
	private void initTimer() {
		// this guy will slowly update the world map in background
		final SwingWorker updater = new SwingWorker<Void, Void>() {

			@Override
			protected Void doInBackground() throws Exception {
				m_WorldMap.setState(m_Game.getGameState());
				return null;
			}

			@Override
			protected void done() {
				System.out.println(new Date());
			}

			
		};

		ActionListener l = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				m_WorldMap.setState(m_Game.getGameState());
				if (updater.isDone())
					updater.execute();
			}

		};
		updater.execute();
		Timer timer = new Timer(DELAY, l);
		timer.start();
	}

}
