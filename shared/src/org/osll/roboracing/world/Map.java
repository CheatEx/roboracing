package org.osll.roboracing.world;

import java.util.Collection;

/**
 * @author zan
 */
public interface Map {
	
	public Collection<Checkpoint> getCheckpoints();
	
	public Collection<Hill> getHills();
	
	public Collection<Pit> getPits();
}
