package org.osll.roboracing.server.game;

import org.osll.roboracing.server.connector.tcp.GameServer;

/**
 *  Траноспорт для взаимодействия с клиентами-игроками.
 *  Предоставляет доступ по различным протоколам. 
 *  Каждый объект {@link GameController} на ряду с {@link Game} должен иметь 
 *  {@link GameTransport} который обеспечивает удаленное взаимодействие.
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

	public GameTransport(GameController controller) {
		tcpServer = new GameServer(controller);
		new Thread(tcpServer).start();
	}
	
	
	public int getTcpPort() {
		return tcpServer.getPort();
	}
	
}
