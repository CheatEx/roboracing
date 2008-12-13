package org.osll.roboracing.world;

import java.io.Serializable;

/**
 */
public class Checkpoint extends WorldObject implements Serializable {

	public Checkpoint(double x, double y) {
		super(x, y, 0);
	}
	
	public Checkpoint(Checkpoint point) {
		super(point);
	}

//	@Override
//	public double getRadius() {
//		return 0;
//	}

	@Override
	public void setRadius(double radius) {
		throw new UnsupportedOperationException("Radius changing not supported by Checkpoint");
	}
}
