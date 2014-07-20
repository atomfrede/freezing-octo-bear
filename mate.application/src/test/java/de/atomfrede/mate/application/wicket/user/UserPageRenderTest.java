package de.atomfrede.mate.application.wicket.user;

import org.apache.wicket.Session;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.atomfrede.mate.application.wicket.WicketPageRenderTest;
import de.atomfrede.mate.application.wicket.security.UserAuthModel;
import de.atomfrede.mate.application.wicket.security.UserSession;
import de.atomfrede.mate.domain.entities.user.User;
import de.atomfrede.mate.service.user.UsernameAlreadyTakenException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:WEB-INF/applicationContext.xml" })
public class UserPageRenderTest extends WicketPageRenderTest {

	@Test
	@DirtiesContext
	public void assertThatUserListPageCanBeRendered() throws UsernameAlreadyTakenException {
		User fred = userService.createAdminUser("fred-user-page", "fred", "fred", "fred",
				"fred");
		Session session = tester.getSession();
		((UserSession<UserAuthModel>) session).setUser(new UserAuthModel(
				User.class, fred.getId()));
		tester.startPage(UserPage.class);
		tester.assertRenderedPage(UserPage.class);
	}
}
