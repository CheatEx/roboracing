package org.osll.roboracing.server.connector.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.osll.roboracing.server.connector.query.CommandAcceptedResponse;
import org.osll.roboracing.server.connector.query.CommandQuery;
import org.osll.roboracing.server.connector.query.DefaultQuery;
import org.osll.roboracing.server.connector.query.DefaultResponse;
import org.osll.roboracing.server.connector.query.IsStartedResponse;
import org.osll.roboracing.server.connector.query.LoginConfirmationResponse;
import org.osll.roboracing.server.connector.query.LoginRejectedResponse;
import org.osll.roboracing.server.connector.query.PhysicalConstraintsQuery;
import org.osll.roboracing.server.connector.query.PhysicalConstraintsResponse;
import org.osll.roboracing.server.connector.query.RobotConnectQuery;
import org.osll.roboracing.server.connector.query.TelemetryQuery;
import org.osll.roboracing.server.connector.query.TelemetryResponse;
import org.osll.roboracing.server.connector.query.TimeCountDownQuery;
import org.osll.roboracing.server.connector.query.TimeCountDownResponse;
import org.osll.roboracing.server.game.GameController;

public class GameServer implements Runnable {

	private class ServerThread extends SocketProcessor implements Runnable {

		private Socket socket = null;
		
		public ServerThread(Socket socket) {
			this.socket = socket;
		}
		@Override
		public void run() {
			for(;;) {
				DefaultQuery query = (DefaultQuery)read(socket);
				
				switch (query.getType()) {
				case GET_CONNECT:
				{
					RobotConnectQuery q = (RobotConnectQuery)query;
					DefaultResponse resp = null;
					if(controller.connectPlayer(q.getName(),q.getTeam()))
						resp = new LoginConfirmationResponse();
					else 
						resp = new LoginRejectedResponse();
					write(socket,resp);
					break;
				}
				case IS_STARTED:
				{
					IsStartedResponse resp = new IsStartedResponse();
					resp.setStarted(controller.isStarted());
					write(socket,resp);
					break;
				}
				case PHYSICAL_CONSTRAINTS:
				{
					PhysicalConstraintsResponse resp = new PhysicalConstraintsResponse();
					resp.setConstraints(controller.getConstraints());
					write(socket,resp);
					break;
				}
				case COMMAND:
				{
					CommandQuery q = (CommandQuery)query;
					controller.putCommand(q.getName(), q.getCommand());
					CommandAcceptedResponse resp = new CommandAcceptedResponse();
					write(socket,resp);
					break;
				}
				case TELEMETRY:
				{
					TelemetryQuery q = (TelemetryQuery)query;
					TelemetryResponse resp = new TelemetryResponse();
					resp.setTelemetry(controller.getTelemetryFor(q.getName()));
					write(socket,resp);
					break;
				}
				case TIME_COUNTDOWN:
				{
					TimeCountDownResponse resp = new TimeCountDownResponse();
					resp.setTimeCountDown(controller.getTimeToStart());
					write(socket,resp);
					break;
				}
				default:
					break;
				}
			}
			
		}
		
	}
	private ServerSocket socket= null;
	private GameController controller = null;
	
	public GameServer(GameController controller) {
		this.controller = controller;
		try {
			socket = new ServerSocket(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	@Override
	public void run() {
		for(;;) {
			try {
				Socket client = socket.accept();
				new Thread(new ServerThread(client)).start();
			} catch (IOException e) {
				// TODO need test
				// думаю, что ничего страшного
			}
		}
	}

	public int getPort() {
		return socket.getLocalPort();
	}

}
