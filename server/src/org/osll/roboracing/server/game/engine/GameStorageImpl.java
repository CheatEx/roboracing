package org.osll.roboracing.server.game.engine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

import javax.swing.event.ChangeListener;

import org.osll.roboracing.server.game.GameController;
import org.osll.roboracing.server.game.GameStorage;
import org.osll.roboracing.server.game.controller.ControllerImpl;
import org.osll.roboracing.world.PhysicalConstraints;
import org.osll.roboracing.world.Team;

/**
 * Concurrently-safe хранилище игр, точнее контролеров для них
 *
 */
public class GameStorageImpl implements GameStorage {

    static private GameStorage instance = null;
    
    private ArrayList<GameController> storage = null;
    private ArrayList<ChangeListener> listeners = null;
    
    private GameStorageImpl() {
    	storage = new ArrayList<GameController>();
    	listeners = new ArrayList<ChangeListener>();
	}
    
    static public GameStorage getInstance() {
    	if(instance == null)
    		instance = new GameStorageImpl();
    	return instance;
    }
	@Override
	synchronized public void addGame(GameController game) {
		storage.add(game);
		for(ChangeListener l: listeners)
			l.stateChanged(null);
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
	    GameController gc = new ControllerImpl(new GameImpl(new PhysicalConstraints()));
	    gc.registerPlayer(name, team);
	    addGame(gc);
	    return gc;
	}

	public synchronized ArrayList<GameController> getGames() {
		return storage;
	}

	@Override
	public void addChangeListener(ChangeListener l) {
		listeners.add(l);
	}
	
	

}
