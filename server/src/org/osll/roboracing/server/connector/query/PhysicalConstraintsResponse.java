package org.osll.roboracing.server.connector.query;

import java.io.Serializable;

import org.osll.roboracing.world.PhysicalConstraints;

public class PhysicalConstraintsResponse extends DefaultResponse implements
		Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9092270547737220631L;

	private PhysicalConstraints constraints;
	
	public PhysicalConstraintsResponse() {
		super(Type.PHYSICAL_CONSTRAINTS);
	}

	public void setConstraints(PhysicalConstraints constraints) {
		this.constraints = constraints;
	}

	public PhysicalConstraints getConstraints() {
		return constraints;
	}
}
