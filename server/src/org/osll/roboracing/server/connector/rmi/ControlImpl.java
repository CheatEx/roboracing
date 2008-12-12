package org.osll.roboracing.server.connector.rmi;

import java.rmi.RemoteException;

import org.osll.roboracing.server.game.GameController;
import org.osll.roboracing.world.ControlCommand;
import org.osll.roboracing.world.PhysicalConstraints;
import org.osll.roboracing.world.Telemetry;

public class ControlImpl implements Control {

	private GameController controller = null;
	
	public ControlImpl(GameController controller) {
		this.controller = controller;
	}
	
	@Override
	public PhysicalConstraints getPhysicalConstraints() throws RemoteException {
		return controller.getConstraints();
	}

	@Override
	public Telemetry getTelemetry(String name) throws IllegalStateException,
			RemoteException {
		return controller.getTelemetryFor(name);
	}

	@Override
	public long getTimeToStart() throws RemoteException {
		return controller.getTimeToStart();
	}

	@Override
	public boolean isGameStarted() throws RemoteException {
		return controller.isStarted();
	}

	@Override
	public void sendCommand(String name, ControlCommand command)
			throws IllegalStateException, RemoteException {
		controller.putCommand(name, command);

	}

}
