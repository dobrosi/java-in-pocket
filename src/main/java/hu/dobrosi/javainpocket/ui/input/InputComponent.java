package hu.dobrosi.javainpocket.ui.input;

import java.util.ArrayList;
import java.util.List;

import hu.dobrosi.javainpocket.javascript.Function;
import hu.dobrosi.javainpocket.javascript.JQueryBuilder;
import hu.dobrosi.javainpocket.ui.Component;
import hu.dobrosi.javainpocket.ui.listener.ChangeEvent;
import hu.dobrosi.javainpocket.ui.listener.ChangeListener;

public abstract class InputComponent<T> extends Component {
	private List<ChangeListener> changeListeners = new ArrayList<>();

	private String caption;

	private T value;

	private boolean enable;

	protected Object labelId;

	protected Object inputId;

	public InputComponent() {
		super();
	}


	public InputComponent(String caption) {
		this(caption, null);
	}

	public InputComponent(String caption, T defaultValue) {
		this();
		this.caption = caption;
		this.value = defaultValue;
		init();
	}

	
	@Override
	public void create() {
		Object v = this.getValue() == null ? "" : this.getValue();
		String name = "name_" + getId();
		labelId = "label_" + getId();
		inputId = "input_" + getId();
		if(getCaption() != null) {
			JQueryBuilder.call("o", "$('#nullPanel')", "append", "<div id='" + this.getId() + "' data-role='fieldcontain'><label id='" + labelId + "' for='" + name + "'>" +  getCaption() + "</label><input id='" + inputId + "' name='" + name + "' type='" + getInputComponenType() + "' value='" + v + "'></input></div>");
		} else {
			inputId = getId();
			JQueryBuilder.call("o", "$('#nullPanel')", "append", "<input id='" + inputId + "' name='" + name + "' type='" + getInputComponenType() + "' value='" + v + "'></input>");
		}
	}

	public abstract String getInputComponenType();

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		JQueryBuilder.call(null, JQueryBuilder.getQuery("#" + labelId), "html", caption);
		this.caption = caption;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		JQueryBuilder.call(null, JQueryBuilder.getQuery("#" + inputId), "val", value);
		this.value = value;
	}

	public void addChangeListener(ChangeListener changeListener) {
		JQueryBuilder.call(null, this, "change", new Function("o", "change(o, \"" + JQueryBuilder.getQuery("#" + inputId) + "\");"));
		changeListeners.add(changeListener);
	}

	public void removeChangeListener(ChangeListener changeListener) {
		changeListeners.remove(changeListener);
	}

	public void onChange(ChangeEvent<T> changeEvent) {
		value = changeEvent.newValue;
		changeListeners.forEach(cl -> cl.onChange(changeEvent));
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		JQueryBuilder.call(null, this, "prop", "disabled", !enable);
		this.enable = enable;
	}
}