package de.atomfrede.mate.application.wicket.user.detail;

import static de.atomfrede.mate.application.wicket.MessageUtils._;

import org.apache.wicket.extensions.markup.html.form.select.SelectOption;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.model.CompoundPropertyModel;

import de.atomfrede.mate.domain.entities.user.Role;


@SuppressWarnings("serial")
public class RoleSelectOption extends SelectOption<Role> {

	public RoleSelectOption(String id, Role role) {
		super(id, new CompoundPropertyModel<Role>(role));
	}

	public void onComponentTagBody(final MarkupStream markupStream,
			final ComponentTag openTag) {
		String displayedText = _("user.role." + getDefaultModelObjectAsString())
				.getString();
		replaceComponentTagBody(markupStream, openTag, displayedText);
	}
}
