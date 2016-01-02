package hu.dobrosi.javainpocket;

import hu.dobrosi.javainpocket.ui.Component;

public class JQueryBuilder {

	private static String javaScript = "";

	public static String call(String selector, String method, Object... args) {
		String res = "$(\"" + selector + "\")." + method + "(";
		int i = 0;
		for (Object arg : args) {
			if (arg instanceof Component) {
				res += getSelector((Component) arg);
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

	public static String call(Component object, String method, Object... args) {
		return call(getSelector(object), method, args);
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
