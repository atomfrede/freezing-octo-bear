package de.atomfrede.mate.application.wicket.consumption;

import java.util.Iterator;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import de.atomfrede.mate.application.wicket.model.AbstractEntityModel;
import de.atomfrede.mate.domain.entities.consumption.Consumption;
import de.atomfrede.mate.service.consumption.ConsumptionService;

public class ConsumptionProvider implements IDataProvider<Consumption>{

	private static final long serialVersionUID = -1958234544172006317L;
	
	@SpringBean
	public ConsumptionService consumptionService;

	public ConsumptionProvider(){
		Injector.get().inject(this);
	}
	
	@Override
	public void detach() {
		//Nothing to do here...
		
	}

	@Override
	public Iterator<? extends Consumption> iterator(long first, long count) {
		return consumptionService.list(first, count, "consumptionDate", true).iterator();
	}

	@Override
	public long size() {
		return consumptionService.count();
	}

	@Override
	public IModel<Consumption> model(Consumption object) {
		return new AbstractEntityModel<Consumption>(object);
	}

}
