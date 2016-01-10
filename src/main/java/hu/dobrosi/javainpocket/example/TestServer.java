package hu.dobrosi.javainpocket.example;

import java.util.EnumSet;

import javax.servlet.DispatcherType;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import hu.dobrosi.javainpocket.AppFilter;

public class TestServer {

	public static void main(String[] args) throws Exception {
		Server server = new Server(8080);
		
		WebAppContext context = new WebAppContext();
		
		context.setContextPath("/");
		context.setResourceBase("/home/andris/git/java-in-pocket/src/main/resources");
		context.addFilter(AppFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));
		server.setHandler(context);

		server.start();
		server.join();
	}

}
