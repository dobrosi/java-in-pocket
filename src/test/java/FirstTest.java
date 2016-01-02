import java.util.Set;

import org.junit.Test;
import org.reflections.Reflections;

import hu.dobrosi.javainpocket.Application;

public class FirstTest {

	@Test
	public void test() {
		Reflections reflections = new Reflections("hu.dobrosi");
		Set<Class<? extends Application>> apps = reflections.getSubTypesOf(Application.class);
		System.out.println(apps.size());
	}
}