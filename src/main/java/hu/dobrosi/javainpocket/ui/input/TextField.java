package hu.dobrosi.javainpocket.ui.input;

public class TextField extends InputComponent<String> {
	public TextField(String caption, String defaultValue) {
		super(caption, defaultValue);
	}

	public TextField(String caption) {
		this(caption, null);
	}

	@Override
	public String getInputComponenType() {
		return "text";
	}
}