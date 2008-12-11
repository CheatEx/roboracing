package org.osll.roboracing.server.connector.query;

import java.io.Serializable;

public class DefaultResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5417892237623541672L;

	/*
	 * тип полученного ответа
	 */
	public enum Type {
		CONTROL, //получен объект с интерфейсом Control
		LOGIN_ACCEPTED, // подтверждение соединения
		LOGIN_REJECTED, // отклонение соединения
		PHYSICAL_CONSTRAINTS,
		TELEMETRY,
		ERROR,
		IS_STARTED,
		COMMAND_ACCEPTED,
		COMMAND_REJECTED,
		TIME_COUNTDOWN,
		UNKNOWN
	}
	
	private Type type = null;
	
	public DefaultResponse(Type type) {
		this.type = type;
	}

	public Type getType() {
		return type;
	}
}
