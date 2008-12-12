package org.osll.roboracing.server.connector.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.osll.roboracing.server.connector.query.CommandRejectResponse;
import org.osll.roboracing.server.connector.query.CommandQuery;
import org.osll.roboracing.server.connector.query.DefaultResponse;
import org.osll.roboracing.server.connector.query.ErrorResponse;
import org.osll.roboracing.server.connector.query.IsStartedQuery;
import org.osll.roboracing.server.connector.query.IsStartedResponse;
import org.osll.roboracing.server.connector.query.LoginRejectedResponse;
import org.osll.roboracing.server.connector.query.PhysicalConstraintsQuery;
import org.osll.roboracing.server.connector.query.PhysicalConstraintsResponse;
import org.osll.roboracing.server.connector.query.RobotConnectQuery;
import org.osll.roboracing.server.connector.query.TelemetryQuery;
import org.osll.roboracing.server.connector.query.TelemetryResponse;
import org.osll.roboracing.server.connector.query.TimeCountDownQuery;
import org.osll.roboracing.server.connector.query.TimeCountDownResponse;
import org.osll.roboracing.world.Control;
import org.osll.roboracing.world.ControlCommand;
import org.osll.roboracing.world.PhysicalConstraints;
import org.osll.roboracing.world.Team;
import org.osll.roboracing.world.Telemetry;

public class ControlImpl extends SocketProcessor implements Control {

	private DatagramSocket socket = null;
	private String name = null;
	private Team team = null;
	
	public ControlImpl(String host, int port, String name, Team team) throws IOException {
		socket = new DatagramSocket();
		socket.connect(new InetSocketAddress(host,port));
		this.name = name;
		this.team = team;
		
		RobotConnectQuery query = new RobotConnectQuery();
		query.setName(name);
		query.setTeam(team);
		write(socket,null,query);
		
		DefaultResponse r = (DefaultResponse) read(socket);
		if(r instanceof LoginRejectedResponse)	{
			socket.close();
			throw new IllegalStateException("Login was rejected");
		}
	}
	
	@Override
	synchronized public PhysicalConstraints getPhysicalConstraints() {
		write(socket,null,new PhysicalConstraintsQuery());
		return ((PhysicalConstraintsResponse)read(socket)).getConstraints();
	}

	@Override
	synchronized public Telemetry getTelemetry() throws IllegalStateException {
		TelemetryQuery query = new TelemetryQuery();
		query.setName(name);
		write(socket,null,query);
		
		DefaultResponse response = (DefaultResponse) read(socket);

		if(response instanceof ErrorResponse)
			throw ((ErrorResponse)response).getException();

		if(!(response instanceof TelemetryResponse))
			throw new IllegalStateException("Exchange error");
		
		return ((TelemetryResponse)response).getTelemetry();
	}

	@Override
	synchronized public boolean isGameStarted() {
		write(socket,null,new IsStartedQuery());
		return ((IsStartedResponse)read(socket)).isStarted();
	}

	@Override
	synchronized public void sendCommand(ControlCommand command)
			throws IllegalStateException {
		CommandQuery query = new CommandQuery();
		query.setCommand(command);
		query.setName(name);
		write(socket,null,query);
		
		DefaultResponse response = (DefaultResponse) read(socket);
		
		if(response instanceof CommandRejectResponse)
			throw new IllegalStateException("Command was rejected by server");
	}

	@Override
	synchronized public long getTimeToStart() {
		write(socket,null, new TimeCountDownQuery());
		return ((TimeCountDownResponse)read(socket)).getTimeCountDown();
	}

}
