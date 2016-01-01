package hu.dobrosi.javainpocket;

import java.io.IOException;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.reflections.Reflections;

@WebFilter("/event/*")
public class AppFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		Application app = searchApplication(request);

		if (app == null) {
//			chain.doFilter(request, response);
		} else {
		}
		response.getOutputStream().write("alert(19)".getBytes());

	}

	private Application searchApplication(ServletRequest request) {
		Reflections reflections = new Reflections("");
		Set<Class<? extends Application>> apps = reflections.getSubTypesOf(Application.class);

		apps.forEach(s -> System.out.println(s));
		return null;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}
}
