package org.osll.roboracing.server.connector;

public class DefaultOptions {
	private static String host = "127.0.0.1"; //Адрес хоста где запущены все серверы

	public static void setHost(String host) {
		DefaultOptions.host = host;
	}

	public static String getHost() {
		return host;
	}

	public static int getTcpPort() {
		return 7777;
	}
	
}
