package hu.dobrosi.javainpocket.ui;

import java.util.ArrayList;
import java.util.List;

import hu.dobrosi.javainpocket.ui.listener.ClickListener;
import hu.dobrosi.javainpocket.ui.listener.Listener;

public class Component {
	private List<Component> components = new ArrayList<>();

	private List<Listener> listeners = new ArrayList<>();

	public void addComponent(Component component) {
		components.add(component);
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
