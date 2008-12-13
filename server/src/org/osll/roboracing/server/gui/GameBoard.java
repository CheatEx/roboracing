package org.osll.roboracing.server.gui;

import java.applet.Applet;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.osll.roboracing.server.game.GameController;
import org.osll.roboracing.server.game.GameStorage;
import org.osll.roboracing.server.game.engine.GameStorageImpl;

/**
 * General view panel binds world map with state updater
 * 
 * @author oakjumper
 * 
 */
public class GameBoard extends JPanel implements ChangeListener {

	/** Map view */
	private WorldRound m_WorldMap;

	/** Game state holder */
	private GameController m_Game;
	
	private JComboBox m_GameSelector;

	/** update time interval */
	private static int DELAY = 500;

	/**
	 * internal usage: run without GameStorageImpl
	 * @param game test game
	 */
	GameBoard(GameController game) {
		this();
		setGame(game);
	}
	
	public GameBoard() {
		setLayout(new GridBagLayout());
		int y=0;
		add(m_WorldMap = new WorldRound(1.), 
				new GridBagConstraints(0,y++,
						2,1, 
						1.0, 1.0, 
						GridBagConstraints.CENTER, 
						GridBagConstraints.BOTH, 
						new Insets(5,5,0,5),
						0,0));
		add(new JLabel("Game "),
				new GridBagConstraints(0,y,
						1,0, 
						0.0, 0.0, 
						GridBagConstraints.WEST, 
						GridBagConstraints.NONE, 
						new Insets(5,5,0,0),
						0,0));
		add(createGameSelector(),
			new GridBagConstraints(1,y,
					1,0, 
					1.0, 0.0, 
					GridBagConstraints.NORTHWEST, 
					GridBagConstraints.HORIZONTAL, 
					new Insets(5,5,5,5),
					0,0));
		GameStorageImpl.getInstance().addChangeListener(this);
		stateChanged(null);
		launchAnimation();
	}
	
	private JComboBox createGameSelector() {
		m_GameSelector = new JComboBox();
		m_GameSelector.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int idx = m_GameSelector.getSelectedIndex();
				if(idx>=0)
				{
					GameController game = GameStorageImpl.getInstance().getGames().get(idx);
					setGame(game);
				}
			}
			
		});
		return m_GameSelector;
	}
	
	/**
	 * update game list
	 */
	private void updateGameSelector() {
		ArrayList<GameController> games = GameStorageImpl.getInstance().getGames();
		m_GameSelector.removeAllItems();
		for(int i=0; i<games.size();++i)
			m_GameSelector.addItem(""+(i+1));
		m_GameSelector.setSelectedIndex(games.indexOf(m_Game));
		
	}
	
	/**
	 * switch game
	 * @param game new game
	 */
	private void setGame(GameController game) {
		m_Game = game;
		m_WorldMap.setWorldRadius(game.getConstraints().getWorldRadius());
	}

	public void launchAnimation(){
		Thread th = new Thread() {

			@Override
			public void run() {
				for(;;)
				{
					if(m_Game!=null)
					{
						m_WorldMap.setState(m_Game.getGameState());
//						System.out.println(new Date());
					}
		    		try {
						Thread.sleep(DELAY);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		};
		th.start();
	}
	
	/**
	 * invoke when game list was changed
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
		updateGameSelector();
	}
}
