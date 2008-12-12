package org.osll.roboracing.server.connector.corba.client;

import java.io.IOException;

import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.osll.roboracing.server.connector.DefaultOptions;
import org.osll.roboracing.server.connector.corba.Adapter;
import org.osll.roboracing.server.connector.corba.service.ServerConnectionHelper;
import org.osll.roboracing.world.Control;
import org.osll.roboracing.world.ServerConnection;
import org.osll.roboracing.world.Team;

public class ServerConnectionImpl implements ServerConnection {

	private org.osll.roboracing.server.connector.corba.service.ServerConnection service = null;
	
	public ServerConnectionImpl() {
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
	    String name = "Roboracing/LoginServer";
	    try {
			service = ServerConnectionHelper.narrow(ncRef.resolve_str(name));
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
	public Control connect(String name, Team team)
			throws IllegalStateException, IOException {
		return new ControlImpl(service.connect(name, Adapter.convertTeam(team)),name,team);
	}

}
