package com.people.batch.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository("BatchDao")
public interface BatchDao {
	List<Map<String, Object>> getData(Map<String, Object> params) ;
	
	List<Map<String, Object>> getJobExceutionList() ;
	
	
}
