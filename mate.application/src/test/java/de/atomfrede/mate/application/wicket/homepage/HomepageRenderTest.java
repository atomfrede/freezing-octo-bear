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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import de.atomfrede.mate.application.wicket.WicketApplication;
import de.atomfrede.mate.application.wicket.security.UserAuthModel;
import de.atomfrede.mate.application.wicket.security.UserSession;
import de.atomfrede.mate.domain.entities.user.User;
import de.atomfrede.mate.service.user.UserService;
import de.atomfrede.mate.service.user.UsernameAlreadyTakenException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:WEB-INF/applicationContext.xml" })
public class HomepageRenderTest {

	private WicketTester tester;

	@Autowired
	private ApplicationContext ctx;

	@Autowired
	private WicketApplication myWebApplication;

	@Autowired
	private UserService userService;


	@Autowired
	private SessionFactory sessionFactory;

	protected FlushMode flushMode = FlushMode.MANUAL;

	@Before
	public void setUp() throws UsernameAlreadyTakenException {
		 org.hibernate.Session session = getSession(this.sessionFactory);
	        TransactionSynchronizationManager.bindResource(sessionFactory,new SessionHolder(session));
		tester = new WicketTester(myWebApplication);
	}
	
	@After
	public void tearDown() {
		SessionHolder sessionHolder = (SessionHolder) TransactionSynchronizationManager.unbindResource(sessionFactory);
        closeSession(sessionHolder.getSession(), sessionFactory);
	}

	protected org.hibernate.Session getSession(SessionFactory sessionFactory) throws DataAccessResourceFailureException {
        org.hibernate.Session session = sessionFactory.openSession();
        FlushMode flushMode = this.flushMode;
        if (flushMode != null) {
            session.setFlushMode(flushMode);
        }
        return session;
    }
	
	protected void closeSession(org.hibernate.Session session, SessionFactory sessionFactory) {
        SessionFactoryUtils.closeSession(session);
    }
	
	@Test
	public void assertThatHomepageIsRendered()
			throws UsernameAlreadyTakenException {
		User fred = userService.createAdminUser("fred", "fred", "fred", "fred",
				"fred");
		Session session = tester.getSession();
		((UserSession<UserAuthModel>) session).setUser(new UserAuthModel(
				User.class, fred.getId()));
		tester.startPage(Homepage.class);
		tester.assertRenderedPage(Homepage.class);
	}
}
