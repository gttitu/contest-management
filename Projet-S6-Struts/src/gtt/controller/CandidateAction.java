package gtt.controller;

import java.util.List;

import org.apache.struts2.ServletActionContext;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionSupport;

import gtt.model.candidate.Candidate;
import gtt.model.candidate.CandidateDetail;
import gtt.model.center.Center;
import gtt.service.CandidateService;
import gtt.service.CenterService;

@SuppressWarnings("serial")
public class CandidateAction extends ActionSupport {
	
	// ATTRIBUTES :
	
	private WebApplicationContext context;
	
	private CandidateService candServ;
	private CenterService centServ;
	
	private List<Center> centers;
	private List<CandidateDetail> candidates;
	
	private Candidate candidate;
	private CandidateDetail detail;
	
	private String errorMessage = "Aucune erreur";
	
	// METHODS :
	
	public String findCandidates() throws Exception {
		
		try {
		
			this.initContext(); this.initCandServ(); this.initCentServ();
			
			centers = centServ.getCenters();
			candidates = candServ.getCandidates();
		
		} catch (Exception ex) {
			
			errorMessage = ex.getMessage();
			throw ex;
			
		} return "success";
		
	}
	
	public String insertCandidate() throws Exception{
		
		try {
		
			this.initContext(); this.initCandServ(); this.initCentServ();
			
			candServ.addCandidate(candidate, detail);
			centers = centServ.getCenters();
			candidates = candServ.getCandidates();
		
		} catch (Exception ex) {
			
			errorMessage = ex.getMessage();
			throw ex;
			
		} return "success";
		
	}
	
	private void initContext() {
		
		if(context == null) 
			context = WebApplicationContextUtils
				.getRequiredWebApplicationContext(ServletActionContext.getServletContext());
		
	}
	
	private void initCandServ() {
		
		if(candServ == null) candServ = context.getBean(CandidateService.class);
		
	}
	
	private void initCentServ() {
		
		if(centServ == null) centServ = context.getBean(CenterService.class);
		
	}
	
	// GETTERS AND SETTERS :
	
	public List<CandidateDetail> getCandidates(){ return candidates; }

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	public CandidateDetail getDetail() {
		return detail;
	}

	public void setDetail(CandidateDetail detail) {
		this.detail = detail;
	}

	public List<Center> getCenters() {
		return centers;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
	
	

}
