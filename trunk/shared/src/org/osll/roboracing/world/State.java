package org.osll.roboracing.world;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author zan
 */
public class State extends Map {

	private ArrayList<Robot> robots;
	
	public Collection<Robot> getRobots() {
		return robots;
	}

	public void setRobots(ArrayList<Robot> robots) {
		this.robots = robots;
	}
}
