package org.osll.roboracing.server.connector.rmi.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.osll.roboracing.server.connector.DefaultOptions;
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
		System.out.println("RMI connecting" + serviceName);
		this.name = user;
		this.team = team;
		try {
			Registry registry = LocateRegistry.getRegistry(DefaultOptions.getHost());
			server = (Control)registry.lookup(serviceName);
			server.connectPlayer(user, team);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			throw new IllegalStateException("Couldn't connect to registry");
		}
	}
	@Override
	public PhysicalConstraints getPhysicalConstraints() {
		PhysicalConstraints c = null;
		try {
			c = server.getPhysicalConstraints();
		} catch (RemoteException e) {
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
			throw new IllegalStateException("Connection refused");
		}
		return s;
	}

	@Override
	public void sendCommand(ControlCommand command)
			throws IllegalStateException {
		try {
			server.sendCommand(name, command);
		} catch (RemoteException e) {
			throw new IllegalStateException("RemoteError");
		}
	}

}
