package de.atomfrede.mate.service;

import java.io.Serializable;

public interface EntityLoader {

	public <T> T load(Class<T> clazz, Serializable id);
}
