package hu.dobrosi.javainpocket.ui;

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
		this.caption = caption;
	}
}
