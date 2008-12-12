package org.osll.roboracing.server.connector.query;

import java.io.Serializable;

import org.osll.roboracing.world.ControlCommand;

public class CommandQuery extends DefaultQuery implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 733487834895486045L;
	
	private ControlCommand command;
	private String name;
	
	public CommandQuery() {
		super(Type.COMMAND);
	}

	public void setCommand(ControlCommand command) {
		this.command = command;
	}

	public ControlCommand getCommand() {
		return command;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
