package net.argus.emessage.api.ui.bubble;

import java.awt.Dimension;
import java.awt.Graphics2D;

import net.argus.emessage.api.ui.CComponent;
import net.argus.emessage.api.ui.bubble.instance.BubbleInstance;
import net.argus.emessage.api.ui.bubble.tail.TailBubble;

public class Bubble extends CComponent {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -665188632025684191L;
	
	
	private MessageBubble messageBubble;
	private TailBubble tailBubble;

	@SuppressWarnings("unused")
	private BubbleDebug debug;
	
	private BubbleInstance instance;
	private BubbleText text;
	private BubbleMeta meta;

	public Bubble(BubbleText text, BubbleMeta meta) {
		super(new Dimension((int) (meta.getInstance().getCoef() * meta.getInstance().getBorderWidth() * 2 + text.getMaxWidth()), (int) meta.getInstance().getCoef() * (meta.getInstance().getBorderHeight() * 2 + meta.getInstance().getSpaceLine() * (text.getLength())) + text.getHeight() * text.getLength()));
		this.text = text;
		this.instance = meta.getInstance();
		this.meta = meta;

		messageBubble = new MessageBubble(text, instance, meta.isCenter());
		tailBubble = new TailBubble(text.getType(), instance);	
		
		debug = new BubbleDebug(instance, text, getPreferredSize());
	}

	@Override
	public void draw(Graphics2D g, int offX, int offY, boolean hidden) {
		if(meta.isDisplayBubble()) {
			switch(text.getType()) {
				case Type.FRIEND + Type.LIGHT:
				case Type.FRIEND + Type.DARK:
					tailBubble.draw(g, 0, messageBubble.getHeight() - tailBubble.getHeight(), !meta.isDisplayTail());			
					offX += instance.getTailAdd() * instance.getCoef();
					break;
						
				case Type.USER + Type.LIGHT:
				case Type.USER + Type.DARK:
						
				default:
					tailBubble.draw(g, getWidth() - tailBubble.getWidth() + tailBubble.getOff(), messageBubble.getHeight() - tailBubble.getHeight(), !meta.isDisplayTail());				
					break;
			}
			messageBubble.draw(g, offX, offY, false);
		}
		messageBubble.drawText(g, offX, offY);
		
		//debug.draw(g, offX, offY, false);
	}
	
	public BubbleText getBubbleText() {
		return text;
	}
	
	public MessageBubble getMessageBubble() {
		return messageBubble;
	}
	
	public TailBubble getTailBubble() {
		return tailBubble;
	}
	
	public BubbleMeta getMeta() {
		return meta;
	}

}
