package hu.dobrosi.javainpocket.ui;

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
}