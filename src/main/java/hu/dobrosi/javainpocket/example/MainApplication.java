package hu.dobrosi.javainpocket.example;

import hu.dobrosi.javainpocket.Application;
import hu.dobrosi.javainpocket.ui.Label;
import hu.dobrosi.javainpocket.ui.Panel;
import hu.dobrosi.javainpocket.ui.input.Button;
import hu.dobrosi.javainpocket.ui.input.TextField;
import hu.dobrosi.javainpocket.ui.menu.MenuBar;

public class MainApplication extends Application {
	@Override
	public String getUrl() {
		return null;
	}

	@Override
	public void buildMenu(MenuBar menuBar) {
	}

	@Override
	public void buildContentPanel(Panel contentPanel) {
		Label label = new Label("");
		TextField textField = new TextField("");
		Button button = new Button("Send");

		button.addClickListener(l -> {
			label.setCaption(textField.getValue());
		});

		contentPanel.addComponent(label);
		contentPanel.addComponent(textField);
		contentPanel.addComponent(button);
	}
}