package org.osll.roboracing.world;

import java.io.Serializable;

/**
 */
public class Hill extends WorldObject implements Serializable{

	public Hill(double x, double y, double r) {
		super(x, y, r);
	}
	
	public Hill(Hill hill) {
		super(hill);
	}
	
}
