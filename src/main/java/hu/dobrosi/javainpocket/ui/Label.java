package hu.dobrosi.javainpocket.ui;

import hu.dobrosi.javainpocket.javascript.JQueryBuilder;

public class Label extends Component {
	private String caption;

	public Label(String caption) {
		super();
		this.caption = caption;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		JQueryBuilder.call(null, this, "html", caption);
		this.caption = caption;
	}
}
