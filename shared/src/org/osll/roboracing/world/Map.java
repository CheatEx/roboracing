package org.osll.roboracing.world;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author zan
 */
public class Map {
	
	private ArrayList<Checkpoint> checkpoints;
	
	private ArrayList<Hill> hills;
	
	private ArrayList<Pit> pits;
	
	public Collection<Checkpoint> getCheckpoints() {
		return checkpoints;
	}
	
	public Collection<Hill> getHills() {
		return hills;
	}
	
	public Collection<Pit> getPits() {
		return pits;
	}

	public void setCheckpoints(ArrayList<Checkpoint> checkpoints) {
		this.checkpoints = checkpoints;
	}

	public void setHills(ArrayList<Hill> hills) {
		this.hills = hills;
	}

	public void setPits(ArrayList<Pit> pits) {
		this.pits = pits;
	}
}
