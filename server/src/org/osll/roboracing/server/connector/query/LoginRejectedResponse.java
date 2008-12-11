package org.osll.roboracing.server.connector.query;

import java.io.Serializable;

public class LoginRejectedResponse extends DefaultResponse implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1028253269886047265L;

	public LoginRejectedResponse() {
		super(Type.LOGIN_REJECTED);
	}
}
