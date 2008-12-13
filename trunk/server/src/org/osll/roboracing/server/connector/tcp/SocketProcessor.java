package org.osll.roboracing.server.connector.tcp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketProcessor {

	public SocketProcessor() {
		super();
	}

	static public Object read(final Socket socket) {
		Object response = null;
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			try {
				socket.close();
			} catch (IOException e1) {
			}
			throw new IllegalStateException("socket error"); 
		}
		try {
			response = ois.readObject();
		} catch (IOException e) {
			try {
				socket.close();
			} catch (IOException e1) {
			}
			Thread.currentThread().interrupt();
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("recived incorrect object");
		}
		return response;
	}

	static public void write(Socket socket, Object obj) {
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
		}
		try {
			oos.writeObject(obj);
		} catch (IOException e) {
			try {
				socket.close();
			} catch (IOException e1) {
			}
		}
		try {
			oos.flush();
		} catch (IOException e) {
		}
	}

}