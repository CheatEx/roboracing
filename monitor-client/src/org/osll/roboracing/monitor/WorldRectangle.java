package org.osll.roboracing.monitor;

import static org.osll.roboracing.monitor.ColorConstants.BACKGROUND_COLOR;
import static org.osll.roboracing.monitor.ColorConstants.BOUNDS_COLOR;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import org.osll.roboracing.world.State;

/**
 * Here is where I paint our world.
 * It looks like a white rectangle with coloured rounds.
 * @author oakjumper
 *
 */
public class WorldRectangle extends JPanel {
	/**world to paint*/
	private State m_State;
	
	@Override
	protected void paintComponent(Graphics g) {
		paintBackground(g);
		paintObjects(g);
	}

	public WorldRectangle(State state) {
		m_State = state;
	}

	private void paintObjects(Graphics g) {
		//TODO
	}

	private void paintBackground(Graphics g) {
		Dimension dim = getSize();
		g.setColor(BACKGROUND_COLOR);
		g.fillRect(0, 0, dim.width, dim.height);
		g.setColor(BOUNDS_COLOR);
		g.drawRect(0, 0, dim.width, dim.height);
	}

}
