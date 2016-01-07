package hu.dobrosi.javainpocket;

import java.util.HashMap;
import java.util.Map;

public class ApplicationContextProvider {
	private static Map<String, ApplicationContext> apps = new HashMap<>();

	private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

	public static ApplicationContext get() {
		return apps.get(threadLocal.get());
	}

	protected static ApplicationContext get(String sessionId) {
		ApplicationContext ctx = apps.get(sessionId);
		if (ctx == null) {
			apps.put(sessionId, ctx = new ApplicationContext());
		}
		threadLocal.set(sessionId);
		return ctx;
	}
}