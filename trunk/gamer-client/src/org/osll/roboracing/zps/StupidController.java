package org.osll.roboracing.zps;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import org.osll.roboracing.server.connector.ClientConnectionFactory;
import org.osll.roboracing.world.Checkpoint;
import org.osll.roboracing.world.Control;
import org.osll.roboracing.world.ControlCommand;
import org.osll.roboracing.world.PhysicalConstraints;
import org.osll.roboracing.world.Pit;
import org.osll.roboracing.world.ServerConnection;
import org.osll.roboracing.world.Team;
import org.osll.roboracing.world.Telemetry;

public class StupidController implements Runnable {

	@Override
	public void run() {
		for(;;) {
			control();
		}
	}
	
	ServerConnection conn = null;
	
	Control control = null;
	
	PhysicalConstraints phy = null;
	
	public StupidController(ClientConnectionFactory.Type type) {
		conn = ClientConnectionFactory.getConnection(type);
	}
	
	public void connect(String name, Team team) {
		try {
			control = conn.connect(name, team);
			phy = control.getPhysicalConstraints();
		} catch (IllegalStateException e) {
			throw e;
		} catch (IOException e) {
		}
	}
	
	public void waitForStart() {
		while(!control.isGameStarted()) {
			try {
				Thread.sleep(control.getTimeToStart());
			} catch (InterruptedException e) {
				return;
			}
		}
	}
	
	public void control() {
		waitForStart();
		ArrayList<Checkpoint> checkpoints = null;
		int nextCheckpoint = 0;
		Checkpoint checkpoint = null;
		double lastAngle = 0;
		for(;;) {
			Telemetry t = control.getTelemetry();
			if(checkpoints==null) 
				checkpoints = new ArrayList<Checkpoint>(t.getCheckpoints());
			if(checkpoints.size()>0)
				checkpoint = checkpoints.get(nextCheckpoint);
			
			double angle = lastAngle + getAngle(checkpoint,t);
			if(angle > 360)
				angle -= 360.;
			
			ControlCommand command = new ControlCommand();
			command.setAcceleration(phy.getMaxAcceleration());
			command.setAngularSpeed(angle * 4);
			control.sendCommand(command);
		}
	}

	private double getAngle(Checkpoint checkpoint, Telemetry tel) {
//		Vector<Pit> pits = new Vector<Pit>(tel.getPits());
//		Math2DVector V = new Math2DVector(tel.getSelf().getVx(),tel.getSelf().getVx());
//		Math2DVector P = new Math2DVector(tel.getSelf().getX(),tel.getSelf().getY());
//		double minDist = 1e16;
//		Pit warrning = null;
//		for (Pit pit : pits) {
//			double dist = P.diff(new Math2DVector(pit.getX(),pit.getY()));
//			if(dist<minDist) {
//				double alpha = P.sub(new Math2DVector(pit.getX(),pit.getY())).getAlpha();
//				
//				minDist = dist;
//				warrning = pit;
//			}
//		}
//		
		
		return 10*Math.random();
	}
}
