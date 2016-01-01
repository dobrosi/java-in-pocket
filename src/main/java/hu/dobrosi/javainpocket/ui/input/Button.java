package hu.dobrosi.javainpocket.ui.input;

import hu.dobrosi.javainpocket.ui.Component;

public class Button extends Component {
	private String caption;

	public Button(String caption) {
		super();
		this.caption = caption;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}
}
