package org.osll.roboracing.server.connector.tcp.test;

import java.io.IOException;

import org.osll.roboracing.server.connector.tcp.ControlImpl;
import org.osll.roboracing.server.connector.tcp.LoginServer;
import org.osll.roboracing.server.connector.tcp.ServerConnectionImpl;
import org.osll.roboracing.world.Control;
import org.osll.roboracing.world.ServerConnection;
import org.osll.roboracing.world.Team;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Thread(new LoginServer(7777)).start();
		System.out.println("Login Server started");
		ServerConnection service = new ServerConnectionImpl("127.0.0.1",7777);
		
		try {
			Control control = service.connect("zps",Team.Explorers);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
