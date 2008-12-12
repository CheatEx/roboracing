package org.osll.roboracing.server.game;

/**
 *  Траноспорт для взаимодействия с клиентами-игроками.
 *  Предоставляет доступ по различным протоколам. 
 *  Каждый объект {@link GameController} на ряду с {@link Game} должен иметь 
 *  {@link GameTransport} который обеспечивает удаленное взаимодействие.
 *
 */
public class GameTransport {
	
	private org.osll.roboracing.server.connector.tcp.GameServer tcpServer = null;
	private org.osll.roboracing.server.connector.udp.GameServer udpServer = null;
	private org.osll.roboracing.server.connector.rmi.GameServer rmiServer = null;
	
	public enum Type {
		TCP,
		UDP,
		RMI,
		CORBA
	}

	public GameTransport(GameController controller) {
		tcpServer = new org.osll.roboracing.server.connector.tcp.GameServer(controller);
		new Thread(tcpServer).start();
		udpServer = new org.osll.roboracing.server.connector.udp.GameServer(controller);
		new Thread(udpServer).start();
		rmiServer = new org.osll.roboracing.server.connector.rmi.GameServer(controller);
		new Thread(rmiServer).start();
	}
	
	
	public int getTcpPort() {
		return tcpServer.getPort();
	}
	
	public int getUdpPort() {
		return udpServer.getPort();
	}
	
	public String getRmiServiceName() {
		return rmiServer.getServiceName();
	}
}
