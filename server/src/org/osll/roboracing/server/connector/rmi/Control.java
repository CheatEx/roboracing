package org.osll.roboracing.server.connector.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import org.osll.roboracing.world.ControlCommand;
import org.osll.roboracing.world.PhysicalConstraints;
import org.osll.roboracing.world.Team;
import org.osll.roboracing.world.Telemetry;

/**
 * Interface for controlling robot, receive telemetry and general game info.
 */
public interface Control extends Remote {

	public void connectPlayer(String name, Team team) throws RemoteException;
	/**
	 * This method isn't cheat-safe (:
	 * @param roboName name of robot, who received picture
	 * @return robots telemetry
	 * @throws IllegalStateException if game not
	 */
	public Telemetry getTelemetry(String name) throws IllegalStateException,RemoteException;
	
	/**
	 * And this too :(
	 * @param command command for robot
	 * @throws IllegalStateException if game not
	 */
	public void sendCommand(String name, ControlCommand command) throws IllegalStateException,RemoteException;
	
	public boolean isGameStarted() throws RemoteException;
	
	public PhysicalConstraints getPhysicalConstraints() throws RemoteException;
	
	/**
	 * Получить время до старта. Время может меняться.  
	 * @return время в милисекундах до ориентировачного старта  
	 */
	public long getTimeToStart() throws RemoteException;
}

