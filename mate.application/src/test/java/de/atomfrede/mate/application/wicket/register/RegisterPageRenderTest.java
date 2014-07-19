package de.atomfrede.mate.application.wicket.register;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.atomfrede.mate.application.wicket.WicketPageRenderTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:WEB-INF/applicationContext.xml" })
public class RegisterPageRenderTest extends WicketPageRenderTest {

	@Test
	@DirtiesContext
	public void assertThatRegisterPageCanBeRendered() {
		tester.startPage(RegisterPage.class);
		tester.assertRenderedPage(RegisterPage.class);
	}
	
}
