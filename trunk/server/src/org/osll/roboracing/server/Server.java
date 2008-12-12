package org.osll.roboracing.server;

import org.osll.roboracing.server.connector.tcp.LoginServer;

public class Server {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Thread(new LoginServer(7777)).start();
//		new Thread(new org.osll.roboracing.server.connector.corba.LoginServer()).start();
//		new Thread(new org.osll.roboracing.server.connector.rmi.LoginServer()).start();
		new Thread(new org.osll.roboracing.server.connector.udp.LoginServer()).start();
	}

}
