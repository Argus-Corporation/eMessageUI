package net.argus.emessage.api.ui.bubble;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

import net.argus.emessage.api.ui.CComponent;
import net.argus.emessage.api.ui.bubble.instance.BubbleInstance;

public class MessageBubble extends CComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3661300261856121132L;
	
	private BubbleInstance instance;
	
	private BubbleText text;
	private boolean center;

	public MessageBubble(BubbleText text, BubbleInstance instance, boolean center) {
		this(new Dimension((int) (instance.getCoef() * instance.getBorderWidth() * 2 + text.getMaxWidth()),
			(int) instance.getCoef() * (instance.getBorderHeight() * 2 + instance.getSpaceLine() * (text.getLength())) + text.getHeight() * text.getLength()), text, instance, center);
	}
	
	public MessageBubble(Dimension dimension, BubbleText text, BubbleInstance instance, boolean center) {
		super(dimension);
		this.instance = instance;
		this.text = text;
		this.center = center;
	}
	
	@Override
	public void draw(Graphics2D g, int offX, int offY, boolean hidden) {
		g.translate(offX, offY);
		g.setColor(instance.getBubbleColor(text.getType()));

		g.fillRoundRect(0, 0, getWidth(), getHeight(), (int) instance.getCoef() * instance.getArcSize(), (int) (instance.getCoef() * instance.getArcSize()));
		g.translate(-offX, -offY);
	}
	
	public void drawText(Graphics2D g, int offX, int offY) {
		g.translate(offX, offY);

		g.setColor(Color.decode("#000000"));


		int wBorder = instance.getBorderWidth() * (int) instance.getCoef();
		int hBorder = (int) ((int) instance.getCoef() * (instance.getBorderHeight()) + text.getHeight());
		
		g.translate(wBorder, hBorder);
		
		
		int w = 0;
		int h = 0;
		int H = 0;
		
		g.setColor(instance.getTextColor(text.getType()));
		for(int i = 0; i  < text.getLength(); i++) {
			if(center) {
				w = (int) (getWidth() - text.getWidth(i)) / 2;
				g.translate(w, 0);
			}
			g.drawString(text.getText(i), -1, 0);
			
			//g.drawRect(0, (int)  -text.getHeight(), text.getWidth(i),  text.getHeight());
			
			h = (int) (instance.getSpaceLine() * instance.getCoef()) + text.getHeight();
			H += h;
			g.translate(-w, h);
		}

		g.translate(0, -H);
		
		
		g.translate(-wBorder, -hBorder);
		g.translate(-offX, -offY);

	}

}
