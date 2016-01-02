package hu.dobrosi.javainpocket.ui;

import java.util.ArrayList;
import java.util.List;

import hu.dobrosi.javainpocket.JQueryBuilder;
import hu.dobrosi.javainpocket.ui.input.Button;
import hu.dobrosi.javainpocket.ui.input.TextField;
import hu.dobrosi.javainpocket.ui.listener.ClickListener;
import hu.dobrosi.javainpocket.ui.listener.Listener;

public class Component {
	private List<Component> components = new ArrayList<>();

	private List<Listener> listeners = new ArrayList<>();

	public Object getId() {
		return hashCode();
	}

	public void addComponent(Component component) {
		components.add(component);
		Object cid = component.getId();
		if (component instanceof Label) {
			Label l = (Label) component;
			JQueryBuilder.call(this, "append", "<div id='" + cid + "'>" + l.getCaption() + "</div>");
		} else if (component instanceof Button) {
			Button b = (Button) component;
			JQueryBuilder.call(this, "append", "<div id='" + cid + "'><input type='button' value='" + b.getCaption() + "'></div>");
			JQueryBuilder.call("#" + cid, "trigger", "create");
		} else if (component instanceof TextField) {
			TextField tf = (TextField) component;
			JQueryBuilder.call(this, "append", "<div id='" + cid + "'><input type='text' value='" + tf.getValue() + "'></div>");
			JQueryBuilder.call("#" + cid, "trigger", "create");
		}
	}

	public void addComponent(int index, Component component) {
		components.add(index, component);
	}

	public void removeComponent(Component component) {
		components.remove(component);
	}

	public void removeComponent(int index) {
		components.remove(index);
	}

	public void addListener(Listener listener) {
		listeners.add(listener);
	}

	public void removeListener(Listener listener) {
		listeners.remove(listener);
	}

	public void addClickListener(ClickListener listener) {
		listeners.add(listener);
	}
}
