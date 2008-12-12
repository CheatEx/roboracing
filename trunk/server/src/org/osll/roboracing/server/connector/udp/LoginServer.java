package org.osll.roboracing.server.connector.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import org.osll.roboracing.server.connector.query.ControlResponse;
import org.osll.roboracing.server.connector.query.DefaultQuery;
import org.osll.roboracing.server.connector.query.LoginRejectedResponse;
import org.osll.roboracing.server.connector.query.RobotConnectQuery;
import org.osll.roboracing.server.game.GameController;
import org.osll.roboracing.server.game.engine.GameStorageImpl;

public class LoginServer extends SocketProcessor implements Runnable {

	private DatagramSocket socket = null;
	
	public LoginServer(int port) {
		try {
			socket = new DatagramSocket(port);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		for(;;) {
			byte [] buf = new byte[30000];
			DatagramPacket packet = new DatagramPacket(buf,buf.length);
			try {
				socket.receive(packet);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				continue;
			}
			DefaultQuery query = (DefaultQuery)read(packet);
			
			if(query.getType()!=DefaultQuery.Type.GET_CONNECT) {
				write(socket, packet.getSocketAddress(), new LoginRejectedResponse());
			}
			RobotConnectQuery rcq = (RobotConnectQuery)query;
			// получили контроллер для игры в которую записали этого чувака
			GameController c = GameStorageImpl.getInstance().register(rcq.getName(), rcq.getTeam());
			
			ControlResponse resp = new ControlResponse();
			resp.setPort(c.getTransport().getUdpPort());
			write(socket,packet.getSocketAddress(),resp);
		}
	}

}
