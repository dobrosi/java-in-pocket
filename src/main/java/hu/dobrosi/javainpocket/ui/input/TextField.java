package hu.dobrosi.javainpocket.ui.input;

import hu.dobrosi.javainpocket.ui.Component;

public class TextField extends Component {
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public TextField(String defaultValue) {
		super();
		this.value = defaultValue;
	}

}
