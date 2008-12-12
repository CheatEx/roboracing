package org.osll.roboracing.server.game;

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
}
