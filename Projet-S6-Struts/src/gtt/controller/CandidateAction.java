package gtt.controller;

import java.util.List;

import gtt.model.candidate.Candidate;
import gtt.model.candidate.CandidateDetail;
import gtt.model.center.Center;
import gtt.service.CandidateService;
import gtt.service.CenterService;

public class CandidateAction{
	
	// ATTRIBUTES :
	
	private CandidateService candidateService;
	private CenterService centerService;
	
	private List<Center> centers;
	private List<CandidateDetail> candidates;
	
	private Candidate candidate;
	private CandidateDetail detail;
	
	private String errorMessage = "Aucune erreur";
	
	// METHODS :
	
	public String defaultMtd() throws Exception { 
		
		try {
			
			centers = centerService.getCenters();
			
		} catch (Exception ex) {
			
			throw ex;
			
		} return "success"; 
		
	}
	
	public String findCandidates() throws Exception {
		
		try {
		
			centers = centerService.getCenters();
			candidates = candidateService.getCandidates();
		
		} catch (Exception ex) {
			
			errorMessage = ex.getMessage();
			throw ex;
			
		} return "success";
		
	}
	
	public String insertCandidate() throws Exception{
		
		try {
		
			candidateService.addCandidate(candidate, detail);
			centers = centerService.getCenters();
			candidates = candidateService.getCandidates();
		
		} catch (Exception ex) {
			
			errorMessage = ex.getMessage();
			throw ex;
			
		} return "success";
		
	}
	
	// GETTERS AND SETTERS :
	
	public CandidateService getCandidateService() {
		return candidateService;
	}

	public void setCandidateService(CandidateService candidateService) {
		this.candidateService = candidateService;
	}

	public CenterService getCenterService() {
		return centerService;
	}

	public void setCenterService(CenterService centerService) {
		this.centerService = centerService;
	}
	
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
