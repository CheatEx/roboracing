package org.osll.roboracing.server.connector.udp;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.net.SocketException;

public class SocketProcessor {

	public SocketProcessor() {
		super();
	}
	static public Object read(DatagramSocket socket) {
		byte [] buf = new byte[30000];
		DatagramPacket packet = new DatagramPacket(buf,buf.length);
		try {
			socket.receive(packet);
		} catch (IOException e) {
			throw new IllegalStateException("Could't send packet");
		}
		return read(packet);
	}
	
	static public Object read(DatagramPacket packet) {
		Object object = null;
		ObjectInputStream s;
		try {
			s = new ObjectInputStream(new ByteArrayInputStream(packet.getData()));
			try {
				object = s.readObject();
			} catch (ClassNotFoundException e) {
			}
		} catch (IOException e) {
		}
		
		return object;
	}

	static public void write(DatagramSocket socket, SocketAddress socketAddress, Object object) {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			ObjectOutputStream s = new ObjectOutputStream(os);
			s.writeObject(object);
			s.flush();
		} catch (IOException e) {
			throw new IllegalStateException("socker error");
		}		
		try {
			if(socketAddress!=null)
				socket.send(new DatagramPacket(os.toByteArray(),os.toByteArray().length,socketAddress));
		    if(socketAddress==null)
		    	socket.send(new DatagramPacket(os.toByteArray(),os.toByteArray().length));
		} catch (SocketException e) {
			throw new IllegalStateException("socker error");
		} catch (IOException e) {
			throw new IllegalStateException("socker error");
		}
	}

}
