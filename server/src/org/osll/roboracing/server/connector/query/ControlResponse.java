package org.osll.roboracing.server.connector.query;

import java.io.Serializable;

public class ControlResponse extends DefaultResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8577760692027691656L;
	
	private int port; // порт на котором будет слушать игровой сервер
	
	public ControlResponse() {
		super(Type.CONTROL);
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getPort() {
		return port;
	}
}
