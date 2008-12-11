package org.osll.roboracing.server.connector.query;

import java.io.Serializable;

public class TelemetryQuery extends DefaultQuery implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4925209638784892311L;
	
	private String name;
	
	public TelemetryQuery() {
		super(Type.TELEMETRY);
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
}
