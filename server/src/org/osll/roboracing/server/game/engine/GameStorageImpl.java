package org.osll.roboracing.server.game.engine;

import java.util.Collection;
import java.util.Vector;

import org.osll.roboracing.server.game.GameController;
import org.osll.roboracing.server.game.GameStorage;
import org.osll.roboracing.server.game.controller.ControllerImpl;
import org.osll.roboracing.world.Team;

/**
 * Concurrently-safe хранилище игр, точнее контролеров для них
 *
 */
public class GameStorageImpl implements GameStorage {

    static private GameStorage instance = null;
    
    private Collection<GameController> storage = null;
    
    private GameStorageImpl() {
    	storage = new Vector<GameController>();
	}
    
    static public GameStorage getInstance() {
    	if(instance == null)
    		instance = new GameStorageImpl();
    	return instance;
    }
	@Override
	synchronized public void addGame(GameController game) {
		storage.add(game);
	}

	@Override
	synchronized public GameController register(String name, Team team) {
		for (GameController it : storage) {
			if(it.getPlayers(team)<it.getMaxPlayers(team)) {
				it.registerPlayer(name, team);
				return it;
			}
		}
		// если дошли досюда то не нашли подходящей игры. Делаем новую.
	    GameController gc = new ControllerImpl(new GameImpl());
	    gc.registerPlayer(name, team);
	    addGame(gc);
	    return gc;
	}

}
