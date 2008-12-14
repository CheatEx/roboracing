package org.osll.roboracing.server.connector.rmi.client;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.osll.roboracing.server.connector.DefaultOptions;
import org.osll.roboracing.server.connector.rmi.Control;
import org.osll.roboracing.server.connector.rmi.ServerConnection;
import org.osll.roboracing.world.Team;

public class ServerConnectionImpl implements org.osll.roboracing.world.ServerConnection {
	
	ServerConnection service = null;
	
	public ServerConnectionImpl() {
		try {
			Registry registry = LocateRegistry.getRegistry(DefaultOptions.getHost());
			service = (ServerConnection)registry.lookup("Roboracing_LoginServer");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public org.osll.roboracing.world.Control connect(String name, Team team)
			throws IllegalStateException, IOException, RemoteException {
		return new ControlImpl(service.connect(name, team),name,team);
	}

}
