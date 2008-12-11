package org.osll.roboracing.server.gui;

import static org.osll.roboracing.server.gui.ColorConstants.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import org.osll.roboracing.world.Robot;
import org.osll.roboracing.world.State;
import org.osll.roboracing.world.Team;
import org.osll.roboracing.world.WorldObject;

/**
 * Here is where I paint our world.
 * It looks like a white rounds with coloured round objects.
 * @author oakjumper
 *
 */
public class WorldRound extends JPanel {
	/**world to paint*/
	private State m_State;
	
	/**a radius of the while round*/
	private double m_WorldRadius;
	
	/**
	 * refresh state
	 * @param state new world map
	 */
	public void setState(State state) {
		m_State = state;
		paintComponent(getGraphics());
	}

	@Override
	protected void paintComponent(Graphics g) {
		paintBackground(g);
		if(m_State!=null)
			paintObjects(g);
	}

	private void paintBackground(Graphics g) {
		Dimension dim = getSize();
		int side = Math.min(dim.height, dim.width);
		g.setColor(BACKGROUND_COLOR);
		g.fillOval(0, 0, side, side);
		g.setColor(BOUNDS_COLOR);
		g.drawOval(0, 0, side, side);
	}

	private void paintObjects(Graphics g) {
		for(WorldObject hill: m_State.getHills())
			paintRound(hill, HILL_COLOR);
		for(WorldObject pit: m_State.getPits())
			paintRound(pit, PIT_COLOR);
		for(Robot robot: m_State.getRobots())
		{
			Color color = robot.getTeam()==Team.Explorers ? EXPLORER_COLOR : INTERCEPTOR_COLOR;
			paintRound(robot, color);
		}
	}

	private void paintRound(WorldObject o, Color color) {
		Graphics g = getGraphics();
		g.setColor(HILL_COLOR);
		double side = o.getRadius()/m_WorldRadius;
		g.fillOval((int) (o.getX()/(2.*m_WorldRadius)),
				(int) (o.getY()/(2.*m_WorldRadius)), 
				(int) (2.*side), 
				(int) (2.*side));
		g.setColor(BOUNDS_COLOR);
		g.drawOval((int) (o.getX()/(2.*m_WorldRadius)),
				(int) (o.getY()/(2.*m_WorldRadius)), 
				(int) (2.*side), 
				(int) (2.*side));
	}
	

}
