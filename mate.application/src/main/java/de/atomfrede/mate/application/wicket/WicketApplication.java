package de.atomfrede.mate.application.wicket;

import java.io.IOException;
import java.util.Properties;

import org.apache.wicket.Application;
import org.apache.wicket.Page;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.Session;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.https.HttpsConfig;
import org.apache.wicket.protocol.https.HttpsMapper;
import org.apache.wicket.protocol.https.Scheme;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.wicketstuff.annotation.scan.AnnotatedMountScanner;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.settings.BootstrapSettings;
import de.agilecoders.wicket.core.settings.ThemeProvider;
import de.agilecoders.wicket.themes.settings.BootswatchThemeProvider;
import de.atomfrede.mate.application.wicket.homepage.Homepage;
import de.atomfrede.mate.application.wicket.login.LoginPage;
import de.atomfrede.mate.application.wicket.security.ISecureApplication;
import de.atomfrede.mate.application.wicket.security.SimpleUserAuthorizationStrategy;
import de.atomfrede.mate.application.wicket.security.UserAuthModel;
import de.atomfrede.mate.application.wicket.security.UserSession;

@Component(value = "wicketApplication")
public class WicketApplication extends WebApplication implements
		ISecureApplication, ApplicationContextAware {

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

		setRootRequestMapper(new HttpsMapper(getRootRequestMapper(),
				new HttpsConfig(8080, 8443)) {

			@Override
			protected Scheme getDesiredSchemeFor(Class pageClass) {
				if (getConfigurationType() == RuntimeConfigurationType.DEVELOPMENT) {
					return Scheme.HTTP;
				} else {
					return Scheme.HTTPS;
				}
			}
		});

		// addDummyUsers();
	}

	protected void initSpring() {
		// Initialize Spring Dependency Injection
		if(mApplicationContext == null) {
			getComponentInstantiationListeners().add(
					new SpringComponentInjector(this));
		} else {
			getComponentInstantiationListeners().add(
					new SpringComponentInjector(this, mApplicationContext, true));
		}
		
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
		// settings.useJqueryPP(false).useModernizr(false).useResponsiveCss(true)
		// .setJsResourceFilterName("footer-container");

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
		UserSession<UserAuthModel> session = new UserSession<UserAuthModel>(
				request);
		return session;
	}

	@Override
	public RuntimeConfigurationType getConfigurationType() {
		return RuntimeConfigurationType.DEVELOPMENT;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.mApplicationContext = applicationContext;
		
	}
}
