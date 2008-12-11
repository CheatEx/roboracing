package org.osll.roboracing.server.connector.query;

import java.io.Serializable;

public class IsStartedQuery extends DefaultQuery implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9149400994057212852L;

	public IsStartedQuery() {
		super(Type.IS_STARTED);
	}
}
