package org.osll.roboracing.world;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
	
	
	
	public State(State state) {
		super(state);
		robots = new ArrayList<Robot>();
		for(Robot r : state.getRobots())
			robots.add(new Robot(r));
		}



	public State() {
	}

	public Collection<Robot> getRobots() {
		return robots;
	}

//	public void setRobots(ArrayList<Robot> robots) {
//		this.robots = robots;
//	}

	public void setRobots(Collection<Robot> values) {
		robots = values;
	}
	
	public State clone() {
		ObjectOutputStream objectOutStream = null;
		ObjectInputStream objectInStream = null;
		State s = null; 
		try{
			ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
			objectOutStream = new ObjectOutputStream(byteOutStream);
		 
			// serialize and pass the object
			objectOutStream.writeObject(this);
			objectOutStream.flush();
		 
			ByteArrayInputStream byteInStream = new ByteArrayInputStream(byteOutStream.toByteArray());
			objectInStream = new ObjectInputStream(byteInStream);
		 
			// return the new object
			s = (State)objectInStream.readObject();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				objectOutStream.close();
				objectInStream.close();
			} catch (IOException e) {
			}
		}
		return s;
	}
}
