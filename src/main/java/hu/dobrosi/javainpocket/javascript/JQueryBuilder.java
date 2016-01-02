package hu.dobrosi.javainpocket.javascript;

import hu.dobrosi.javainpocket.ui.Component;

public class JQueryBuilder {

	private static String javaScript = "";

	public static String call(String script) {
		javaScript += script;
		return script;
	}
	
	public static String call(String targetVariable, String object, String method, Object... args) {
		String res = "";
		if (targetVariable != null) {
			res += targetVariable + "=";
		}
		res += object + "." + method + "(";
		int i = 0;
		for (Object arg : args) {
			if (arg instanceof Component) {
				res += getSelector((Component) arg);
			} else if (arg instanceof Function) {
				Function f = (Function) arg;
				res += "function(" + f.getArgumentPart() + " ){" + f.getBody() + "}";
			} else if (arg instanceof String) {
				res += "\"" + arg + "\"";
			} else if (arg instanceof String) {
				res += arg;
			}
			i++;
			if (i < args.length) {
				res += ",";
			}
		}
		res += ");";
		javaScript += res;
		return res;
	}

	public static String call(String targetVariable, Component object, String method, Object... args) {
		return call(targetVariable, getQuery(getSelector(object)), method, args);
	}


	public static String css(Component object, String name, String value) {
		return call(null, object, "css", name, value);
	}
	
	private static String getQuery(String selector) {
		return "$(\"" + selector + "\")";
	}

	public static String getSelector(Component object) {
		return "#" + object.getId();
	}

	public static String getJavaScript() {
		String res = javaScript;
		javaScript = "";
		return res;
	}
}
