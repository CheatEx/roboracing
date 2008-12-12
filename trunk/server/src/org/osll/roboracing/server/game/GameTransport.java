package org.osll.roboracing.server.game;

import org.osll.roboracing.server.connector.tcp.GameServer;

/**
 *  Траноспорт для взаимодействия с клиентами-игроками.
 *  Предоставляет доступ по различным протоколам
 *
 */
public class GameTransport {
	
	private GameServer tcpServer = null;
	
	public enum Type {
		TCP,
		UDP,
		RMI,
		CORBA
	}

	public GameTransport() {
		tcpServer = new GameServer();
		new Thread(tcpServer).start();
	}
	
	
	public int getTcpPort() {
		return tcpServer.getPort();
	}
	
}
