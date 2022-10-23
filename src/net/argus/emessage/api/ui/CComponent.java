package net.argus.emessage.api.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JComponent;

public abstract class CComponent extends JComponent implements Drawable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2075069534402800987L;
	
	public CComponent(Dimension dimension) {
		setSize(dimension);
		setPreferredSize(dimension);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		draw(g, 0, 0, false);
	}
	
	protected void superPaintComponent(Graphics g) {
		super.paintComponent(g);
	}
	
	public void draw(Graphics g, int offX, int offY, boolean hidden) {
		Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        draw(g2d, offX, offY, hidden);
	}

}
