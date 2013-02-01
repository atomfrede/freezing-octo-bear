package de.atomfrede.mate.application.wicket.security;

import java.io.Serializable;

import javax.persistence.EntityNotFoundException;

import de.atomfrede.mate.application.wicket.model.AbstractEntityModel;
import de.atomfrede.mate.domain.entities.user.AnonymousUser;
import de.atomfrede.mate.domain.entities.user.User;

public class UserAuthModel extends AbstractEntityModel<User> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5019674040250627202L;

	boolean isAnonymous = true;

	public UserAuthModel(Class<? extends User> clazz, Serializable id) {
		super(clazz, id);
		if (id != null && !id.equals(-1L)) {
			isAnonymous = false;
		}else{
			isAnonymous = true;
		}
	}

	public User getObject() {
		if (entity == null) {
			if (id != null) {
				entity = load(id);
				if (entity == null && !isAnonymous) {
					throw new EntityNotFoundException("Entity of type " + clazz
							+ " with id " + id + " could not be found.");
				} else if (entity == null) {
					entity = new AnonymousUser();
				}
			}
		}
		return entity;
	}

	protected User load(Serializable id) {
		if (!isAnonymous) {
			return (User)mateEntityLoader.load(clazz, id);
		} else {
			return new AnonymousUser();
		}
	}

}
