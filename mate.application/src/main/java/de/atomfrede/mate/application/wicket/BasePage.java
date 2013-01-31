package de.atomfrede.mate.application.wicket;

import java.util.Properties;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.GenericWebPage;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.CssResourceReference;

import de.agilecoders.wicket.Bootstrap;
import de.agilecoders.wicket.markup.html.bootstrap.image.IconType;
import de.agilecoders.wicket.markup.html.bootstrap.navbar.Navbar;
import de.agilecoders.wicket.markup.html.bootstrap.navbar.NavbarButton;
import de.agilecoders.wicket.markup.html.bootstrap.navbar.NavbarComponents;
import de.atomfrede.mate.application.wicket.base.AbstractBasePage;
import de.atomfrede.mate.application.wicket.footer.Footer;
import de.atomfrede.mate.application.wicket.homepage.Homepage;
import de.atomfrede.mate.application.wicket.security.UserAuthModel;
import de.atomfrede.mate.application.wicket.security.UserSession;

public abstract class BasePage<T> extends GenericWebPage<T> {

	public BasePage() {
		super();

		commonInit(new PageParameters());
	}

	/**
	 * Construct.
	 * 
	 * @param model
	 *            The model to use for this page
	 */
	public BasePage(IModel<T> model) {
		super(model);

		commonInit(new PageParameters());
	}

	/**
	 * Construct.
	 * 
	 * @param parameters
	 *            current page parameters
	 */
	public BasePage(PageParameters parameters) {
		super(parameters);

		commonInit(parameters);
	}

	/**
	 * @return application properties
	 */
	public Properties getProperties() {
		return WicketApplication.get().getProperties();
	}

	private void commonInit(PageParameters pageParameters) {

		add(newNavbar("navbar"));
		add(new Footer("footer"));
	}

	protected Navbar newNavbar(String markupId) {
		Navbar navbar = new Navbar(markupId);

		navbar.setPosition(Navbar.Position.TOP);

		// show brand name
		navbar.brandName(Model.of("Mate Tracker"));
		navbar.addComponents(NavbarComponents.transform(
				Navbar.ComponentPosition.LEFT,
				new NavbarButton<Homepage>(Homepage.class, Model.of("Home")).setIconType(IconType.home)
				));
		
		return navbar;
	}

	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		response.render(CssHeaderItem.forReference(new CssResourceReference(BasePage.class, "base-content.css")));
		response.render(CssHeaderItem.forReference(new CssResourceReference(AbstractBasePage.class, "base.css")));
		Bootstrap.renderHead(response);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public UserSession<UserAuthModel> getSession() {
		return (UserSession<UserAuthModel>)super.getSession();
	}

	public UserAuthModel getUser() {
		return getSession().getUser();
	}
}
