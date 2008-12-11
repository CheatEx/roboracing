package org.osll.roboracing.server.connector.query;

import java.io.Serializable;

public class PhysicalConstraintsQuery extends DefaultQuery implements
		Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6772666928574884937L;

	public PhysicalConstraintsQuery() {
		super(Type.PHYSICAL_CONSTRAINTS);
	}
}
