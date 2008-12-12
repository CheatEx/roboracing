package org.osll.roboracing.server.connector.rmi;

import java.io.IOException;
import java.rmi.RemoteException;

import org.osll.roboracing.server.game.GameController;
import org.osll.roboracing.server.game.engine.GameStorageImpl;
import org.osll.roboracing.world.Team;

public class ServerConnectionImpl implements ServerConnection {
    
	public ServerConnectionImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String connect(String name, Team team)
			throws IllegalStateException, IOException, RemoteException {
		GameController c = GameStorageImpl.getInstance().register(name, team);
		return c.getTransport().getRmiServiceName();
	}

}
