package org.osll.roboracing.server.connector.tcp;

import java.io.IOException;
import java.net.ServerSocket;

public class GameServer implements Runnable {

	private ServerSocket socket= null;
	
	public GameServer() {
		try {
			socket = new ServerSocket(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	public int getPort() {
		return socket.getLocalPort();
	}

}
