package de.atomfrede.mate.application.wicket.consumption;


import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import de.agilecoders.wicket.markup.html.bootstrap.navigation.ajax.BootstrapAjaxPagingNavigator;
import de.atomfrede.mate.domain.entities.consumption.Consumption;
import de.atomfrede.mate.service.consumption.ConsumptionService;

public class ConsumptionOverviewPanel extends Panel{

	private static final long serialVersionUID = 2273927022784081562L;
	
	@SpringBean
	public ConsumptionService consumptionService;
	
	public ConsumptionOverviewPanel(String id){
		super(id);
		setOutputMarkupId(true);
		populateItems();
		
	}
	
	private void populateItems(){
		DataView<Consumption> consumptions = new DataView<Consumption>("consumptions", new ConsumptionProvider()){

			@Override
			protected void populateItem(Item<Consumption> item) {
				item.add(new Label("date", new PropertyModel<String>(item.getModel(), "consumptionDate")));
				item.add(new Label("username", new PropertyModel<String>(item.getModel().getObject().getConsumedBy(), "username")));
				item.add(new Label("firstname", new PropertyModel<String>(item.getModel().getObject().getConsumedBy(), "firstname")));
				item.add(new Label("lastname", new PropertyModel<String>(item.getModel().getObject().getConsumedBy(), "lastname")));
				item.add(new Label("all", new PropertyModel<String>(item.getModel().getObject().getConsumedBy().getConsumptions(), "size")));
				item.add(new Label("account", new PropertyModel<String>(item.getModel().getObject().getConsumedBy().getAccount(), "value")));
			}
			
		};
		
		consumptions.setItemsPerPage(25);
		add(consumptions);
		add(new BootstrapAjaxPagingNavigator("pager", consumptions));
	}

}
