package de.atomfrede.mate.application.wicket.homepage;

import org.apache.wicket.Session;
import org.apache.wicket.util.tester.WicketTester;
import org.hibernate.FlushMode;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.orm.hibernate4.SessionHolder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import de.atomfrede.mate.application.wicket.WicketApplication;
import de.atomfrede.mate.application.wicket.WicketPageRenderTest;
import de.atomfrede.mate.application.wicket.security.UserAuthModel;
import de.atomfrede.mate.application.wicket.security.UserSession;
import de.atomfrede.mate.domain.entities.user.User;
import de.atomfrede.mate.service.user.UserService;
import de.atomfrede.mate.service.user.UsernameAlreadyTakenException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:WEB-INF/applicationContext.xml" })
public class HomepageRenderTest extends WicketPageRenderTest {

	@Test
	@DirtiesContext
	public void assertThatHomepageIsRendered()
			throws UsernameAlreadyTakenException {
		User fred = userService.createAdminUser("fred-homepage-render", "fred", "fred", "fred",
				"fred");
		Session session = tester.getSession();
		((UserSession<UserAuthModel>) session).setUser(new UserAuthModel(
				User.class, fred.getId()));
		tester.startPage(Homepage.class);
		tester.assertRenderedPage(Homepage.class);
	}
}
