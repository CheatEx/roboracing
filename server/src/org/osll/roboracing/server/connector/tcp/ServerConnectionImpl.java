package org.osll.roboracing.server.connector.tcp;

import java.io.IOException;
import java.net.Socket;

import org.osll.roboracing.server.connector.query.ControlResponse;
import org.osll.roboracing.server.connector.query.RobotConnectQuery;
import org.osll.roboracing.world.Control;
import org.osll.roboracing.world.ServerConnection;
import org.osll.roboracing.world.Team;

/**
 * Получение соединения с сервером через TCP
 */
public class ServerConnectionImpl extends SocketProcessor implements ServerConnection {
	
	String host = null;
	int port = 0;
	
	public ServerConnectionImpl(String host, int port) {
		this.host = host;
		this.port = port;
	}
	
	@Override
	synchronized public Control connect(String name, Team team)
			throws IllegalStateException, IOException {
		Socket socket = new Socket(host,port);
		
		RobotConnectQuery query = new RobotConnectQuery();
		query.setName(name);
		query.setTeam(team);
		
		write(socket,query);
		
		ControlResponse response = (ControlResponse) read(socket);
				
		return new ControlImpl(host,response.getPort(), name, team);
	}

}
