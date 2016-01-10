package hu.dobrosi.javainpocket;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.reflections.Reflections;

import hu.dobrosi.javainpocket.ui.Component;
import hu.dobrosi.javainpocket.ui.Panel;
import hu.dobrosi.javainpocket.ui.Panel.Layout;
import hu.dobrosi.javainpocket.ui.input.InputComponent;
import hu.dobrosi.javainpocket.ui.listener.ChangeEvent;
import hu.dobrosi.javainpocket.ui.listener.ClickEvent;

@WebFilter("/*")
public class AppFilter implements Filter {
	private Class<? extends Application> appClass;

	private Map<String, Application> apps = new HashMap<>();

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		try {
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;

			String contextPath = httpServletRequest.getContextPath();
			String uri = httpServletRequest.getRequestURI();
			uri = uri.replaceAll(contextPath, "");

			if (uri.contains("/event")) {
				event(response, httpServletRequest);
			} else {
				ServletContext context = request.getServletContext();
				resource(uri, context, response);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			response.getOutputStream().write(("error('" + exception.getLocalizedMessage() + "');").getBytes());
		}
	}

	private void resource(String uri, ServletContext context, ServletResponse response) {
		System.out.println(uri);
		if (uri.replace("/", "").equals("")) {
			uri = "index.html";
		}
		InputStream resourceContent = this.getClass().getClassLoader().getResourceAsStream(uri);
		try {
			IOUtils.copy(resourceContent, response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void event(ServletResponse response, HttpServletRequest httpServletRequest) throws IOException {
		Application app = searchApplication(httpServletRequest);

		if (app == null) {
			// chain.doFilter(request, response);

		} else {
			String sessionId = httpServletRequest.getSession().getId();
			ApplicationContext ctx = ApplicationContextProvider.get(sessionId);

			String type = httpServletRequest.getParameter("type");
			String cid = httpServletRequest.getParameter("cid");
			cid = cid == null ? null : cid;
			Component component = cid == null ? null : ctx.components.get(cid);

			String js;
			if ("init".equals(type)) {
				ctx.init();
				Panel contentPanel = new Panel(Layout.FIX_POSITION) {
					@Override
					public Object getId() {
						return "contentPanel";
					}

					@Override
					public void create() {
					}
				};
				app.onLoad(contentPanel);
			} else if ("click".equals(type)) {
				ClickEvent clickEvent = new ClickEvent();
				component.onClick(clickEvent);
			} else if ("change".equals(type)) {
				InputComponent<Object> inputComponent = (InputComponent) component;
				ChangeEvent<Object> changeEvent = new ChangeEvent<>();
				changeEvent.oldValue = inputComponent.getValue();
				changeEvent.newValue = httpServletRequest.getParameter("newValue");
				System.out.println("newValue: " + changeEvent.newValue);
				inputComponent.onChange(changeEvent);
			} else {
				js = "alert('hiba')";
			}
			js = ctx.deleteJavaScript();
			System.out.println(js);
			response.getOutputStream().write(js.getBytes());
		}
	}

	private Application searchApplication(HttpServletRequest httpServletRequest) {

		String sessionId = httpServletRequest.getSession().getId();
		Application app = apps.get(sessionId);
		if (app == null) {
			Class<? extends Application> appClass = getApplicationClass();
			try {
				app = appClass.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
				return null;
			}
			apps.put(sessionId, app);
		}
		return app;
	}

	private Class<? extends Application> getApplicationClass() {
		if (appClass == null) {
			Reflections reflections = new Reflections("");
			Set<Class<? extends Application>> apps = reflections.getSubTypesOf(Application.class);
			if (apps.size() == 0) {
				throw new RuntimeException("Application class not found.");
			} else if (apps.size() > 1) {
				throw new RuntimeException("Too many application class are implemented. You should implement only one instance.");
			}
			return appClass = apps.iterator().next();
		}
		return appClass;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}
}
