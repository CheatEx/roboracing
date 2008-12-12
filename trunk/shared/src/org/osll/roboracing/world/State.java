package org.osll.roboracing.world;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author zan
 */
public class State extends Map implements Serializable {

	private Collection<Robot> robots;
	
	public State(Map map) {
		setCheckpoints(map.getCheckpoints());
		setHills(map.getHills());
		setPits(map.getPits());
	}
	
	public State() {
	}

	public Collection<Robot> getRobots() {
		return robots;
	}

	public void setRobots(ArrayList<Robot> robots) {
		this.robots = robots;
	}

	public void setRobots(Collection<Robot> values) {
		robots = values;
	}
}
