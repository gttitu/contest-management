package gtt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gtt.model.dao.mybernate.Mybernate;

import gtt.model.setting.Contest;

@Component
public class ContestService {
	@Autowired
	private Mybernate dataAccess;
	
	@SuppressWarnings("unchecked")
	public List<Contest> getContests() throws Exception{
		
		return dataAccess.findAll(new Contest(), null);
		
	}
	
}
