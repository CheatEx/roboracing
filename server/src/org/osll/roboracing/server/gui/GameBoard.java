package org.osll.roboracing.server.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
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
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(m_WorldMap = new WorldRound());
		initTimer();
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

		};

		ActionListener l = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (updater.isDone())
					updater.execute();
			}

		};
		updater.execute();
		new Timer(DELAY, l).start();
	}

}
