package org.osll.roboracing.server.connector.udp;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import org.osll.roboracing.server.connector.DefaultOptions;
import org.osll.roboracing.server.connector.query.ControlResponse;
import org.osll.roboracing.server.connector.query.DefaultQuery;
import org.osll.roboracing.server.connector.query.RobotConnectQuery;
import org.osll.roboracing.world.Control;
import org.osll.roboracing.world.ServerConnection;
import org.osll.roboracing.world.Team;

/**
 * Получение соединения с сервером через TCP
 */
public class ServerConnectionImpl extends SocketProcessor implements ServerConnection {
	
	String host = null;
	int port = 0;
	
	public ServerConnectionImpl() {
		this.host = DefaultOptions.getHost();
		this.port = DefaultOptions.getUdpPort();
	}
	
	@Override
	synchronized public Control connect(String name, Team team)
			throws IllegalStateException, IOException {
		DatagramSocket socket = new DatagramSocket();
		socket.connect(new InetSocketAddress(host,port));	
		RobotConnectQuery query = new RobotConnectQuery();
		query.setName(name);
		query.setTeam(team);
		
		write(socket,null,query);
		
		byte [] buf = new byte[30000];
		DatagramPacket packet = new DatagramPacket(buf,buf.length);
		try {
			socket.receive(packet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new IllegalStateException("Could't send packet");
		}
		
		ControlResponse response = (ControlResponse) read(packet);
		System.out.println("Try to connect control to " + host + ":" + response.getPort());
		return new ControlImpl(host,response.getPort(), name, team);
	}

}
