package org.osll.roboracing.zps;

import org.osll.roboracing.server.connector.ClientConnectionFactory.Type;
import org.osll.roboracing.world.Team;

public class TestExplorer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
				StupidController c = new StupidController(Type.TCP);
				c.connect("tcp_zps", Team.Explorers);
				c.control();
		} catch (IllegalStateException e) {
			System.err.println(e.getMessage());
		}
	}

}
