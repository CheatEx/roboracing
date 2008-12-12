package org.osll.roboracing.server.connector.corba;

import org.osll.roboracing.server.connector.corba.service.ServerConnectionPOA;
import org.osll.roboracing.server.connector.corba.service.Team;
import org.osll.roboracing.server.game.GameController;
import org.osll.roboracing.server.game.GameStorage;
import org.osll.roboracing.server.game.engine.GameStorageImpl;

public class ServerConnectionServant extends ServerConnectionPOA {

	@Override
	public String connect(String name, Team team) {
		return GameStorageImpl.getInstance().register(name, Adapter.convertTeam(team)).getTransport().getCorbaServiceName();
	}

}
