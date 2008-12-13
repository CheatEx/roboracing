package org.osll.roboracing.zps;

import org.osll.roboracing.server.connector.ClientConnectionFactory.Type;
import org.osll.roboracing.world.Team;

public class TestExplorer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
//			Thread t1;
//			Thread t2;
//			Thread t3;
//			Thread t4;
			{
				StupidController c = new StupidController(Type.TCP);
				c.connect("tcp_zps", Team.Explorers);
				c.control();
//				t1 = new Thread(c);
//				t1.start();
//			}
//			{
//				StupidController c = new StupidController(Type.UDP);
//				c.connect("udp_zps", Team.Explorers);
//				t2 =new Thread(c);
//				t2.start();
//			}
//			{
//				StupidController c = new StupidController(Type.RMI);
//				c.connect("rmi_zps", Team.Explorers);
//				t3 = new Thread(c);
//				t3.start();
//			}
//			{
//				StupidController c = new StupidController(Type.CORBA);
//				c.connect("corba_zps", Team.Explorers);
//				t4 = new Thread(c);
//				t4.start();
//			}
//			try {
//				t1.join();
//				t2.join();
//				t3.join();
//				t4.join();
//			} catch (InterruptedException e) {
			}
		} catch (IllegalStateException e) {
			System.err.println(e.getMessage());
		}
	}

}
