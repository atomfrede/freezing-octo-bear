package de.atomfrede.mate.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "entityLoader")
@Transactional(rollbackFor = Exception.class)
public class EntityLoaderImpl implements EntityLoader {

	@Autowired
	private EntityLoader mEntityLoader;

	@Override
	public <T> T load(Class<T> clazz, Serializable id) {
		return mEntityLoader.load(clazz, id);
	}

}
