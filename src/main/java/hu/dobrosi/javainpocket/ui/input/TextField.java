package hu.dobrosi.javainpocket.ui.input;

public class TextField extends InputComponent<String> {

	public TextField(String caption) {
		super(caption);
	}

	public TextField(String caption, String defaultValue) {
		super(caption, defaultValue);
	}
}