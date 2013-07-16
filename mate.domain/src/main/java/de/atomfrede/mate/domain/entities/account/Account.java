package de.atomfrede.mate.domain.entities.account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import de.atomfrede.mate.domain.entities.AbstractEntity;

@Entity
@Table(name = "account")
public class Account extends AbstractEntity {

	private static final long serialVersionUID = 4303031792710743696L;
	@GenericGenerator(name = "AccountIdGenerator", strategy = "org.hibernate.id.MultipleHiLoPerTableGenerator", parameters = {
			@Parameter(name = "table", value = "IdentityGenerator"),
			@Parameter(name = "primary_key_column", value = "sequence_name"),
			@Parameter(name = "primary_key_value", value = "Account"),
			@Parameter(name = "value_column", value = "next_hi_value"),
			@Parameter(name = "primary_key_length", value = "100"),
			@Parameter(name = "max_lo", value = "1000") })
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "AccountIdGenerator")
	protected Long id;

	@Override
	public Long getId() {
		return id;
	}

	@Column(name = "value")
	public double value;

	public double getValue() {
		return value;
	}

	public double increaseBy(double increment) {
		this.value = this.value + increment;
		return this.value;
	}

	public double decreaseBy(double decrement) {
		this.value -= decrement;
		return this.value;
	}
}
