package net.argus.emessage.api.ui.bubble;

import static net.argus.emessage.api.ui.bubble.Type.CENTER;
import static net.argus.emessage.api.ui.bubble.Type.DARK;
import static net.argus.emessage.api.ui.bubble.Type.FRIEND;
import static net.argus.emessage.api.ui.bubble.Type.LIGHT;
import static net.argus.emessage.api.ui.bubble.Type.USER;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

import javax.swing.JPanel;

import net.argus.emessage.api.ui.bubble.instance.BubbleInstance;
import net.argus.emessage.api.ui.bubble.instance.DefaultBubbleInstance;

public class BubbleGroup extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2775885915400791949L;
	private BubbleInstance instance;
	private List<Bubble> bubbles = new ArrayList<Bubble>();
	
	public BubbleGroup() {
		instance = new DefaultBubbleInstance(new Font("SFPro", 0, 17));
	}
	
	public Bubble createBubble(String text, String name, int type) {
		BubbleMeta meta = null;
		
		Bubble before = bubbles.size()!=0?bubbles.get(bubbles.size()-1):null;

		if(before == null || before.getBubbleText().getType() != type || (before.getMeta().getName() == null?name != null:!before.getMeta().getName().equals(name)))
			meta = new BubbleMeta(instance, type, name, true, (name==null||name.equals("")?false:true), ((type==Type.CENTER+Type.DARK||type==Type.CENTER+Type.LIGHT)?false:true));
		else {
			meta = new BubbleMeta(instance, type, name, true, false, ((type==Type.CENTER+Type.DARK||type==Type.CENTER+Type.LIGHT)?false:true));
			before.getMeta().setDisplayTail(false);
		}

		Bubble b = new Bubble(new BubbleText(text, type, meta.isCenter(), instance), meta);
		
		int plusHeight = (int) instance.getCoef() * (meta.isDisplayTail()?instance.getSpace():instance.getSmallSpace());
		if(bubbles.size() != 0 && !bubbles.get(bubbles.size()-1).getMeta().isDisplayTail())
			plusHeight = plusHeight - (instance.getSpace() - instance.getSmallSpace()) * (int) instance.getCoef();
		
		if(!meta.isCenter() && meta.isDisplayName())
			plusHeight = plusHeight + instance.getNameHeight() * (int) instance.getCoef();
		
		setPreferredSize(new Dimension(getPreferredSize().width, getPreferredSize().height + b.getHeight() + plusHeight));
		
		bubbles.add(b);
		repaint();
		
		return b;
	}
	
	public BubbleInstance getInstance() {
		return instance;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		super.paintComponent(g2d);
		

		g2d.translate(instance.getPanelBorderWidth(), instance.getPanelBorderHeight());
		try {
			for(Bubble b : bubbles) {
				BubbleText text = b.getBubbleText();
				int transX = 0;
				
				boolean myMessage = false;
				boolean centerMesage = false;
				
				switch(text.getType()) {
					case FRIEND + LIGHT:
					case FRIEND + DARK:
						
						break;
					
					case CENTER + LIGHT:
					case CENTER + DARK:
						transX = (getWidth() - b.getWidth()) / 2 - instance.getPanelBorderWidth() * 2;
						centerMesage = true;
						break;
						
					case USER + LIGHT:
					case USER + DARK:
						
					default:
						transX = getWidth() - b.getWidth() - instance.getPanelBorderWidth() * 2;
						myMessage = true;
						break;
				}
				g2d.translate(transX, 0);
				
				if(!centerMesage && b.getMeta().isDisplayName()) {
					int h = instance.getNameHeight() * (int) instance.getCoef();
					int w = instance.getBorderWidth() * (int) instance.getCoef() + (myMessage?(b.getWidth() - 3 * instance.getBorderWidth() * (int) instance.getCoef()):0);
					
					
					g2d.setColor(instance.getNameColor(text.getType()));
					
					g2d.setFont(instance.getFont((float) instance.getCoef() * instance.getNameSize()));
					g2d.translate(w, h / 2);
					g2d.drawString(b.getMeta().getName(), 0, 0);
					g2d.translate(-w, h / 2);
				}
				
				
				g2d.setFont(instance.getFont((float) ((!centerMesage?instance.getDefualtFontSize():instance.getCenterSize()) * instance.getCoef())));
				b.draw(g2d, 0, 0, false);
				
	
				/*Color c = g2d.getColor();
				g2d.setColor(Color.decode("#FF00FF"));
				g2d.drawRect(0, 0, b.getWidth(), b.getHeight());
				g2d.setColor(c);*/
				
				g2d.translate(-transX, b.getHeight() + (b.getMeta().isDisplayTail()?instance.getSpace():instance.getSmallSpace()) * (int) instance.getCoef());
			}
		}catch(ConcurrentModificationException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void clear() {
		bubbles.clear();
		setPreferredSize(new Dimension(getPreferredSize().width, 0));
		repaint();

	}

}
