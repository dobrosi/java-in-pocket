package hu.dobrosi.javainpocket.example;

import hu.dobrosi.javainpocket.Application;
import hu.dobrosi.javainpocket.ui.Label;
import hu.dobrosi.javainpocket.ui.Panel;
import hu.dobrosi.javainpocket.ui.input.Button;
import hu.dobrosi.javainpocket.ui.input.TextField;

public class MainApplication implements Application {
	@Override
	public void onLoad(Panel contentPanel) {
		Label label = new Label("Test label");
		TextField textField = new TextField("Test input");
		Button button = new Button("Send");

		button.addClickListener(l -> {
			label.setCaption(textField.getValue());
		});

		contentPanel.addComponent(label);
		contentPanel.addComponent(textField);
		contentPanel.addComponent(button);
	}
}