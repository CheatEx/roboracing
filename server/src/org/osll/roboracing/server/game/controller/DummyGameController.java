package org.osll.roboracing.server.game.controller;

import java.util.ArrayList;

import org.osll.roboracing.server.game.GameController;
import org.osll.roboracing.server.game.GameTransport;
import org.osll.roboracing.world.Checkpoint;
import org.osll.roboracing.world.ControlCommand;
import org.osll.roboracing.world.Coordinate;
import org.osll.roboracing.world.Hill;
import org.osll.roboracing.world.PhysicalConstraints;
import org.osll.roboracing.world.Pit;
import org.osll.roboracing.world.Robot;
import org.osll.roboracing.world.State;
import org.osll.roboracing.world.Team;
import org.osll.roboracing.world.Telemetry;

public class DummyGameController implements GameController {

	private PhysicalConstraints m_constraints = new PhysicalConstraints();
	private State m_state = new State();

	public DummyGameController() {
		m_constraints.setWorldRadius(200.);

		m_state.setPits(new ArrayList<Pit>());
		m_state.getPits().add(new Pit(123., 123., 10.));
		
		m_state.setHills(new ArrayList<Hill>());
		m_state.getHills().add(new Hill(215,215, 30));

		m_state.setRobots(new ArrayList<Robot>());
		m_state.getRobots().add(new Robot("Explorer",Team.Explorers, new Coordinate(300,200)));
		m_state.getRobots().add(new Robot("Interceptor",Team.Interceptors, new Coordinate(100,300)));

		m_state.setCheckpoints(new ArrayList<Checkpoint>());
		m_state.getCheckpoints().add(new Checkpoint(160., 160.));
	}

	@Override
	public PhysicalConstraints getConstraints() {
		return m_constraints;
	}

	@Override
	public State getGameState() {
		return m_state;
	}

	@Override
	public Telemetry getTelemetryFor(String name)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putCommand(String name, ControlCommand command) {
		// TODO Auto-generated method stub

	}

	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

	@Override
	public long getMaxPlayers(Team team) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getPlayers(Team team) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public GameTransport getTransport() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registerPlayer(String name, Team team) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean connectPlayer(String name, Team team) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long getTimeToStart() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isStarted() {
		// TODO Auto-generated method stub
		return false;
	}

}
