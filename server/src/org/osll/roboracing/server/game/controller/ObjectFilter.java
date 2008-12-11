package org.osll.roboracing.server.game.controller;

import org.osll.roboracing.world.WorldObject;

public interface ObjectFilter {
	
	public boolean objectAccepted(WorldObject object);
}
