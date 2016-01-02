package hu.dobrosi.javainpocket.ui.listener;

public class ChangeEvent<T> extends Event {
	public T oldValue;
	public T newValue;
}