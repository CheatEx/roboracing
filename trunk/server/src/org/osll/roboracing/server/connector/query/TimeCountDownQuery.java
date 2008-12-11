package org.osll.roboracing.server.connector.query;

import java.io.Serializable;

public class TimeCountDownQuery extends DefaultQuery implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3301572603112147090L;

	public TimeCountDownQuery() {
		super(Type.TIME_COUNTDOWN);
	}
}
