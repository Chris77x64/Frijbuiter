package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.Frijbuiter;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Frijbuiter.V_WIDTH;
		config.height = Frijbuiter.V_HEIGHT;
		config.title = Frijbuiter.TITLE;

		new LwjglApplication(new Frijbuiter(), config);
	}
}
