package de.atomfrede.mate.application.wicket.base;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.resource.CssResourceReference;

import de.agilecoders.wicket.Bootstrap;

public abstract class AbstractBasePage extends WebPage {

	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		response.render(CssHeaderItem.forReference(new CssResourceReference(
				AbstractBasePage.class, "base.css")));
		Bootstrap.renderHead(response);

	}
}
