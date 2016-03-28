package com.db.javaschool.finalproject.dao;

import java.util.List;

import com.db.javaschool.finalproject.entity.Entity;

public interface Dao<T extends Entity, I> {

	List<T> findAll();

	T find(I id);

	T save(T newsEntry);

	void delete(I id);

}