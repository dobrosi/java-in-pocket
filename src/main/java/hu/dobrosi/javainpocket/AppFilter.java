package hu.dobrosi.javainpocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.reflections.Reflections;

import hu.dobrosi.javainpocket.ui.Panel;
import hu.dobrosi.javainpocket.ui.Panel.Layout;

@WebFilter("/event/*")
public class AppFilter implements Filter {
	private Class<? extends Application> appClass;

	private Map<String, Application> apps = new HashMap<>();

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;

		Application app = searchApplication(httpServletRequest);

		String type = httpServletRequest.getParameter("type");

		if (app == null) {
			// chain.doFilter(request, response);
		} else {
			String js;
			if ("init".equals(type)) {
				Panel contentPanel = new Panel(Layout.FIX_POSITION) {
					@Override
					public Object getId() {
						return "contentPanel";
					}
				};
				app.onLoad(contentPanel);
				js = JQueryBuilder.getJavaScript();
			} else if ("click".equals(type)) {
				js = "alert('onClick')";
			} else if ("change".equals(type)) {
				js = "alert('onChange')";
			} else {
				js = "alert('hiba')";
			}
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
