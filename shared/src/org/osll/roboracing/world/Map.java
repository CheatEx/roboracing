package org.osll.roboracing.world;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author zan
 */
public class Map implements Serializable{
	
	private Collection<Checkpoint> checkpoints;
	
	private Collection<Hill> hills;
	
	private Collection<Pit> pits;
	
	public Collection<Checkpoint> getCheckpoints() {
		return checkpoints;
	}
	
	public Collection<Hill> getHills() {
		return hills;
	}
	
	public Collection<Pit> getPits() {
		return pits;
	}

	public void setCheckpoints(Collection<Checkpoint> checkpoints) {
		this.checkpoints = checkpoints;
	}

	public void setHills(Collection<Hill> hills) {
		this.hills = hills;
	}

	public void setPits(Collection<Pit> pits) {
		this.pits = pits;
	}
}
