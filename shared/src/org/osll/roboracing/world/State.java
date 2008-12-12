package org.osll.roboracing.world;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author zan
 */
public class State extends Map implements Serializable {

	private ArrayList<Robot> robots;
	
	public Collection<Robot> getRobots() {
		return robots;
	}

	public void setRobots(ArrayList<Robot> robots) {
		this.robots = robots;
	}
}
