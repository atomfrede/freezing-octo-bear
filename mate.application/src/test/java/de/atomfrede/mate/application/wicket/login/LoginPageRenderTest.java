package de.atomfrede.mate.application.wicket.login;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.atomfrede.mate.application.wicket.WicketPageRenderTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:WEB-INF/applicationContext.xml" })
public class LoginPageRenderTest extends WicketPageRenderTest {

	@Test
	@DirtiesContext
	public void assertThatLoginPageIsRendered() {
		tester.startPage(LoginPage.class);
		tester.assertRenderedPage(LoginPage.class);
	}
}
