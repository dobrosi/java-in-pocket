package hu.dobrosi.javainpocket.ui;

import hu.dobrosi.javainpocket.javascript.JQueryBuilder;

public class Image extends Component {
	private String url;

	public Image(String url) {
		super();
		setUrl(url);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		css("background-image", "url('" + url + "')");
		css("background-repeat", "no-repeat");
		css("background-position", "center");
		this.url = url;
	}

	@Override
	public void create() {
		JQueryBuilder.call("o", "$('#nullPanel')", "append", "<div id='" + getId() + "' src='" + getUrl() + "'></div>");
	}
}