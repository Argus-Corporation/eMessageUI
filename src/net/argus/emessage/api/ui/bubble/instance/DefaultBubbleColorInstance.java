package net.argus.emessage.api.ui.bubble.instance;

import java.awt.Color;

public class DefaultBubbleColorInstance extends BubbleColorInstance {

	@Override
	public Color getUserLightBubble() {
		return Color.decode("#007AFF");
	}

	@Override
	public Color getFriendLightBubble() {
		return Color.decode("#E6E5EB");
	}

	@Override
	public Color getUserDarkBubble() {
		return getUserLightBubble();
	}

	@Override
	public Color getFriendDarkBubble() {
		return Color.decode("#262629");
	}

	@Override
	public Color getUserLightText() {
		return Color.decode("#FFFFFF");
	}
	
	@Override
	public Color getFriendLightText() {
		return Color.decode("#000000");
	}
	
	@Override
	public Color getUserDarkText() {
		return getUserLightText();
	}

	@Override
	public Color getFriendDarkText() {
		return getUserLightText();
	}

	@Override
	public Color getLightName() {
		return Color.decode("#7f7f7f");
	}

	@Override
	public Color getDarkName() {
		return Color.decode("#adadb5");
	}

	@Override
	public Color getLight() {
		return Color.decode("#FFFFFF");
	}

	@Override
	public Color getDark() {
		return Color.decode("#000000");
	}

}
