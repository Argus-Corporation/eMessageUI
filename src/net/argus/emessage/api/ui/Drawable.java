package net.argus.emessage.api.ui;

import java.awt.Graphics2D;

public interface Drawable {
	
	public void draw(Graphics2D g, int offX, int offY, boolean hidden);

}
