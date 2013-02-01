package de.atomfrede.mate.domain.entities.consumption;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import de.atomfrede.mate.domain.entities.AbstractEntity;
import de.atomfrede.mate.domain.entities.bottle.Bottle;
import de.atomfrede.mate.domain.entities.user.User;

@Entity
@Table(name = "consumption")
public class Consumption extends AbstractEntity {

	@GenericGenerator(name = "ConsumptionIdGenerator", strategy = "org.hibernate.id.MultipleHiLoPerTableGenerator", parameters = {
			@Parameter(name = "table", value = "IdentityGenerator"),
			@Parameter(name = "primary_key_column", value = "sequence_name"),
			@Parameter(name = "primary_key_value", value = "Bottle"),
			@Parameter(name = "value_column", value = "next_hi_value"),
			@Parameter(name = "primary_key_length", value = "100"),
			@Parameter(name = "max_lo", value = "1000") })
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ConsumptionIdGenerator")
	protected Long id;

	@Override
	public Long getId() {
		return id;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "consumptiondate")
	private java.util.Date consumptionDate;

	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "bottle", nullable = false)
	private Bottle bottle;

	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "consumedby", nullable = false)
	private User consumedBy;

	public java.util.Date getConsumptionDate() {
		return consumptionDate;
	}

	public void setConsumptionDate(java.util.Date consumptionDate) {
		this.consumptionDate = consumptionDate;
	}

	public Bottle getBottle() {
		return bottle;
	}

	public void setBottle(Bottle bottle) {
		this.bottle = bottle;
	}

	public User getConsumedBy() {
		return consumedBy;
	}

	public void setConsumedBy(User consumedBy) {
		this.consumedBy = consumedBy;
	}

}
