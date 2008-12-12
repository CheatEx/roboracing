package org.osll.roboracing.world;

import java.io.Serializable;

/**
 * @author zan
 */
public class Checkpoint extends WorldObject implements Serializable {

	public Checkpoint(double x, double y) {
		super(x, y, 0);
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
