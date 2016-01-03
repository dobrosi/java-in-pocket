package hu.dobrosi.javainpocket.example;

import java.util.Date;

import hu.dobrosi.javainpocket.Application;
import hu.dobrosi.javainpocket.javascript.Browser;
import hu.dobrosi.javainpocket.ui.Label;
import hu.dobrosi.javainpocket.ui.ListView;
import hu.dobrosi.javainpocket.ui.Panel;
import hu.dobrosi.javainpocket.ui.Panel.Layout;
import hu.dobrosi.javainpocket.ui.input.Button;
import hu.dobrosi.javainpocket.ui.input.TextField;

public class TestApplication implements Application {
	@Override
	public void onLoad(Panel contentPanel) {
		
		Label label = new Label("Test label");
		TextField textField = new TextField("Test input", "Test input value");
		Button button = new Button("Send");
		Button button1 = new Button("Show");
		Button button2 = new Button("Hide");
		Button button3 = new Button("Enable");
		Button button4 = new Button("Disable");
		Panel mainPanel = new Panel(Layout.HORIZONTAL);
		ListView listView = new ListView();
		
		Label label1 = new Label("Label1");
		Label label2 = new Label("Label2");
			
		contentPanel.addComponent(label);
		contentPanel.addComponent(textField);
		contentPanel.addComponent(button);
		contentPanel.addComponent(button1);
		contentPanel.addComponent(button2);
		contentPanel.addComponent(button3);
		contentPanel.addComponent(button4);
		contentPanel.addComponent(mainPanel);
		contentPanel.addComponent(listView);
		
		mainPanel.addComponent(label1);
		mainPanel.addComponent(label2);
		
		mainPanel.setWidth("500px");
		label1.setWidth("150px");
		label2.setWidth("150px");

		button.addClickListener(l -> {
			label.setCaption(textField.getValue());
			label.setBackgroundColor("gray");
			label.setTextColor("white");
			Browser.setTitle("Test application " + new Date());
		});
		
		button1.addClickListener(l -> textField.setVisible(true));
		button2.addClickListener(l -> textField.setVisible(false));
		button3.addClickListener(l -> {
			textField.setEnable(true);
			button.setEnable(true);
			label1.setVisible(true);
		});
		button4.addClickListener(l -> {
			textField.setEnable(false);
			button.setEnable(false);
			label1.setVisible(false);
		});
		
		listView.addItem("Alpha");
		listView.addItem("Beta");
		listView.addItem("<b>Gamma</b><p>test</p>");
		listView.addItem("Delta");
		listView.addItem("Omega");
	}
}