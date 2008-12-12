package org.osll.roboracing.server.game.controller;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.osll.roboracing.server.game.Game;
import org.osll.roboracing.server.game.GameController;
import org.osll.roboracing.server.game.GameTransport;
import org.osll.roboracing.world.ControlCommand;
import org.osll.roboracing.world.PhysicalConstraints;
import org.osll.roboracing.world.Robot;
import org.osll.roboracing.world.State;
import org.osll.roboracing.world.Team;
import org.osll.roboracing.world.Telemetry;

/**
 * Game controller implementation.
 * 
 * @author zan
 */
public class ControllerImpl implements GameController {

	private static TelemetryFactory telemetryFactory =
			TelemetryFactory.instance();
	
	private Map<String, ControlCommand> commands =
			new HashMap<String, ControlCommand>();
	
	private Game game;
	private boolean started = false;
	private Date startTime = new Date(0); // ориентировачное время старта игры
	
	/**
	 * набор объектов, которые предоставляют внешний интерфейс для этой игры
	 */
	private GameTransport transport = new GameTransport(this);
	
	private class LoginInfo {
		public String name = "";
		public Team team = null;
		public boolean isConnected = false;
		public Date queryTime = new Date();
	}
	/**
	 * учет списка подавших заявку на полдключение
	 */
	private HashMap<Team, HashMap<String, LoginInfo>> wantToLogin = new HashMap<Team, HashMap<String,LoginInfo>>();

	/**
	 * Game, that will be controlled. Game must be provided with appropriate map
	 * and added robots.  
	 * @param game The Game
	 */
	public ControllerImpl(Game game) {
		this.game = game;
		wantToLogin.put( Team.Explorers, new HashMap<String, LoginInfo>());
		wantToLogin.put( Team.Interceptors, new HashMap<String, LoginInfo>());
	}
	
	@Override
	public State getGameState() {
		return game.getState();
	}

	@Override
	public Telemetry getTelemetryFor(String name) {
		State state = getGameState();
		Robot self = findRobot(state.getRobots(), name);
		
		Telemetry tel =	telemetryFactory.createTelemetry(state, 
				new VisionRadiusFilter(self, getConstraints().getVisionRadius()));
		
		tel.setTime(game.getTime());
		tel.setSelf(self);
		
		return tel;
	}

	@Override
	public void putCommand(String name, ControlCommand command) {
		commands.put(name, command);
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		started = true;
	}
	
	@Override
	public PhysicalConstraints getConstraints() {
		return game.getPhysicalConstraints();
	}
	
	private Robot findRobot(Collection<Robot> robots, String name) {
		for (Robot robot : robots) {
			if (robot.getName().equals(name))
				return robot;
		}
		throw new IllegalArgumentException("No robot with name "+name+" found");
	}

	@Override
	public long getMaxPlayers(Team team) {
		return 4;
	}

	@Override
	synchronized public long getPlayers(Team team) {
		return wantToLogin.get(team).size();
	}

	@Override
	synchronized public void registerPlayer(String name, Team team) {
		LoginInfo info = new LoginInfo();
		info.name = name;
		info.team = team;
		wantToLogin.get(team).put(name,info);
	}

	@Override
	public GameTransport getTransport() {
		return transport;
	}

	@Override
	synchronized public boolean connectPlayer(String name, Team team) {
		if(!wantToLogin.get(team).containsKey(name))
			return false;
		wantToLogin.get(team).get(name).isConnected = true;
		boolean allConnected = true;
		for (Team teamIt : wantToLogin.keySet()) {
			for (String nameIt : wantToLogin.get(teamIt).keySet()) {
				allConnected = allConnected && wantToLogin.get(teamIt).get(nameIt).isConnected;
			}
		}
		if(allConnected && getPlayers(Team.Explorers) == getMaxPlayers(Team.Explorers)
				  && getPlayers(Team.Interceptors) == getMaxPlayers(Team.Interceptors))
		{
			startTime = new Date(new Date().getTime()+10000);
			start();
		}
		return true;

	}

	@Override
	synchronized public long getTimeToStart() {
		if(startTime.getTime()==0)
			return 10000;
		return new Date().getTime() - startTime.getTime();
	}

	@Override
	public boolean isStarted() {
		return started;
	}
}
