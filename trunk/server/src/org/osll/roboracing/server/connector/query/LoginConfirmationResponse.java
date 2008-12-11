package org.osll.roboracing.server.connector.query;

import java.io.Serializable;

public class LoginConfirmationResponse extends DefaultResponse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6822390979644097821L;

	public LoginConfirmationResponse() {
		super(Type.LOGIN_ACCEPTED);
	}
}
