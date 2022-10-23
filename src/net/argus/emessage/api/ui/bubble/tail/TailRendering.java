package net.argus.emessage.api.ui.bubble.tail;

import java.awt.Point;

import net.argus.emessage.api.ui.Drawable;
import net.argus.emessage.api.ui.bubble.instance.BubbleInstance;

public abstract class TailRendering implements Drawable {
	
	private BubbleInstance instance;
	private int type;

	public TailRendering(int type, BubbleInstance instance) {
		this.instance = instance;
		this.type = type;
	}
	
	public Point interpolate(Point p1, Point p2, double t){
	    return new Point((int)Math.round(p1.x * (1-t) + p2.x*t), 
	            (int)Math.round(p1.y * (1-t) + p2.y*t));
	}
	
	public BubbleInstance getInstance() {
		return instance;
	}
	
	public int getType() {
		return type;
	}

}
