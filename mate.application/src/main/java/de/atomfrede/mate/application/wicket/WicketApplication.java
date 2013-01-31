package de.atomfrede.mate.application.wicket;

import java.io.IOException;
import java.util.Properties;

import org.apache.wicket.Application;
import org.apache.wicket.Page;
import org.apache.wicket.Session;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.wicketstuff.annotation.scan.AnnotatedMountScanner;

import de.agilecoders.wicket.Bootstrap;
import de.agilecoders.wicket.settings.BootstrapSettings;
import de.agilecoders.wicket.settings.BootswatchThemeProvider;
import de.agilecoders.wicket.settings.ThemeProvider;
import de.atomfrede.mate.application.wicket.homepage.Homepage;
import de.atomfrede.mate.application.wicket.login.LoginPage;
import de.atomfrede.mate.application.wicket.security.ISecureApplication;
import de.atomfrede.mate.application.wicket.security.SimpleUserAuthorizationStrategy;
import de.atomfrede.mate.application.wicket.security.UserAuthModel;
import de.atomfrede.mate.application.wicket.security.UserSession;

@Component(value = "wicketApplication")
public class WicketApplication extends WebApplication implements
		ISecureApplication {

	private Properties properties;

	@Autowired
	private ApplicationContext mApplicationContext;
	
	public WicketApplication() {
	}

	public static WicketApplication get() {
		return (WicketApplication) Application.get();
	}

	@Override
	protected void init() {
		super.init();
		configureBootstrap();

		initSpring();

		// customized auth strategy
		getSecuritySettings().setAuthorizationStrategy(
				new SimpleUserAuthorizationStrategy());

		// don't throw exceptions for missing translations
		getResourceSettings().setThrowExceptionOnMissingResource(false);

		// enable ajax debug etc.
		getDebugSettings().setDevelopmentUtilitiesEnabled(true);
		

		new AnnotatedMountScanner().scanPackage(
				"de.atomfrede.mate.application.*").mount(this);
		
//		addDummyUsers();
	}

	protected void initSpring() {
		// Initialize Spring Dependency Injection
		getComponentInstantiationListeners().add(
				new SpringComponentInjector(this));
	}

	@Override
	public Class<? extends Page> getHomePage() {
		return Homepage.class;
	}

	/**
	 * @return used configuration properties
	 */
	public Properties getProperties() {
		return properties;
	}

	/**
	 * loads all configuration properties from disk
	 * 
	 * @return configuration properties
	 */
	private Properties loadProperties() {
		Properties properties = new Properties();
		try {
			properties.load(getClass()
					.getResourceAsStream("/config.properties"));
		} catch (IOException e) {
			throw new WicketRuntimeException(e);
		}
		return properties;
	}

	private void configureBootstrap() {
		final BootstrapSettings settings = new BootstrapSettings();
		settings.useJqueryPP(false).useModernizr(false).useResponsiveCss(true)
				.setJsResourceFilterName("footer-container");

		// reactivate if new less4j version is available:
		// settings.getBootstrapLessCompilerSettings().setUseLessCompiler(usesDevelopmentConfig());
		// settings.getBootstrapLessCompilerSettings().setLessCompiler(new
		// Less4JCompiler());

		ThemeProvider themeProvider = new BootswatchThemeProvider() {
			{
				defaultTheme("united");
			}
		};
		settings.setThemeProvider(themeProvider);

		Bootstrap.install(this, settings);
	}

	@Override
	public Class<? extends Page> getLoginPage() {
		return LoginPage.class;
	}

	@Override
	public Session newSession(Request request, Response response) {
		System.out.println("Getting new session ");
		UserSession<UserAuthModel> session = new UserSession<UserAuthModel>(request);
		System.out.println("Session is "+session.getUser());
		
		return session;
	}
	

}
