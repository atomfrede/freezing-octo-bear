package de.atomfrede.mate.application.wicket.admin;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapLink;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.ButtonBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons;
import de.agilecoders.wicket.core.markup.html.bootstrap.dialog.TextContentModal;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.IconType;
import de.atomfrede.mate.service.admin.AdminService;

public class ClearAllDataPanel extends Panel {

	@SpringBean
	private AdminService adminService;

	private TextContentModal modalWarning;

	public ClearAllDataPanel(String id) {
		super(id);
		setupModal();
		initClearAllDataButton();
	}

	private void maybeResetData() {
		modalWarning.show(true);
	}

	private void doResetData() {
		adminService.clearAllData();
	}

	@SuppressWarnings("serial")
	protected void initClearAllDataButton() {
		BootstrapLink<Void> resetBtn = new BootstrapLink<Void>("btn-reset",
				Buttons.Type.Danger) {

			@Override
			public void onClick() {
				maybeResetData();
			}
		};

		resetBtn.setIconType(IconType.bancircle).setSize(Buttons.Size.Large);
		resetBtn.setLabel(Model.of("Alle Daten zurücksetzen")).setInverted(
				false);

		add(resetBtn);
	}

	private void setupModal() {
		modalWarning = new TextContentModal(
				"modal-prompt",
				Model.of("Achtung! Hierdurch werden alle Konten zurückgesetzt!"));
		modalWarning.addCloseButton(Model.of("Abbrechen"));
		modalWarning.header(Model.of("Alle Daten zurücksetzen?"));

		AjaxLink<String> doPut = new AjaxLink<String>("button",
				Model.of("Daten zurücksetzen")) {

			@Override
			protected void onConfigure() {
				super.onConfigure();

				setBody(getDefaultModel());
				add(new ButtonBehavior(Buttons.Type.Warning));
				// add(new IconBehavior(IconType.remove));
			}

			@Override
			public void onClick(AjaxRequestTarget target) {
				doResetData();
				target.appendJavaScript("$('.modal').modal('close');");
				setResponsePage(AdminPage.class);
			}
		};

		modalWarning.addButton(doPut);
		add(modalWarning);
	}

}
