package org.osll.roboracing.server.connector;

import org.osll.roboracing.world.ServerConnection;

public class ClientConnectionFactory {

	public enum Type {
		RMI,
		CORBA,
		TCP,
		UDP
	}
	
	public static ServerConnection getConnection(Type type) {
		ServerConnection con = null;
		switch (type) {
		case RMI:
			con = new org.osll.roboracing.server.connector.rmi.client.ServerConnectionImpl();
		    break;
	    case CORBA:
	    	con = new org.osll.roboracing.server.connector.rmi.client.ServerConnectionImpl();
	    	break;
	    case TCP:
	    	con = new org.osll.roboracing.server.connector.tcp.ServerConnectionImpl(DefaultOptions.getHost(),DefaultOptions.getTcpPort());
	    	break;
	    case UDP:
	    	con = new org.osll.roboracing.server.connector.udp.ServerConnectionImpl();
	    	break;
		default:
			break;
		}
		return con;
	}
}
