package hu.dobrosi.javainpocket.ui.input;

public class Button extends InputComponent<Object> {
	public Button(String value) {
		super(null, value);
	}

	@Override
	public String getInputComponenType() {
		return "button";
	}
}