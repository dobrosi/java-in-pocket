package hu.dobrosi.javainpocket.ui;

import hu.dobrosi.javainpocket.javascript.JQueryBuilder;

public class Label extends Component {
	private String value;

	public Label(String value) {
		super();
		this.value = value;
		init();
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		JQueryBuilder.call(null, this, "html", value);
		this.value = value;
	}

	@Override
	public void create() {
		JQueryBuilder.call("o", "$('#nullPanel')", "append", "<label id='" + getId() + "'>" + getValue() + "</label>");
	}

	public void setFor(String name) {
		JQueryBuilder.call(null, this, "attr", "for", name);
	}
}