package org.osll.roboracing.world;

import java.util.Collection;

/**
 * @author zan
 */
public interface State extends Map {

		
	/**
	 * 
	 * @return
	 */
	public Collection<Robot> getRobots();
}
