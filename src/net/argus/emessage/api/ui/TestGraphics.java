package net.argus.emessage.api.ui;


import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import net.argus.emessage.api.ui.bubble.BubbleScrollPane;
import net.argus.emessage.api.ui.bubble.Type;

public class TestGraphics {

	public static void main(String[] args) {
		JFrame fen = new JFrame();
		
		fen.addMouseListener(new MouseListener() {
			
			Point point;
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				if(point == null)
					point = e.getPoint();
				else {
					System.out.println("x: " + (e.getPoint().x - point.x) + "  y: " + (e.getPoint().y - point.y));
					point = null;
				}
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		fen.setDefaultCloseOperation(3);
		fen.setSize(1200, 700);
		
		
		BubbleScrollPane sp = new BubbleScrollPane(Type.DARK);
		
		
		fen.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				int coef = 10;
				
				if(e.isShiftDown())
					coef *= 10;
				
				if(e.isControlDown())
					coef /= 10;
				
				if(e.getKeyCode() == KeyEvent.VK_LEFT) {
					fen.setLocation(fen.getLocation().x - coef, fen.getLocation().y);
				}
				if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
					fen.setLocation(fen.getLocation().x + coef, fen.getLocation().y);
				}
				if(e.getKeyCode() == KeyEvent.VK_UP) {
					fen.setLocation(fen.getLocation().x, fen.getLocation().y - coef);
				}
				if(e.getKeyCode() == KeyEvent.VK_DOWN) {
					fen.setLocation(fen.getLocation().x, fen.getLocation().y + coef);
				}
				
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					System.out.println(fen.getLocation());
					System.out.println(fen.getSize());
					sp.addBubble("abcdef", null, Type.USER);

				}
				
				if(e.getKeyCode() == KeyEvent.VK_SPACE) {
					System.out.println(fen.getLocation());
					System.out.println(fen.getSize());
					sp.addBubble("Hello, my name is GitHub and", "my friend", Type.FRIEND);

				}
				
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					System.out.println(fen.getLocation());
					System.out.println(fen.getSize());
					sp.addBubble("Hello, my name is GitHub and I live in Olargues in french south", "my other friend", Type.FRIEND);

				}
				
				if(e.getKeyCode() == KeyEvent.VK_F6) {
					System.out.println(fen.getLocation());
					System.out.println(fen.getSize());
					sp.addBubble("Hello, my name is GitHub and I live in Olargues in french south", "my other friend", Type.CENTER);

				}
				
				if(e.getKeyCode() == KeyEvent.VK_DELETE) {
					sp.clear();
				}
			}
		});
				
		
		fen.add(sp);
		
		fen.setVisible(true);
		
	}
}
