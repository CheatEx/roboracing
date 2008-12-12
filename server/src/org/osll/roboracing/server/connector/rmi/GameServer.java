package org.osll.roboracing.server.connector.rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import org.osll.roboracing.server.game.GameController;

public class GameServer implements Runnable {

	private static int counter = 0; // идентификатор объектов 
	private ControlImpl engine;
	private Control stub; // объект который реально получит клиент
	private String name;
	
	public GameServer(GameController controller) {
		name = "Roboracing/GameServer" + counter;
		++counter;
		try {
			engine = new ControlImpl(controller);
			stub = (Control)UnicastRemoteObject.exportObject(engine, 0);
			Registry registry = LocateRegistry.getRegistry();
            registry.rebind(name, stub);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		for(;;)
		{
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				return;
			}
		}
	}
	
	public String getServiceName() {
		return name;
	}
}
