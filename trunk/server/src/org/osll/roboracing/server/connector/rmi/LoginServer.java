package org.osll.roboracing.server.connector.rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class LoginServer implements Runnable {
	ServerConnectionImpl engine;
	ServerConnection stub; // объект который реально получит клиент
	
	public LoginServer() {
		try {
			engine = new ServerConnectionImpl();
			stub = (ServerConnection)UnicastRemoteObject.exportObject(engine, 0);
			Registry registry = LocateRegistry.getRegistry();
            registry.rebind("Roboracing_LoginServer", stub);
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

}
