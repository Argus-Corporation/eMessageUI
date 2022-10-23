package net.argus.emessage.api.ui.bubble;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import net.argus.emessage.api.ui.bubble.instance.BubbleInstance;
import net.argus.util.ArrayManager;

public class BubbleText {

	private BubbleInstance instance;
	private List<BubbleTextLine> lines = new ArrayList<BubbleTextLine>();
	private int type;
	private boolean center;
		
	public BubbleText(String text, int type, boolean center, BubbleInstance instance) {
		this.instance = instance;
		this.type = type;
		this.center = center;
		
		init(text);
	}
	
	private void init(String text) {
		List<BubbleTextLine> lines = new ArrayList<BubbleTextLine>();
		
		int maxWidth = (int) (instance.getMaxBodyWidth(center) * instance.getCoef());
		
		List<String> words = ArrayManager.toList(text.split(" "));
		String line = "";
		while(words.size() > 0) {
			if(line.equals("") && getWidth(words.get(0)) > maxWidth) {
				List<Character> chars = getChars(words.get(0));
				String test = "";
				while(chars.size() > 0) {
					char car = chars.get(0);
					test += car;
									
					if(getWidth(test) > maxWidth) {
						lines.add(new BubbleTextLine(line, getWidth(line)));
						test = "";
						line = "";
						continue;
					}
					
					line = test;
					chars.remove(0);
				}
				words.remove(0);
			}else if(!line.equals("") && getWidth(line + words.get(0)) > maxWidth) {
				lines.add(new BubbleTextLine(line, getWidth(line)));
				line = "";
				continue;
			}else if(getWidth(line + words.get(0)) < maxWidth) {
				line += words.get(0);
				words.remove(0);
			}
			line += " ";
		}
		lines.add(new BubbleTextLine(line, getWidth(line)));
		this.lines = lines;
	}
	
	private List<Character> getChars(String word) {
		char[] chars = word.toCharArray();
		List<Character> list = new ArrayList<Character>();
		for(char car : chars)
			list.add(car);
		
		return list;
	}
	
	public BubbleInstance getInstance() {
		return instance;
	}
	
	public String getText(int line) {
		return lines.get(line).getLine();
	}
	
	public int getType() {
		return type;
	}
	
	public int getMaxWidth() {
		int w = -1;
		for(BubbleTextLine line : lines)
			if(line.getWidth() > w)
				w = line.getWidth();
		
		return w;
	}
	
	public Dimension getSize(int line) {
		return getSize(lines.get(line).getLine(), center, instance);
	}
	
	public int getWidth(int line) {
		return lines.get(line).getWidth();
	}
	
	public int getHeight() {
		return getHeight(lines.get(0).getLine(), center, instance);
	}
	
	public Dimension getSize(String text) {
		return getSize(text, center, instance);
	}
	
	public int getWidth(String text) {
		return getWidth(text, center, instance);
	}
	
	public int getHeight(String text) {
		return getHeight(text, center, instance);
	}
	
	public int getSecurityHeight(String text) {
		return instance.getSecurityHeight(text);
	}
	
	public static Dimension getSize(String text, boolean center, BubbleInstance instance) {
		return new Dimension(instance.getWidth(text, (int) ((center?instance.getCenterSize():instance.getDefualtFontSize()) * instance.getCoef())), instance.getHeight(text, (int) ((center?instance.getCenterSize():instance.getDefualtFontSize()) * instance.getCoef())));
	}
	
	public static int getWidth(String text, boolean center, BubbleInstance instance) {
		return getSize(text, center, instance).width;
	}
	
	public static int getHeight(String text, boolean center, BubbleInstance instance) {
		return getSize(text, center, instance).height;
	}
	
	public int getLength() {
		return lines.size();
	}

}
