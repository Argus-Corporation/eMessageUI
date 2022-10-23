package net.argus.emessage.api.ui.bubble;

public class BubbleTextLine {
	
	private String line;
	private int width;

	public BubbleTextLine(String line, int width) {
		this.line = line;
		this.width = width;
	}
	
	public String getLine() {
		return line;
	}
	
	public int getWidth() {
		return width;
	}
	
	@Override
	public String toString() {
		return line;
	}

}
