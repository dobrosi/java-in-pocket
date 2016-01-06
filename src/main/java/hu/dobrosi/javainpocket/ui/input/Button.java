package hu.dobrosi.javainpocket.ui.input;

import hu.dobrosi.javainpocket.javascript.JQueryBuilder;

public class Button extends InputComponent<Object> {
	public Button(String caption) {
		super(caption);

		JQueryBuilder.call("o", "$('#nullPanel')", "append", "<input id='" + this.getId() + "' type='button' value='" + this.getCaption() + "'></input>");
	}
}