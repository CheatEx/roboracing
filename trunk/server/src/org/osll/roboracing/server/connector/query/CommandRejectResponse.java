package org.osll.roboracing.server.connector.query;

import java.io.Serializable;

public class CommandRejectResponse extends DefaultResponse implements
		Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2657739465102078778L;

	public CommandRejectResponse() {
		super(Type.COMMAND_REJECTED);
	}
}
