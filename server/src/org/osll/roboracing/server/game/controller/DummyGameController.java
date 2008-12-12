package org.osll.roboracing.server.game.controller;

import java.util.ArrayList;

import org.osll.roboracing.server.game.GameController;
import org.osll.roboracing.world.Checkpoint;
import org.osll.roboracing.world.ControlCommand;
import org.osll.roboracing.world.Hill;
import org.osll.roboracing.world.PhysicalConstraints;
import org.osll.roboracing.world.Pit;
import org.osll.roboracing.world.Robot;
import org.osll.roboracing.world.State;
import org.osll.roboracing.world.Telemetry;

public class DummyGameController implements GameController {

	private PhysicalConstraints m_constraints = new PhysicalConstraints();
	private State m_state = new State();

	public DummyGameController() {
		m_constraints.setWorldRadius(200.);

		ArrayList<Pit> pits = new ArrayList<Pit>();
		pits.add(new Pit(123., 123., 10.));
		m_state.setPits(pits);
		m_state.setHills(new ArrayList<Hill>());
		m_state.setRobots(new ArrayList<Robot>());
		ArrayList<Checkpoint> checkpoints = new ArrayList<Checkpoint>();
		checkpoints.add(new Checkpoint(160., 160.));
		m_state.setCheckpoints(checkpoints);
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

}
