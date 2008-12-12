package org.osll.roboracing.server.game;

import org.osll.roboracing.server.connector.tcp.GameServer;
import org.osll.roboracing.server.connector.tcp.LoginServer;
import org.osll.roboracing.world.ControlCommand;
import org.osll.roboracing.world.PhysicalConstraints;
import org.osll.roboracing.world.State;
import org.osll.roboracing.world.Team;
import org.osll.roboracing.world.Telemetry;

/**
 * Server-private interface for game instance. Designed as thread-safe wrapper
 * around {@link Game}. Intended to serving of robot connectors. Clients should
 * provide name of robot, their present.
 * 
 * @author zan
 */
public interface GameController {

	/**
	 * Starts the game.
	 */
	public void start();
	public boolean isStarted();
	
	/**
	 * получить оринетировачное время до страта
	 * @return колчество миллисекунд до старта
	 */
	public long getTimeToStart();
	
	public PhysicalConstraints getConstraints();
	
	public State getGameState();

	public Telemetry getTelemetryFor(String name)
		throws IllegalArgumentException;

	/**
	 * Method for accepting commands. Accumulate commands, that will be sent to
	 * game on next step, during calculation circle, when sending commands to game
	 * impossible.
	 * 
	 * @param name name of robot which must receive command
	 * @param command command for robot
	 */
	public void putCommand(String name, ControlCommand command);
	
	
	/**
	 *  Получить максимально возможное число игроков в команде
	 */
	public long getMaxPlayers(Team team);
	
	/**
	 * получить текущее число игроков в команде
	 * @param team
	 * @return
	 */
	public long getPlayers(Team team);
	
	/**
	 * Зарегистрировать заявку игрока на участие. Регистрирует {@link LoginServer}
	 * @param name
	 * @param team
	 */
	public void registerPlayer(String name, Team team);
	
	/**
	 * Подключить игрока. Подключает уже {@link GameServer}. Подключение должно 
	 * происходить лишь в том случае, когда была предварительная заявка. (:
	 * @param name
	 * @param team
	 * @return true если смогли подключить
	 */
	public boolean connectPlayer(String name, Team team);
	
	/**
	 * Получить объекты, отвечающие за передачу данных
	 * @return
	 */
	public GameTransport getTransport();
	
	

	
}
