package net.argus.emessage.api.ui.bubble.tail;

import java.awt.Color;
import java.awt.Graphics2D;

import net.argus.emessage.api.ui.Path;
import net.argus.emessage.api.ui.bubble.instance.BubbleInstance;

public class FriendTailRendering extends TailRendering {

	public FriendTailRendering(int type, BubbleInstance instance) {
		super(type, instance);
	}

	@Override
	public void draw(Graphics2D g, int offX, int offY, boolean hidden) {
		g.translate(offX , offY);
		
		if(!hidden)
			g.setColor(getInstance().getBubbleColor(getType()));
		else
			g.setColor(new Color(0, 0, 0, 0));

		Path path = new Path();
		path.moveTo(getInstance().getCoef() * 0, getInstance().getCoef() * 15);
		path.arcTo(getInstance().getCoef() * 15f, getInstance().getCoef() * 15f, getInstance().getCoef() * 0f, false, false, getInstance().getCoef() * 15f, getInstance().getCoef() * 0);
		path.lineTo(getInstance().getCoef() * 3, path.getCurrentPoint().getY());
		path.lineTo(path.getCurrentPoint().getX(), getInstance().getCoef() * 9f);
		path.arcTo(getInstance().getCoef() * -8f, getInstance().getCoef() * -8f, getInstance().getCoef() * 0f, false, true, getInstance().getCoef() * 0f, getInstance().getCoef() * 15f);
		path.closePath();
		
		g.fill(path);
		g.translate(-offX, -offY);
	}
	
}