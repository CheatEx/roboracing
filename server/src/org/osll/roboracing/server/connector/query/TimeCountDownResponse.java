package org.osll.roboracing.server.connector.query;

import java.io.Serializable;

public class TimeCountDownResponse extends DefaultResponse implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4112260390818720650L;
	
	private long timeCountDown;
	
	public TimeCountDownResponse() {
		super(Type.TIME_COUNTDOWN);
	}

	public long getTimeCountDown() {
		return timeCountDown;
	}

	public void setTimeCountDown(long timeCountDown) {
		this.timeCountDown = timeCountDown;
	}
}
