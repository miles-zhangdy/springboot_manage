package com.zdy.util;

import java.util.List;

public interface ISuperDao<T extends Page> {
	
	List<T> findList(T t);
	
	int count(T t);
	
	int save(T t);

	int delete(T t);
	
	int update(T t);
	
	int deleteById(Long l);
	
	T getById(Long l);
	
	T fetch(T t);
	
}
