package hu.dobrosi.javainpocket.ui;

public class Panel extends Component {
	public enum Layout {
		FIX_POSITION, HORIZONTAL, VERTICAL
	}

	private Layout layout;

	public Panel(Layout layout) {
		super();
		this.layout = layout;
	}
}
