package org.osll.roboracing.server.gui;

import static org.osll.roboracing.server.gui.PaintConstants.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import org.osll.roboracing.world.Checkpoint;
import org.osll.roboracing.world.Coordinate;
import org.osll.roboracing.world.Hill;
import org.osll.roboracing.world.Pit;
import org.osll.roboracing.world.Robot;
import org.osll.roboracing.world.State;
import org.osll.roboracing.world.Team;
import org.osll.roboracing.world.WorldObject;

/**
 * Here is where I paint our world. It looks like a white rounds with coloured
 * round objects.
 * 
 * @author oakjumper
 * 
 */
public class WorldRound extends JPanel {
	/** world to paint */
	private State m_State;

	/** a radius of the while round */
	private double m_WorldRadius;

	public WorldRound(double worldRadius) {
		super();
		m_WorldRadius = worldRadius;
	}

	/**
	 * refresh state
	 * 
	 * @param state
	 *            new world map
	 */
	public void setState(State state) {
		m_State = state;
		if(getGraphics()!=null)
			paintComponent(getGraphics());
	}
	
	public void setWorldRadius(double r) {
		m_WorldRadius = r;
	}

	@Override
	protected void paintComponent(Graphics g) {
		paintBackground(g);
		if (m_State != null)
			paintObjects(g);
	}

	private void paintBackground(Graphics g) {
		Dimension dim = getSize();
		int side = Math.min(dim.height, dim.width);
		g.setColor(BACKGROUND_COLOR);
		g.fillOval(0, 0, side-1, side-1);
		g.setColor(BOUNDS_COLOR);
		g.drawOval(0, 0, side-1, side-1);
	}

	private void paintObjects(Graphics g) {
		for (Hill hill : m_State.getHills())
			drawRound(hill, HILL_COLOR);
		for (Pit pit : m_State.getPits())
			drawRound(pit, PIT_COLOR);
		for (Checkpoint checkpoint : m_State.getCheckpoints())
			drawCross(checkpoint, CHECKPOINT_COLOR);
		for (Robot robot : m_State.getRobots())
		{
			drawRound(robot, getTeamColor(robot.getTeam()));
			writeName(robot);
		}
	}
	
	private static Color getTeamColor(Team team)
	{
		return team == Team.Explorers ? EXPLORER_COLOR
				: INTERCEPTOR_COLOR;
	}

	private void drawRound(WorldObject o, Color color) {
		Graphics g = getGraphics();
		g.setColor(color);
		double k = getNormCoef();
		int r =(int) (o.getRadius() * k); //radius in screen srd
		int x = (int) (o.getX()*k); //screen crd
		int y = (int) (o.getY()*k);
		g.fillOval(x-r,y-r,2*r, 2*r);
		g.setColor(BOUNDS_COLOR);
		g.drawOval(x-r,y-r, 2*r, 2*r);
	}

	private void drawCross(Coordinate crd, Color color) {
		Graphics g = getGraphics();
		g.setColor(CHECKPOINT_COLOR);
		double k = getNormCoef();
		int x = (int) (crd.getX() *k);
		int y = (int) (crd.getY() *k);
		g.drawLine(x - CROSS_SIZE / 2, y - CROSS_SIZE / 2,
				x + CROSS_SIZE / 2,	y + CROSS_SIZE / 2);
		g.drawLine(x + CROSS_SIZE / 2, y - CROSS_SIZE / 2,
				x - CROSS_SIZE / 2,	y + CROSS_SIZE / 2);
	}
	
	private void writeName(Robot robot)
	{
		Graphics g = getGraphics();
		g.setColor(getTeamColor(robot.getTeam()));
		double k = getNormCoef();
		int x = (int) (robot.getX()*k);
		int y = (int) ((robot.getY()-robot.getRadius())*k)-1;
		g.drawString(robot.getName(), x, y);
	}
	
	/**
	 * normalize coefficient
	 * @return
	 */
	private double getNormCoef() {
		int size = Math.min(getSize().width, getSize().height);
		return (double) size/(2.*m_WorldRadius);
	}

}
