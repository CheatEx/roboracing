package org.osll.roboracing.server.connector.corba;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.osll.roboracing.world.ControlCommand;
import org.osll.roboracing.world.Team;
import org.osll.roboracing.world.PhysicalConstraints;
import org.osll.roboracing.world.Telemetry;

public class Adapter {
	
	public static String makeString(Object object) {
		ByteArrayOutputStream bs = new ByteArrayOutputStream();
		XMLEncoder xe = new XMLEncoder(bs);
		xe.writeObject(object);
		xe.close();
		return bs.toString();
	}
	
	public static Object makeObject(String string) {
		ByteArrayInputStream bs = new ByteArrayInputStream(string.getBytes());
		XMLDecoder xd = new XMLDecoder(bs);
		Object obj = xd.readObject();
		xd.close();
		return obj;
	}
	
	public static org.osll.roboracing.server.connector.corba.service.Team convertTeam(Team team) {
		org.osll.roboracing.server.connector.corba.service.Team t = new org.osll.roboracing.server.connector.corba.service.Team();
		t.xml = makeString(team);
		return t;
	}

	public static PhysicalConstraints convertConstraints(
			org.osll.roboracing.server.connector.corba.service.PhysicalConstraints physicalConstraints) {
		return (PhysicalConstraints)makeObject(physicalConstraints.xml);
	}

	public static Telemetry convertTelemetry(
			org.osll.roboracing.server.connector.corba.service.Telemetry telemetry) {
		return (Telemetry)makeObject(telemetry.xml);
	}

	public static org.osll.roboracing.server.connector.corba.service.ControlCommand convertCommand(
			ControlCommand command) {
		org.osll.roboracing.server.connector.corba.service.ControlCommand t = new org.osll.roboracing.server.connector.corba.service.ControlCommand();
		t.xml = makeString(command);
		return t;
	}

	public static org.osll.roboracing.server.connector.corba.service.PhysicalConstraints convertConstraints(
			PhysicalConstraints constraints) {
		org.osll.roboracing.server.connector.corba.service.PhysicalConstraints t = new org.osll.roboracing.server.connector.corba.service.PhysicalConstraints();
		t.xml = makeString(constraints);
		return t;
	}

	public static org.osll.roboracing.server.connector.corba.service.Telemetry convertTelemetry(
			Telemetry telemetryFor) {
		org.osll.roboracing.server.connector.corba.service.Telemetry t = new org.osll.roboracing.server.connector.corba.service.Telemetry();
		t.xml = makeString(telemetryFor);
		return t;
	}

	public static ControlCommand convertCommand(
			org.osll.roboracing.server.connector.corba.service.ControlCommand command) {
		return (ControlCommand)makeObject(command.xml);
	}

	public static Team convertTeam(
			org.osll.roboracing.server.connector.corba.service.Team team) {
		return (Team)makeObject(team.xml);
	}

	
	
}
