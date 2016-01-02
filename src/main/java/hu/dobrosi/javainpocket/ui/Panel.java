package hu.dobrosi.javainpocket.ui;

public class Panel extends Component {
	public enum Layout {
		FIX_POSITION, HORIZONTAL, VERTICAL
	}

	private Layout layout;

	public Panel() {
		this(Layout.VERTICAL);
	}

	public Panel(Layout layout) {
		super();
		this.layout = layout;
	}

	public Layout getLayout() {
		return layout;
	}

	public void setLayout(Layout layout) {
		this.layout = layout;
	}
}
