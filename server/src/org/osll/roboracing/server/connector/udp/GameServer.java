package org.osll.roboracing.server.connector.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import org.osll.roboracing.server.connector.query.CommandAcceptedResponse;
import org.osll.roboracing.server.connector.query.CommandQuery;
import org.osll.roboracing.server.connector.query.DefaultQuery;
import org.osll.roboracing.server.connector.query.DefaultResponse;
import org.osll.roboracing.server.connector.query.IsStartedResponse;
import org.osll.roboracing.server.connector.query.LoginConfirmationResponse;
import org.osll.roboracing.server.connector.query.LoginRejectedResponse;
import org.osll.roboracing.server.connector.query.PhysicalConstraintsResponse;
import org.osll.roboracing.server.connector.query.RobotConnectQuery;
import org.osll.roboracing.server.connector.query.TelemetryQuery;
import org.osll.roboracing.server.connector.query.TelemetryResponse;
import org.osll.roboracing.server.connector.query.TimeCountDownResponse;
import org.osll.roboracing.server.game.GameController;

public class GameServer extends SocketProcessor implements Runnable {

	private DatagramSocket socket = null;
	private GameController controller = null;
	
	public GameServer(GameController controller) {
		this.controller = controller;
		try {
			socket = new DatagramSocket(0);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		for(;;) {
			byte [] buf = new byte[30000];
			DatagramPacket packet = new DatagramPacket(buf,buf.length);
			try {
				socket.receive(packet);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				continue;
			}
			DefaultQuery query = (DefaultQuery)read(packet);
			
			switch (query.getType()) {
			case GET_CONNECT:
			{
				RobotConnectQuery q = (RobotConnectQuery)query;
				DefaultResponse resp = null;
				if(controller.connectPlayer(q.getName(),q.getTeam()))
					resp = new LoginConfirmationResponse();
				else 
					resp = new LoginRejectedResponse();
				write(socket,packet.getSocketAddress(),resp);
				break;
			}
			case IS_STARTED:
			{
				IsStartedResponse resp = new IsStartedResponse();
				resp.setStarted(controller.isStarted());
				write(socket,packet.getSocketAddress(),resp);
				break;
			}
			case PHYSICAL_CONSTRAINTS:
			{
				PhysicalConstraintsResponse resp = new PhysicalConstraintsResponse();
				resp.setConstraints(controller.getConstraints());
				write(socket,packet.getSocketAddress(),resp);
				break;
			}
			case COMMAND:
			{
				CommandQuery q = (CommandQuery)query;
				controller.putCommand(q.getName(), q.getCommand());
				CommandAcceptedResponse resp = new CommandAcceptedResponse();
				write(socket,packet.getSocketAddress(),resp);
				break;
			}
			case TELEMETRY:
			{
				TelemetryQuery q = (TelemetryQuery)query;
				TelemetryResponse resp = new TelemetryResponse();
				resp.setTelemetry(controller.getTelemetryFor(q.getName()));
				write(socket,packet.getSocketAddress(),resp);
				break;
			}
			case TIME_COUNTDOWN:
			{
				TimeCountDownResponse resp = new TimeCountDownResponse();
				resp.setTimeCountDown(controller.getTimeToStart());
				write(socket,packet.getSocketAddress(),resp);
				break;
			}
			default:
				break;
			}
		}
	}

	public int getPort() {
		return socket.getLocalPort();
	}
}
