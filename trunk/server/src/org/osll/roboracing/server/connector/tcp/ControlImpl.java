package org.osll.roboracing.server.connector.tcp;

import java.io.IOException;
import java.net.Socket;

import org.osll.roboracing.server.connector.query.DefaultResponse;
import org.osll.roboracing.server.connector.query.LoginRejectedResponse;
import org.osll.roboracing.server.connector.query.PhysicalConstraintsQuery;
import org.osll.roboracing.server.connector.query.PhysicalConstraintsResponse;
import org.osll.roboracing.server.connector.query.RobotConnectQuery;
import org.osll.roboracing.world.Control;
import org.osll.roboracing.world.ControlCommand;
import org.osll.roboracing.world.PhysicalConstraints;
import org.osll.roboracing.world.Team;
import org.osll.roboracing.world.Telemetry;

public class ControlImpl extends SocketProcessor implements Control {

	private Socket socket = null;
	private String name = null;
	private Team team = null;
	
	public ControlImpl(String host, int port, String name, Team team) throws IOException {
		socket = new Socket(host,port);
		this.name = name;
		this.team = team;
		
		RobotConnectQuery query = new RobotConnectQuery();
		query.setName(name);
		query.setTeam(team);
		write(socket,query);
		
		DefaultResponse r = (DefaultResponse) read(socket);
		if(r instanceof LoginRejectedResponse)	{
			socket.close();
			throw new IllegalStateException("Login was rejected");
		}
	}
	
	@Override
	synchronized public PhysicalConstraints getPhysicalConstraints() {
		write(socket,new PhysicalConstraintsQuery());
		
		DefaultResponse response = (DefaultResponse) read(socket);
		if (!(response instanceof PhysicalConstraintsResponse)) {
			throw new IllegalStateException("Recived error");
		}		
		return ((PhysicalConstraintsResponse)response).getConstraints();
	}

	@Override
	synchronized public Telemetry getTelemetry() throws IllegalStateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	synchronized public boolean isGameStarted() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	synchronized public void sendCommand(ControlCommand command)
			throws IllegalStateException {
		// TODO Auto-generated method stub

	}

}
