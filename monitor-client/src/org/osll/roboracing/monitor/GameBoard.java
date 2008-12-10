package org.osll.roboracing.monitor;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * General view panel
 * Visible area (and world map possibly)
 * @author oakjumper
 *
 */
public class GameBoard extends JPanel {

	private VisibleArea m_Visible;
	public GameBoard() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(m_Visible = new VisibleArea(null));
	}

	
}
