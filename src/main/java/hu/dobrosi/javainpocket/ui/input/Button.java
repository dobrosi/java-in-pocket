package hu.dobrosi.javainpocket.ui.input;

public class Button extends InputComponent<Object> {
	public Button(String caption) {
		super(caption);
	}

	@Override
	public String getInputComponenType() {
		return "button";
	}

	@Override
	public void setCaption(String caption) {
		setValue(caption);
		super.setCaption(caption);
	}
}