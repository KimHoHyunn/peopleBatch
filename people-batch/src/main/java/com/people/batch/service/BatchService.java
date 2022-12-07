package com.people.batch.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.people.batch.dao.BatchDao;

@Service
public class BatchService {
	
	@Autowired private BatchDao batchDao;
	
	public List<Map<String, Object>> getList() {
		Map<String, Object> params = new HashMap<String, Object>();
		return batchDao.getData(params);
	}
	
	public List<Map<String, Object>> getJobExceutionList() {
		return batchDao.getJobExceutionList();
	}
}
