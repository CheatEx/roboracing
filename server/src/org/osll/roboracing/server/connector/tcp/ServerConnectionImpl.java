package org.osll.roboracing.server.connector.tcp;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

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
			throws IllegalStateException{
		Socket socket;
		try {
			socket = new Socket(host,port);
		} catch (UnknownHostException e) {
			throw new IllegalStateException("Host is unknown");
		} catch (IOException e) {
			throw new IllegalStateException("Couldn't connect to host");
		}
		
		RobotConnectQuery query = new RobotConnectQuery();
		query.setName(name);
		query.setTeam(team);
		
		write(socket,query);
		
		ControlResponse response = (ControlResponse) read(socket);
		System.out.println("Try to connect control to " + host + ":" + response.getPort());
		Control c = null;
		try {
			 c = new ControlImpl(host,response.getPort(), name, team);
		} catch (IOException e) {
			throw new IllegalStateException("Couldn't connect to Control server"); 
		}
		return c;
	}

}
