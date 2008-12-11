package org.osll.roboracing.server.gui;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.osll.roboracing.server.game.GameController;

/**
 * General view panel
 * binds world map with state updater
 * @author oakjumper
 *
 */
public class GameBoard extends JPanel implements ChangeListener{

	/**Map view*/
	private WorldRound m_WorldMap;
	
	/**Game state holder*/
	private GameController m_Game;
	
	public GameBoard(GameController game) {
		m_Game = game;
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(m_WorldMap = new WorldRound());
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		m_WorldMap.setState(m_Game.getGameState());
	}

	
}
