package de.atomfrede.mate.application.wicket;

import java.util.Properties;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.GenericWebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.spring.injection.annot.SpringBean;

import de.agilecoders.wicket.Bootstrap;
import de.agilecoders.wicket.markup.html.bootstrap.button.ButtonBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.button.ButtonSize;
import de.agilecoders.wicket.markup.html.bootstrap.button.ButtonType;
import de.agilecoders.wicket.markup.html.bootstrap.button.TypedAjaxButton;
import de.agilecoders.wicket.markup.html.bootstrap.button.TypedButton;
import de.agilecoders.wicket.markup.html.bootstrap.button.TypedLink;
import de.agilecoders.wicket.markup.html.bootstrap.button.dropdown.MenuBookmarkablePageLink;
import de.agilecoders.wicket.markup.html.bootstrap.extensions.button.DropDownAutoOpen;
import de.agilecoders.wicket.markup.html.bootstrap.image.IconType;
import de.agilecoders.wicket.markup.html.bootstrap.navbar.ImmutableNavbarComponent;
import de.agilecoders.wicket.markup.html.bootstrap.navbar.Navbar;
import de.agilecoders.wicket.markup.html.bootstrap.navbar.NavbarButton;
import de.agilecoders.wicket.markup.html.bootstrap.navbar.NavbarComponents;
import de.agilecoders.wicket.markup.html.bootstrap.navbar.NavbarDropDownButton;
import de.atomfrede.mate.application.wicket.account.MyAccountPage;
import de.atomfrede.mate.application.wicket.base.AbstractBasePage;
import de.atomfrede.mate.application.wicket.footer.Footer;
import de.atomfrede.mate.application.wicket.homepage.Homepage;
import de.atomfrede.mate.application.wicket.login.LoginPage;
import de.atomfrede.mate.application.wicket.logout.LogoutPage;
import de.atomfrede.mate.application.wicket.security.UserAuthModel;
import de.atomfrede.mate.application.wicket.security.UserSession;
import de.atomfrede.mate.domain.entities.user.User;
import de.atomfrede.mate.service.bottle.BottleService;
import de.atomfrede.mate.service.consumption.ConsumptionService;

public abstract class BasePage<T> extends GenericWebPage<T> {

	@SpringBean
	protected ConsumptionService consumptionService;
	
	@SpringBean
	protected BottleService bottleService;

	protected User currentUser;
	
	TypedLink<Void> bottleBtn;
	
	Label availableMatesLabel;

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

	@Override
	public void onBeforeRender(){
		super.onBeforeRender();
		if(bottleService.getNumberOfNotConsumedBottles() == 0){
			bottleBtn.add(new AttributeAppender("class", " disabled"));
			bottleBtn.setEnabled(false);
		}else{
			bottleBtn.add(new AttributeModifier("class", "btn btn-block btn-large btn-primary"));
			bottleBtn.setEnabled(true);
		}
		
		remove(availableMatesLabel);
		availableMatesLabel = new Label("available-mates", "Verfügbare Mates: "+bottleService.getNumberOfNotConsumedBottles());
		add(availableMatesLabel);
	}
	
	private void commonInit(PageParameters pageParameters) {

		currentUser = getSession().getUser().getObject();
		initConsumeButton();
		initCrateButton();
		initUserButton();

		
		add(newNavbar("navbar"));
		add(new Footer("footer"));
		
		availableMatesLabel = new Label("available-mates", "Verfügbare Mates: "+bottleService.getNumberOfNotConsumedBottles());
		add(availableMatesLabel);
	}

	@SuppressWarnings("serial")
	protected void initUserButton() {
		TypedLink<Void> userAdminBtn = new TypedLink<Void>("btn-user-admin",
				ButtonType.Default) {

			@Override
			public void onClick() {
				// TODO Auto-generated method stub
				setResponsePage(MyAccountPage.class);

			}
		};

		userAdminBtn.setIconType(IconType.user).setSize(ButtonSize.Large)
				.setLabel(Model.of("Mein Konto")).setInverted(false);
		
		add(userAdminBtn);
	}

	@SuppressWarnings("serial")
	protected void initCrateButton() {
		TypedLink<Void> createBtn = new TypedLink<Void>("btn-crate",
				ButtonType.Default) {

			@Override
			public void onClick() {
				bottleService.newCrate();
			}
		};

		createBtn.setIconType(IconType.gift).setSize(ButtonSize.Large);
		createBtn.setLabel(Model.of("Versorgung")).setInverted(false);

		add(createBtn);
	}

	@SuppressWarnings("serial")
	protected void initConsumeButton() {

		bottleBtn = new TypedLink<Void>("btn-get-bottle",
				ButtonType.Primary) {

			@Override
			public void onClick() {
				consumeClicked();

			}
		};
		bottleBtn.setIconType(IconType.shoppingcart);
		bottleBtn.setSize(ButtonSize.Large);
		bottleBtn.setLabel(Model.of("Erfrischung"));

		add(bottleBtn);
		
		
	}

	protected Navbar newNavbar(String markupId) {
		Navbar navbar = new Navbar(markupId);

		navbar.setPosition(Navbar.Position.TOP);

		// show brand name
		navbar.brandName(Model.of("Mate Tracker"));
		navbar.addComponents(NavbarComponents.transform(
				Navbar.ComponentPosition.LEFT, new NavbarButton<Homepage>(
						Homepage.class, Model.of("Home"))
						.setIconType(IconType.home)

		));

		navbar.addComponents(new ImmutableNavbarComponent(
				new NavbarButton<LogoutPage>(LogoutPage.class, Model
						.of("Logout")).setIconType(IconType.off),
				Navbar.ComponentPosition.RIGHT));

		return navbar;
	}

	protected void consumeClicked() {
		consumptionService.consumeBottle(getSession().getUser().getObject());
	}

	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		response.render(CssHeaderItem.forReference(new CssResourceReference(
				BasePage.class, "base-content.css")));
		response.render(CssHeaderItem.forReference(new CssResourceReference(
				AbstractBasePage.class, "base.css")));
		Bootstrap.renderHead(response);
	}

	@SuppressWarnings("unchecked")
	public UserSession<UserAuthModel> getSession() {
		return (UserSession<UserAuthModel>) Session.get();
	}

	public UserAuthModel getUser() {
		return getSession().getUser();
	}
}
