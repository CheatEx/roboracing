package org.osll.roboracing.server.connector.query;

import java.io.Serializable;

import org.osll.roboracing.world.Telemetry;

public class TelemetryResponse extends DefaultResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8595951111765725810L;
	
	private Telemetry telemetry;
	
	public TelemetryResponse() {
		super(Type.TELEMETRY);
	}

	public void setTelemetry(Telemetry telemetry) {
		this.telemetry = telemetry;
	}

	public Telemetry getTelemetry() {
		return telemetry;
	}
}
