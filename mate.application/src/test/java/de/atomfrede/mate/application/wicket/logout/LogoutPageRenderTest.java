package de.atomfrede.mate.application.wicket.logout;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.atomfrede.mate.application.wicket.WicketPageRenderTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:WEB-INF/applicationContext.xml" })
public class LogoutPageRenderTest extends WicketPageRenderTest {

	@Test
	@DirtiesContext
	public void assertThatLogoutPageIsRendered() {
		tester.startPage(LogoutPage.class);
		tester.assertRenderedPage(LogoutPage.class);
	}
}
