package hu.dobrosi.javainpocket.ui.panel;

import hu.dobrosi.javainpocket.javascript.JQueryBuilder;

public class MapPanel extends Panel {

	@Override
	public void create() {
		super.create();

		JQueryBuilder.call("initMap(\"" + getId() + "\");");
	}
}
