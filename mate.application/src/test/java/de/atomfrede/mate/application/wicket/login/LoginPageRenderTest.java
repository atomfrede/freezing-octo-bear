package de.atomfrede.mate.application.wicket.login;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.atomfrede.mate.application.wicket.WicketApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:WEB-INF/applicationContext.xml" })
public class LoginPageRenderTest {

	private WicketTester tester;

	@Autowired
	private ApplicationContext ctx;

	@Autowired
	private WicketApplication myWebApplication;

	@Before
	public void setUp() {
		tester = new WicketTester(myWebApplication);
	}

	@Test
	@DirtiesContext
	public void assertThatLoginPageIsRendered() {
		tester.startPage(LoginPage.class);
		tester.assertRenderedPage(LoginPage.class);
	}
}
