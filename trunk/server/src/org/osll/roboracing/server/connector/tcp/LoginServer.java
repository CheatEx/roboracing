package org.osll.roboracing.server.connector.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.osll.roboracing.server.connector.query.ControlResponse;
import org.osll.roboracing.server.connector.query.DefaultQuery;
import org.osll.roboracing.server.connector.query.LoginConfirmationResponse;
import org.osll.roboracing.server.connector.query.LoginRejectedResponse;
import org.osll.roboracing.server.connector.query.RobotConnectQuery;
import org.osll.roboracing.server.game.GameController;
import org.osll.roboracing.server.game.engine.GameStorageImpl;

public class LoginServer implements Runnable {
	
	private class ServerThread extends SocketProcessor implements Runnable {
		private Socket socket = null;
		
		public ServerThread(Socket socket) {
			this.socket = socket;
		}
		
		@Override
		public void run() {
			DefaultQuery query = (DefaultQuery) read(socket);
			
			if(query.getType()!=DefaultQuery.Type.GET_CONNECT) {
				write(socket,new LoginRejectedResponse());
				try {
					socket.getOutputStream().close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return;
				}
			}
			RobotConnectQuery rcq = (RobotConnectQuery)query;
			// получили контроллер для игры в которую записали этого чувака
			GameController c = GameStorageImpl.getInstance().register(rcq.getName(), rcq.getTeam());
			
			ControlResponse resp = new ControlResponse();
			resp.setPort(c.getTransport().getTcpPort());
			write(socket,resp);
			try {
				socket.getOutputStream().close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private ServerSocket socket = null;
	
	public LoginServer(int port) {
		try {
			socket = new ServerSocket(port);
		} catch (IOException e) {
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

}
