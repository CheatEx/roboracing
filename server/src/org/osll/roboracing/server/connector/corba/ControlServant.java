package org.osll.roboracing.server.connector.corba;

import org.osll.roboracing.server.connector.corba.service.ControlCommand;
import org.osll.roboracing.server.connector.corba.service.ControlPOA;
import org.osll.roboracing.server.connector.corba.service.PhysicalConstraints;
import org.osll.roboracing.server.connector.corba.service.Telemetry;
import org.osll.roboracing.server.game.GameController;

public class ControlServant extends ControlPOA {

	private GameController controller = null;
	
	public ControlServant(GameController controller) {
		this.controller = controller;
	}
	
	@Override
	public PhysicalConstraints getPhysicalConstraints() {
		return Adapter.convertConstraints(controller.getConstraints());
	}

	@Override
	public Telemetry getTelemetry(String name) {
		return Adapter.convertTelemetry(controller.getTelemetryFor(name));
	}

	@Override
	public long getTimeToStart() {
		return controller.getTimeToStart();
	}

	@Override
	public boolean isGameStarted() {
		return controller.isStarted();
	}

	@Override
	public void sendCommand(String name, ControlCommand command) {
		controller.putCommand(name, Adapter.convertCommand(command));
	}

}
