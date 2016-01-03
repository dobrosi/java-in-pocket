package hu.dobrosi.javainpocket.ui;

import java.util.ArrayList;
import java.util.List;

import hu.dobrosi.javainpocket.ApplicationContext;
import hu.dobrosi.javainpocket.javascript.Function;
import hu.dobrosi.javainpocket.javascript.JQueryBuilder;
import hu.dobrosi.javainpocket.ui.input.Button;
import hu.dobrosi.javainpocket.ui.input.InputComponent;
import hu.dobrosi.javainpocket.ui.input.TextField;
import hu.dobrosi.javainpocket.ui.listener.ClickEvent;
import hu.dobrosi.javainpocket.ui.listener.ClickListener;

public class Component {
	private Component parent;

	private List<Component> components = new ArrayList<>();

	private List<ClickListener> clickListeners = new ArrayList<>();

	private String width;

	private String height;

	private String backgroundColor;

	private String textColor;

	private String left;

	private String top;

	private boolean visible;

	private boolean enable;

	public Component() {
		ApplicationContext.components.put(getId(), this);
	}

	public Object getId() {
		return hashCode();
	}

	protected Component getParent() {
		return parent;
	}

	protected void setParent(Component parent) {
		this.parent = parent;
	}

	public void addComponent(Component component) {
		if (component.getParent() != null) {
			throw new RuntimeException("This component has already other parent.");
		}
		Object cid = component.getId();
		if (component instanceof Panel) {
			Panel p = (Panel) component;
			JQueryBuilder.call("o", this, "append", "<div id='" + cid + "'></div>");
			JQueryBuilder.call(null, p, "css", "display", "clear");
		} else if (component instanceof Label) {
			Label l = (Label) component;
			JQueryBuilder.call("o", this, "append", "<div id='" + cid + "'>" + l.getCaption() + "</div>");
		} else if (component instanceof Button) {
			Button b = (Button) component;
			JQueryBuilder.call("o", this, "append", "<input id='" + cid + "' type='button' value='" + b.getCaption() + "'></input>");
			JQueryBuilder.call(null, this, "trigger", "create");
		} else if (component instanceof TextField) {
			TextField tf = (TextField) component;
			JQueryBuilder.call("o", this, "append", "<input id='" + cid + "' type='text' value='" + tf.getValue() + "'></input>");
			JQueryBuilder.call(null, this, "trigger", "create");
		}
		if (component instanceof InputComponent) {
			JQueryBuilder.call(null, this, "change", new Function("o", "change(o," + component.getId() + ");"));
		}
		if (this instanceof Panel) {
			Panel p = (Panel) this;
			if (Panel.Layout.HORIZONTAL.equals(p.getLayout())) {
				component.css("float", "left");
			}
		}
		component.setParent(this);
		components.add(component);
	}

	public void css(String name, String value) {
		JQueryBuilder.css(this, name, value);
	}

	public void addComponent(int index, Component component) {
		components.add(index, component);
	}

	public void removeComponent(Component component) {
		component.setParent(null);
		components.remove(component);
	}

	public void removeComponent(int index) {
		components.remove(index);
	}

	public void addClickListener(ClickListener clickListener) {
		JQueryBuilder.call(null, this, "click", new Function("o", "click(o," + getId() + ");"));
		clickListeners.add(clickListener);
	}

	public void removeClickListener(ClickListener clickListener) {
		clickListeners.remove(clickListener);
	}

	public void onClick(ClickEvent clickEvent) {
		clickListeners.forEach(cl -> cl.onClick(clickEvent));
	}

	public String getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor) {
		css("background-color", backgroundColor);
		this.backgroundColor = backgroundColor;
	}

	public String getTextColor() {
		return textColor;
	}

	public void setTextColor(String textColor) {
		css("color", textColor);
		this.textColor = textColor;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		css("width", width);
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		css("height", height);
		this.height = height;
	}

	public String getLeft() {
		return left;
	}

	public void setLeft(String left) {
		this.left = left;
	}

	public String getTop() {
		return top;
	}

	public void setTop(String top) {
		this.top = top;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		JQueryBuilder.call(null, this, visible ? "show" : "hide");
		this.visible = visible;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		JQueryBuilder.call(null, this, "prop", "disabled", !enable);
		this.enable = enable;
	}
}
