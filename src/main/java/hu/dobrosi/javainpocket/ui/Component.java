package hu.dobrosi.javainpocket.ui;

import java.util.ArrayList;
import java.util.List;

import hu.dobrosi.javainpocket.ApplicationContextProvider;
import hu.dobrosi.javainpocket.javascript.Function;
import hu.dobrosi.javainpocket.javascript.JQueryBuilder;
import hu.dobrosi.javainpocket.ui.input.InputComponent;
import hu.dobrosi.javainpocket.ui.listener.ClickEvent;
import hu.dobrosi.javainpocket.ui.listener.ClickListener;
import hu.dobrosi.javainpocket.ui.panel.Panel;

public abstract class Component {
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

	protected void init() {
		ApplicationContextProvider.get().components.put(getId().toString(), this);
		create();
	}

	public abstract void create();

	public Object getId() {
		return "c" + hashCode();
	}
	
	public String getSelector() {
		return JQueryBuilder.getSelector(this);
	}

	protected Component getParent() {
		return parent;
	}

	protected void setParent(Component parent) {
		this.parent = parent;
	}

	public void addComponent(Component component) {
		addComponent(0, component);
	}

	public void addComponent(int index, Component component) {
		components.add(index, component);

		if (component.getParent() != null) {
			throw new RuntimeException("This component has already other parent.");
		}

		JQueryBuilder.call("o", "$('#nullPanel')", "detach", component.getSelector());
		//JQueryBuilder.call(null, component, "appendTo", this);
		if(index > 0) {
			JQueryBuilder.call(null, component, "insertIndex", this.getSelector(), index);
		} else {
			JQueryBuilder.call(null, component, "appendTo", this.getSelector());
		}

		if (component instanceof InputComponent) {
			JQueryBuilder.call(null, component, "change", new Function("o", "change(o,\"" + component.getId() + "\");"));
		}

		if (this instanceof Panel) {
			Panel p = (Panel) this;
			if (Panel.Layout.HORIZONTAL.equals(p.getLayout())) {
				component.css("float", "left");
			} else {
				JQueryBuilder.call(null, this, "append", "<div style='clear: both;'></div>");
			}
		}

		JQueryBuilder.call(null, this, "trigger", "create");
		component.setParent(this);
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
		JQueryBuilder.call(null, this, "click", new Function("o", "click(o,\"" + getId() + "\");"));
		clickListeners.add(clickListener);
	}

	public void removeClickListener(ClickListener clickListener) {
		clickListeners.remove(clickListener);
	}

	public void onClick(ClickEvent clickEvent) {
		clickListeners.forEach(cl -> cl.onClick(clickEvent));
	}

	public void css(String name, String value) {
		JQueryBuilder.css(this, name, value);
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
		// JQueryBuilder.call(null, this, visible ? "show" : "hide");
		JQueryBuilder.call(null, this, "css", "display", visible ? "block" : "none");
		this.visible = visible;
	}
}
