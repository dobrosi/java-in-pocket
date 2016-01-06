package hu.dobrosi.javainpocket.ui;

import hu.dobrosi.javainpocket.javascript.JQueryBuilder;

public class Image extends Component {
	private String url;

	public Image(String url) {
		super();
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public void create() {
		JQueryBuilder.call("o", "$('#nullPanel')", "append", "<div><img id='" + getId() + "' src='" + getUrl() + "'></img></div>");
	}
}