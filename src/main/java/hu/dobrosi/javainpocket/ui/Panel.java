package hu.dobrosi.javainpocket.ui;

import java.util.Arrays;

import hu.dobrosi.javainpocket.javascript.JQueryBuilder;

public class Panel extends Component {
	public enum Layout {
		FIX_POSITION, HORIZONTAL, VERTICAL
	}

	private Layout layout;

	public Panel() {
		this(Layout.VERTICAL);
	}

	public Panel(Component... components) {
		this(Layout.VERTICAL, components);

	}

	public Panel(Layout layout, Component... components) {
		super();
		setLayout(layout);

		Arrays.asList(components).forEach(c -> this.addComponent(c));
	}

	public Layout getLayout() {
		return layout;
	}

	public void setLayout(Layout layout) {
		this.layout = layout;
	}

	@Override
	public void create() {
		JQueryBuilder.call("o", "$('#nullPanel')", "append", "<div id='" + getId() + "'></div>");
		//css("display", "clear");
	}
}
