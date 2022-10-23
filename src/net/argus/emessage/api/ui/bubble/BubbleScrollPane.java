package net.argus.emessage.api.ui.bubble;

import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class BubbleScrollPane extends JScrollPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6425927703555314394L;
	
	private BubbleGroup pan = new BubbleGroup();
	private boolean newBubble;
	private int type;
	private int oldMax = -1;
	
	public BubbleScrollPane(int type) {
		this.type = type;
		
		setBackground(pan.getInstance().getBackgroundColor(type));
		setViewportView(pan);	
		
		getViewport().addChangeListener(getChangeListener());
		getVerticalScrollBar().setUnitIncrement(10);
	}
	
	public Bubble addBubble(String text, String name, int forwho) {
		Bubble b = pan.createBubble(text, name, type + forwho);
		getViewport().revalidate();
		
		newBubble = true;
		
		return b;
	}
	
	private ChangeListener getChangeListener() {
		return (ChangeEvent e) -> {
			if(newBubble)
				SwingUtilities.invokeLater(() -> {
					JScrollBar vert = getVerticalScrollBar();
					Rectangle rect = getViewport().getViewRect();
					
					if(oldMax == -1 || rect.y + rect.height == oldMax)
						vert.setValue(vert.getMaximum());
					
					if(vert.getValue() > 0)
					oldMax = vert.getMaximum();
				});
		};
	}
	
	public void clear() {pan.clear(); oldMax = -1; getViewport().revalidate();}
	
	@Override
	public void setBackground(Color bg) {
		if(pan != null)
			pan.setBackground(bg);
	}

}
