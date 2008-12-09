package org.osll.roboracing.world;

import java.io.IOException;

public interface ServerConnection {

	/**
	 * Request server for connection and joining to given team
	 * @param team desired team
	 * @throws IllegalStateException if server doesn't ready for accepting connections
	 * @throws IOException if error occurred during connection(on network, for example)
	 * @return control on robot
	 */
	public Control connect(String name, Team team) throws IllegalStateException, IOException;
}
