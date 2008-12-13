package org.osll.roboracing.world;

import java.io.Serializable;

/**
 */
public class Pit extends WorldObject implements Serializable{

	public Pit(double x, double y, double r) {
		super(x, y, r);
	}
	public Pit(Pit pit) {
		super(pit);
	}
}
