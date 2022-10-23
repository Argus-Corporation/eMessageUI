package net.argus.emessage.api.ui.bubble.instance;

import java.awt.Font;

import net.argus.emessage.api.ui.bubble.tail.TailBubble;

public class DefaultBubbleInstance extends BubbleInstance {
	
	private BubbleColorInstance colorInstance;
		
	public DefaultBubbleInstance(Font font) {
		super(font);
		colorInstance = new DefaultBubbleColorInstance();
	}

	@Override
	public float getCoef() {
		return 1.1f;
	}

	@Override
	public int getBodyHeight() {
		return 20;
	}

	@Override
	public int getMaxBodyWidth(boolean center) {
		return (int) (200 * (center?getCenterMultiply():1));
	}
	
	@Override
	public int getBorderWidth() {
		return 14;
	}

	@Override
	public int getBorderHeight() {
		return 8;
	}
	
	@Override
	public int getPanelBorderWidth() {
		return 20;
	}

	@Override
	public int getPanelBorderHeight() {
		return 10;
	}

	@Override
	public int getArcSize() {
		return 30;
	}
	
	@Override
	public int getTailAdd() {
		return TailBubble.TAIL_ADD;
	}

	@Override
	public int getSpace() {
		return 15;
	}
	
	@Override
	public int getSmallSpace() {
		return 2;
	}
	
	@Override
	public int getSpaceLine() {
		return 6;
	}
	
	@Override
	public int getNameHeight() {
		return 14;
	}
	
	@Override
	public int getNameSize() {
		return 13;
	}
	
	@Override
	public int getDefualtFontSize() {
		return 17;
	}
	
	@Override
	public int getCenterSize() {
		return 15;
	}
	
	@Override
	public float getCenterMultiply() {
		return 1.7f;
	}

	@Override
	public BubbleColorInstance getColorInstance() {
		return colorInstance;
	}

	

}
