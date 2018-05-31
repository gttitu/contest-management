package gtt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gtt.model.center.Center;
import gtt.model.dao.mybernate.Mybernate;

@Component
public class CenterService {
	
	@Autowired
	private Mybernate dataAccess;
	
	@SuppressWarnings("unchecked")
	public List<Center> getCenters() throws Exception{
		
		return dataAccess.findAll(new Center(), null);
		
	}

}
