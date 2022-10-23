package net.argus.emessage.api.ui.bubble;

import net.argus.emessage.api.ui.bubble.instance.BubbleInstance;
import static net.argus.emessage.api.ui.bubble.Type.*;

public class BubbleMeta {
	
	private BubbleInstance instance;
	private int type;
	private String name;
	private boolean displayTail, displayName, displayBubble;
	

	public BubbleMeta(BubbleInstance instance, int type, String name, boolean displayTail, boolean displayName, boolean displayBubble) {
		this.instance = instance;
		this.type = type;
		this.name = name;
		this.displayTail = displayTail;
		this.displayName = displayName;
		this.displayBubble = displayBubble;
	}

	public BubbleInstance getInstance() {
		return instance;
	}
	
	public int getType() {
		return type;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isDisplayName() {
		return displayName;
	}
	
	public boolean isDisplayTail() {
		return displayTail;
	}
	
	public boolean isDisplayBubble() {
		return displayBubble;
	}
	
	public void setDisplayTail(boolean displayTail) {
		this.displayTail = displayTail;
	}
	
	public boolean isCenter() {
		switch(type) {
			case CENTER + LIGHT:
			case CENTER + DARK:
				return true;
			default:
				return false;
		}
	}
	
}
