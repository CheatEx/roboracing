package org.osll.roboracing.server.game.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.osll.roboracing.world.Checkpoint;
import org.osll.roboracing.world.Hill;
import org.osll.roboracing.world.Pit;
import org.osll.roboracing.world.Robot;
import org.osll.roboracing.world.State;
import org.osll.roboracing.world.Telemetry;
import org.osll.roboracing.world.WorldObject;

public class TelemetryFactory {
	
	private static TelemetryFactory instance;
	
	private TelemetryFactory() {
	}
	
	public static TelemetryFactory instance() {
		if (instance == null)
			instance = new TelemetryFactory();
		
		return instance;
	}
	
	public Telemetry createTelemetry(State state, ObjectFilter filter) {
		Telemetry tel = new Telemetry();

		tel.setCheckpoints(new ArrayList<Checkpoint>());
		tel.setPits(new ArrayList<Pit>());
		tel.setHills(new ArrayList<Hill>());
		tel.setRobots(new ArrayList<Robot>());
		
		filterAndFill(state.getHills(), tel.getHills(), filter);
		filterAndFill(state.getPits(), tel.getPits(), filter);
		filterAndFill(state.getCheckpoints(), tel.getCheckpoints(), filter);
		filterAndFill(state.getRobots(), tel.getRobots(), filter);
		
		return tel;
	}

	private static<E extends WorldObject> void filterAndFill(Collection<E> source,
			Collection<E> dest, ObjectFilter filter) {
		for (E object : source) {
			if (filter.objectAccepted(object))
				dest.add(object);
		}
	}
	
}
