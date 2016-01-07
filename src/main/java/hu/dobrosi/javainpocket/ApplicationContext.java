package hu.dobrosi.javainpocket;

import java.util.HashMap;
import java.util.Map;

import hu.dobrosi.javainpocket.ui.Component;

public class ApplicationContext {
	public Map<String, Component> components = new HashMap<>();
	private String javaScript;

	public void appendJavaScript(String js) {
		javaScript += js;
	}

	public String deleteJavaScript() {
		String res = javaScript;
		javaScript = "";
		return res;
	}
}