package com.db.db_kudos.service;

import java.util.List;
import java.util.Optional;

public interface AbstractService<T, R> {

	List<T> findAll();

	Optional<T> findById(R id);

	T saveOrUpdate(T t);

	boolean deleteById(R id);
}
