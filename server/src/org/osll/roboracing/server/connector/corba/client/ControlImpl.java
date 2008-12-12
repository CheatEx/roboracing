package org.osll.roboracing.server.connector.corba.client;

import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.osll.roboracing.server.connector.DefaultOptions;
import org.osll.roboracing.server.connector.corba.Adapter;
import org.osll.roboracing.server.connector.corba.service.ControlHelper;
import org.osll.roboracing.server.connector.corba.service.ServerConnectionHelper;
import org.osll.roboracing.world.Control;
import org.osll.roboracing.world.ControlCommand;
import org.osll.roboracing.world.PhysicalConstraints;
import org.osll.roboracing.world.Team;
import org.osll.roboracing.world.Telemetry;

public class ControlImpl implements Control {

	private org.osll.roboracing.server.connector.corba.service.Control service = null;
	private String name;
	private Team team;
	
	public ControlImpl(String serviceName, String name, Team team) {
		this.name = name;
		this.team = team;
		String args[] = new String[5];
		args[0] = "";
		args[1] = "-ORBInitialPort";
		args[2] = "1050";
		args[3] = "-ORBInitialHost";
		args[4] = DefaultOptions.getHost();

	    // create and initialize the ORB
	    ORB orb = ORB.init(args, null);

	    // get the root naming context
	    org.omg.CORBA.Object objRef = null;
		try {
			objRef = orb.resolve_initial_references("NameService");
		} catch (InvalidName e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
	    // Use NamingContextExt instead of NamingContext. This is 
	    // part of the Interoperable naming Service.  
	    NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
	 
	    // resolve the Object Reference in Naming
	    try {
			service = ControlHelper.narrow(ncRef.resolve_str(name));
		} catch (NotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CannotProceed e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (org.omg.CosNaming.NamingContextPackage.InvalidName e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public PhysicalConstraints getPhysicalConstraints()
			throws IllegalStateException {
		return Adapter.convertConstraints(service.getPhysicalConstraints());
	}

	@Override
	public Telemetry getTelemetry() throws IllegalStateException {
		return Adapter.convertTelemetry(service.getTelemetry(name));
	}

	@Override
	public long getTimeToStart() throws IllegalStateException {
		return service.getTimeToStart();
	}

	@Override
	public boolean isGameStarted() throws IllegalStateException {
		return service.isGameStarted();
	}

	@Override
	public void sendCommand(ControlCommand command)
			throws IllegalStateException {
		service.sendCommand(name, Adapter.convertCommand(command));
	}

}
