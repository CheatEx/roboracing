package org.osll.roboracing.world;

import java.io.Serializable;

/**
 */
public class Telemetry extends State implements Serializable {
	
	private Robot self;
	
	private double time;
	
	public Robot getSelf() {
		return self;
	}
	
	/**
	 * Time, when picture fixed.
	 * @return telemetry timestamp
	 */
	public double getTime() {
		return time;
	}

	public void setSelf(Robot self) {
		this.self = self;
	}

	public void setTime(double time) {
		this.time = time;
	}
}
