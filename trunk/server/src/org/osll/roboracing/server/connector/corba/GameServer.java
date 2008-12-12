package org.osll.roboracing.server.connector.corba;

import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;
import org.osll.roboracing.server.connector.corba.service.Control;
import org.osll.roboracing.server.connector.corba.service.ControlPOATie;
import org.osll.roboracing.server.game.GameController;

public class GameServer implements Runnable {
	
	private static int count = 0;
	
	private String serviceName = null;
	private ORB orb = null;

	public GameServer(GameController controller) {
		super();
		String args[] = new String[5];
		args[0] = "";
		args[1] = "-ORBInitialPort";
		args[2] = "1050";
		args[3] = "-ORBInitialHost";
		args[4] = "127.0.0.1";

		// Create and initialize the ORB
		orb = ORB.init(args, null);
		// Get reference to rootpoa & activate the POAManager

		POA rootpoa = null;
		try {
			rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
		} catch (InvalidName e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rootpoa.the_POAManager().activate();
		} catch (AdapterInactive e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    // create servant and register it with the ORB
	    ControlServant servant = new ControlServant(controller);
	       

	    // create a tie, with servant being the delegate.
	    ControlPOATie tie = new ControlPOATie(servant, rootpoa);

	    // obtain the objectRef for the tie
	    // this step also implicitly activates the 
	    // the object
	    Control href = tie._this(orb);
		    
	    // get the root naming context
	    org.omg.CORBA.Object objRef = null;
	    NamingContextExt ncRef = null;
		try {
			objRef = orb.resolve_initial_references("NameService");
			// Use NamingContextExt which is part of the Interoperable
		    // Naming Service specification.
		    ncRef = NamingContextExtHelper.narrow(objRef);
		} catch (InvalidName e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
	    // bind the Object Reference in Naming
		serviceName= "Roboracing/GameServer"+count;
		count++;
	    NameComponent path[] = null;
		try {
			path = ncRef.to_name( serviceName );
		} catch (org.omg.CosNaming.NamingContextPackage.InvalidName e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    try {
			ncRef.rebind(path, href);
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
	public void run() {
		orb.run();
	}
	
	public String getServiceName() {
		return serviceName;
	}

}
