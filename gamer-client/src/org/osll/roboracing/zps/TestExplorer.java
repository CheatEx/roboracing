package org.osll.roboracing.zps;

import org.osll.roboracing.server.connector.ClientConnectionFactory.Type;
import org.osll.roboracing.world.Team;

public class TestExplorer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		StupidController c = new StupidController(Type.TCP);
		c.connect("zps", Team.Explorers);
		c.control();
	}

}