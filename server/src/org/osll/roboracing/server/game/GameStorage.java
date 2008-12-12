package org.osll.roboracing.server.game;

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.event.ChangeListener;

import org.osll.roboracing.world.Team;

/**
 * Реализация должна быть concurrently-safe!!!
 * 
 */
public interface GameStorage {

	/**
	 * Зарегистрировать пользователя в игру
	 * @param name
	 * @param team
	 * @return
	 */
	public GameController register(String name, Team team);
	
	/**
	 * Добавить игру
	 * @param game
	 */
	public void addGame(GameController game);
	
	/**
	 * @return all games
	 */
	ArrayList<GameController> getGames();
	
	/**
	 * add a listener of game list changes
	 * @param l listener of a game list
	 */
	void addChangeListener(ChangeListener l);
}
