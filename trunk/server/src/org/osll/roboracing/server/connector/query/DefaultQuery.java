package org.osll.roboracing.server.connector.query;

import java.io.Serializable;
/**
 *  Запрос по-умолчанию. Определяет тип последующего запроса
 */
public class DefaultQuery implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -124471288184886059L;

	public enum Type {
		GET_CONNECT, // запрос типа "Привет" отправляется первым в соединении
		PHYSICAL_CONSTRAINTS,
		TELEMETRY,
		IS_STARTED,
		COMMAND,
		TIME_COUNTDOWN,
		UNKNOWN
	}
	
	private Type type = Type.UNKNOWN;
	
	public DefaultQuery(Type type) {
		this.type = type;
	}
	
	public Type getType() {
		return type;
	}
}