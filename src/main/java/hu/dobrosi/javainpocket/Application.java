package hu.dobrosi.javainpocket;

import hu.dobrosi.javainpocket.ui.Panel;
import hu.dobrosi.javainpocket.ui.menu.MenuBar;

public abstract class Application {
	public abstract String getUrl();

	public abstract void buildMenu(MenuBar menuBar);

	public abstract void buildContentPanel(Panel contentPanel);

	protected void start() {

	}

	protected void event() {

	}
}
