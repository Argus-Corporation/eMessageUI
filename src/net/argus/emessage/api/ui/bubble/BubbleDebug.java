package net.argus.emessage.api.ui.bubble;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

import net.argus.annotation.Test;
import net.argus.emessage.api.ui.CComponent;
import net.argus.emessage.api.ui.bubble.instance.BubbleInstance;

@Test(info = "debug")
public class BubbleDebug extends CComponent {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1643875047471778380L;
	private BubbleInstance instance;
	@SuppressWarnings("unused")
	private BubbleText text;

	public BubbleDebug(BubbleInstance instance, BubbleText text, Dimension size) {
		super(size);
		this.instance = instance;
		this.text = text;
	}

	@Override
	public void draw(Graphics2D g, int offX, int offY, boolean hidden) {
		g.translate(offX, offY);
		g.setColor(Color.decode("#ff00ff"));
		
		int borderH = (int) (instance.getCoef() * instance.getBorderHeight());
		int borderW = (int) (instance.getCoef() * instance.getBorderWidth());

		g.drawRect(0, 0, getWidth(), borderH); //draw border top
		g.drawRect(0, 0, borderW, getHeight()); //draw border left
		
		g.translate(0, getHeight() - borderH);
		g.drawRect(0, 0, getWidth(), borderH); //draw border bottom
		
		g.translate(getWidth() - borderW, -getHeight() + borderH);
		g.drawRect(0, 0, borderW, getHeight()); //draw border right
		
		g.translate(-getWidth() + borderW, getHeight() - borderH);
		g.translate(0, -getHeight() + borderH);
		
		g.translate(-offX, -offY);
	}	

}
