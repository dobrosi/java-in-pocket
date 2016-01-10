package hu.dobrosi.javainpocket.ui.input;

public class PasswordField extends TextField {
	public PasswordField(String caption, String defaultValue) {
		super(caption, defaultValue);
	}

	public PasswordField(String caption) {
		this(caption, null);
	}

	@Override
	public String getInputComponenType() {
		return "password";
	}
}