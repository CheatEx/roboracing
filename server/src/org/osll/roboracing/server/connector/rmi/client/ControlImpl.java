package org.osll.roboracing.server.connector.rmi.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.osll.roboracing.server.connector.rmi.Control;
import org.osll.roboracing.world.ControlCommand;
import org.osll.roboracing.world.PhysicalConstraints;
import org.osll.roboracing.world.Team;
import org.osll.roboracing.world.Telemetry;

public class ControlImpl implements org.osll.roboracing.world.Control {

	private Control server = null;
	private String name;
	private Team team;
	
	public ControlImpl(String serviceName, String user, Team team) {
		this.name = user;
		this.team = team;
		try {
			Registry registry = LocateRegistry.getRegistry();
			server = (Control)registry.lookup(serviceName);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public PhysicalConstraints getPhysicalConstraints() {
		PhysicalConstraints c = null;
		try {
			c = server.getPhysicalConstraints();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new IllegalStateException("RemoteError");
		}
		return c;
	}

	@Override
	public Telemetry getTelemetry() throws IllegalStateException {
		Telemetry t = null;
		try {
			t = server.getTelemetry(name);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new IllegalStateException("RemoteError");
		}
		return t;
	}

	@Override
	public long getTimeToStart() {
		long t = 0;
		try {
			t = server.getTimeToStart();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new IllegalStateException("RemoteError");
		}
		return t;
	}

	@Override
	public boolean isGameStarted() {
		boolean s = false;
		try {
			s = server.isGameStarted();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new IllegalStateException("RemoteError");
		}
		return s;
	}

	@Override
	public void sendCommand(ControlCommand command)
			throws IllegalStateException {
		try {
			server.sendCommand(name, command);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new IllegalStateException("RemoteError");
		}
	}

}
