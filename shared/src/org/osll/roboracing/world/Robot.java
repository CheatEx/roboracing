package org.osll.roboracing.world;

/**
 * @author zan
 */
public interface Robot extends WorldObject, Speed {
	
	/**
	 * Name of robot. Must be unique on game land.
	 * @return robot's name
	 */
	public String getName();
}
