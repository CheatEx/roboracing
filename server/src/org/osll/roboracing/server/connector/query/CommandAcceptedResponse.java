package org.osll.roboracing.server.connector.query;

import java.io.Serializable;

public class CommandAcceptedResponse extends DefaultResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8187201118591406873L;

	public CommandAcceptedResponse() {
		super(Type.COMMAND_ACCEPTED);
	}
}
