package hu.dobrosi.javainpocket.javascript;

public class Browser {
	public static void setTitle(String title) {
		JQueryBuilder.call("document.title='" + title + "';");
	}

	public static void alert(String message) {
		JQueryBuilder.call("alert('" + message + "');");
	}
}