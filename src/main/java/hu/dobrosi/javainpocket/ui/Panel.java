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
		this.layout = layout;
		
		JQueryBuilder.call("o", "$('#nullPanel')", "append", "<div><div id='" + getId() + "'></div></div>");
		JQueryBuilder.call(null, this, "css", "display", "clear");

		Arrays.asList(components).forEach(c -> this.addComponent(c));
	}

	public Layout getLayout() {
		return layout;
	}

	public void setLayout(Layout layout) {
		this.layout = layout;
	}
}
