package hu.dobrosi.javainpocket.ui.input;

import java.util.ArrayList;
import java.util.List;

import hu.dobrosi.javainpocket.javascript.Function;
import hu.dobrosi.javainpocket.javascript.JQueryBuilder;
import hu.dobrosi.javainpocket.ui.Component;
import hu.dobrosi.javainpocket.ui.listener.ChangeEvent;
import hu.dobrosi.javainpocket.ui.listener.ChangeListener;

public class InputComponent<T> extends Component {
	private List<ChangeListener> changeListeners = new ArrayList<>();
	
	protected String caption;

	protected T value;

	public InputComponent() {
		super();
	}

	public InputComponent(String caption) {
		this();
		this.caption = caption;
	}

	public InputComponent(String caption, T defaultValue) {
		this(caption);
		this.value = defaultValue;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		JQueryBuilder.call(null, this, "val", value);
		this.value = value;
	}

	public void addChangeListener(ChangeListener changeListener) {
		JQueryBuilder.call(null, this, "change", new Function("o", "change(o, " + getId() + ");"));
		changeListeners.add(changeListener);
	}

	public void removeChangeListener(ChangeListener changeListener) {
		changeListeners.remove(changeListener);
	}

	public void onChange(ChangeEvent<T> changeEvent) {
		value = changeEvent.newValue;
		changeListeners.forEach(cl -> cl.onChange(changeEvent));
	}
}