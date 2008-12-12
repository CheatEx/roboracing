package org.osll.roboracing.server.connector.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import java.io.IOException;
import org.osll.roboracing.world.Team;

public interface ServerConnection extends Remote {

	/**
	 * Request server for connection and joining to given team
	 * @param team desired team
	 * @throws IllegalStateException if server doesn't ready for accepting connections
	 * @throws IOException if error occurred during connection(on network, for example)
	 * @return RMI service name
	 */
	public String connect(String name, Team team) throws IllegalStateException, IOException, RemoteException;
}