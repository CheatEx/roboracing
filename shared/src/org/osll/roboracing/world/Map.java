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
	
	public Map(Collection<Checkpoint> checkpoints, Collection<Hill> hills,
			Collection<Pit> pits) {
		super();
		this.checkpoints = checkpoints;
		this.hills = hills;
		this.pits = pits;
	}

	public Map(Map map) {
		checkpoints = new ArrayList<Checkpoint>();
		for(Checkpoint point : map.getCheckpoints())
			checkpoints.add(new Checkpoint(point));
		hills = new ArrayList<Hill>();
		for(Hill hill: map.getHills())
			hills.add(new Hill(hill));
		pits = new ArrayList<Pit>();
		for (Pit pit : pits) 
			pits.add(new Pit(pit));
		
	}
	public Map() {
		
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
