package net.argus.emessage.api.ui.bubble.tail;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

import net.argus.emessage.api.ui.CComponent;
import net.argus.emessage.api.ui.bubble.Type;
import net.argus.emessage.api.ui.bubble.instance.BubbleInstance;

public class TailBubble extends CComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5226169821119805701L;
	
	public static final int TAIL_ADD = 3;
	
	private BubbleInstance instance;
	private TailRendering rendering;
	
	private float off;

	public TailBubble(int type, BubbleInstance instance) {
		super(new Dimension((int) (15 * instance.getCoef()), (int) (15 * instance.getCoef())));
		this.instance = instance;
		
		this.rendering = getRendering(type);
				
		off = getWidth() - 12f * instance.getCoef();
	}

	@Override
	public void draw(Graphics2D g, int offX, int offY, boolean hidden) {
		rendering.draw(g, offX, offY, hidden);
		g.setColor(Color.decode("#ff00ff"));
		//g.drawRect(offX, offY, getWidth(), getHeight());
	}

	public int getOff() {
		return(int) off;
	}
	
	private TailRendering getRendering(int type) {
		switch(type) {
	
			case Type.FRIEND + Type.LIGHT:
			case Type.FRIEND + Type.DARK:
				return new FriendTailRendering(type, instance);
				
			case Type.USER + Type.LIGHT:
			case Type.USER + Type.DARK:
				
			default:
				return new UserTailRendering(type, instance);

		}
		
	}

}
