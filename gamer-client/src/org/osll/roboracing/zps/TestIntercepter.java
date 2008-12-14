package org.osll.roboracing.zps;

import org.osll.roboracing.server.connector.ClientConnectionFactory.Type;
import org.osll.roboracing.world.Team;

public class TestIntercepter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
				StupidController c = new StupidController(Type.RMI);
				c.connect("rmi_zan", Team.Interceptors);
				c.control();
		} catch (IllegalStateException e) {
			System.err.println(e.getMessage());
		}
	}

}
