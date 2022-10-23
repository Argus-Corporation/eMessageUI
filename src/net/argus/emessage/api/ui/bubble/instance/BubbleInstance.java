package net.argus.emessage.api.ui.bubble.instance;

import java.awt.Color;
import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import static net.argus.emessage.api.ui.bubble.Type.*;

public abstract class BubbleInstance {
	
	private Font font;
	
	public BubbleInstance(Font font) {
		this.font = font;
	}
	
	public abstract float getCoef();
	
	public abstract int getBorderWidth();
	
	public abstract int getBodyHeight();
	
	public abstract int getMaxBodyWidth(boolean center);
	
	public abstract int getBorderHeight();
	
	public abstract int getPanelBorderWidth();
	
	public abstract int getPanelBorderHeight();
	
	public abstract int getArcSize();
	
	public abstract int getTailAdd();
	
	public abstract int getSpace();
	
	public abstract int getSmallSpace();
	
	public abstract int getSpaceLine();
	
	public abstract int getNameHeight();
	
	public abstract int getNameSize();
	
	public abstract int getCenterSize();
	
	public abstract float getCenterMultiply();
	
	public abstract BubbleColorInstance getColorInstance();
	
	public Color getBubbleColor(int type) {
		switch (type) {
			case USER + LIGHT:
				return getColorInstance().getUserLightBubble();
				
			case USER + DARK:
				return getColorInstance().getUserDarkBubble();
	
			case FRIEND + LIGHT:
				return getColorInstance().getFriendLightBubble();
	
			case FRIEND + DARK:
				return getColorInstance().getFriendDarkBubble();
			}
		
		throw new IllegalArgumentException();
	}
	
	public Color getTextColor(int type) {
		switch (type) {
			case USER + LIGHT:
				return getColorInstance().getUserLightText();
				
			case USER + DARK:
				return getColorInstance().getUserDarkText();
	
			case FRIEND + LIGHT:
				return getColorInstance().getFriendLightText();
	
			case FRIEND + DARK:
				return getColorInstance().getFriendDarkText();
			
			case CENTER + LIGHT:
			case CENTER + DARK:
				return getNameColor(type);
		}
		
		throw new IllegalArgumentException();
	}
	
	public Color getNameColor(int type) {
		switch (type) {
			case USER + LIGHT:
			case FRIEND + LIGHT:
			case CENTER + LIGHT:
				return getColorInstance().getLightName();
				
			case USER + DARK:
			case FRIEND + DARK:
			case CENTER + DARK:
				return getColorInstance().getDarkName();
		}
		
		throw new IllegalArgumentException();
	}
	
	public Color getBackgroundColor(int type) {
		switch (type) {
			case  LIGHT:
				return getColorInstance().getLight();
				
			case DARK:
				return getColorInstance().getDark();
		}
		
		throw new IllegalArgumentException();
	}
	
	public Font getFont() {
		return font;
	}
	
	public abstract int getDefualtFontSize();
	
	public Font getFont(float size) {
		return getFont().deriveFont(size);
	}
	
	public Font getFont(int style) {
		return getFont().deriveFont(style);
	}
	
	public Font getFont(int style, float size) {
		return getFont().deriveFont(style, size);
	}
		
	public int getWidth(String str, int size) {
		return (int) getFontMetrics(str, size).getWidth();
	}
	
	public int getHeight(String str, int size) {
		return (int) getFontMetrics(str, size).getHeight();
	}
	
	public int getSecurityHeight(String str) {
		return (int) getSecurityFontMetrics(str).getHeight();
	}
	
	public Rectangle2D getFontMetrics(String str, int size) {
		GlyphVector vector = getFont(getDefualtFontSize() * getCoef()).createGlyphVector(new FontRenderContext(new AffineTransform(), true, false), str);
		return vector.getVisualBounds();
		
	}
	public Rectangle2D getSecurityFontMetrics(String str) {
		GlyphVector vector = getFont(getDefualtFontSize() * getCoef()).createGlyphVector(new FontRenderContext(new AffineTransform(), true, true), str);
		return vector.getVisualBounds();
		
	}
	
}
