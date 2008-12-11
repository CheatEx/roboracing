package org.osll.roboracing.server.connector.query;

import java.io.Serializable;

import org.osll.roboracing.world.Team;

public class RobotConnectQuery extends DefaultQuery implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4279343130464227989L;

	private String name = null;
	private Team team = null;
	
	public RobotConnectQuery() {
		super(Type.GET_CONNECT);
		// TODO Auto-generated constructor stub
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Team getTeam() {
		return team;
	}

}
