package hu.dobrosi.javainpocket.javascript;

public class Function {
	private String argumentPart;
	private String body;

	public Function(String argumentPart, String body) {
		this.argumentPart = argumentPart;
		this.body = body;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getArgumentPart() {
		return argumentPart;
	}

	public void setArgumentPart(String argumentPart) {
		this.argumentPart = argumentPart;
	}
}