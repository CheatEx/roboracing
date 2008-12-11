package org.osll.roboracing.server.connector.query;

import java.io.Serializable;

public class IsStartedResponse extends DefaultResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3187098681746815518L;
	
	private boolean started;
	
	public IsStartedResponse() {
		super(Type.IS_STARTED);
	}

	public void setStarted(boolean started) {
		this.started = started;
	}

	public boolean isStarted() {
		return started;
	}
}
