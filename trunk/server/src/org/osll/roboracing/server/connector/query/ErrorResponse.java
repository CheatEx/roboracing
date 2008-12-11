package org.osll.roboracing.server.connector.query;

import java.io.Serializable;

public class ErrorResponse extends DefaultResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -445480269417554670L;
	
	private IllegalStateException exception;
	
	public ErrorResponse() {
		super(Type.ERROR);
	}
	public void setException(IllegalStateException exception) {
		this.exception = exception;
	}
	public IllegalStateException getException() {
		return exception;
	}
}
