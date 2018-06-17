package gtt.controller;

import java.util.List;

import gtt.model.setting.Contest;
import gtt.model.setting.Matter;
import gtt.service.ContestService;
import gtt.service.MatterService;

public class MatterAction {
	
	// ATTRIBUTES :
	
	private ContestService contestService;
	private MatterService matterService;
	
	private List<Contest> contests;
	private List<Matter> matters;
	
	private String errorMessage = "Aucune erreur";

	// METHODS :
	
	public String findMatters() throws Exception {
		
		try {
		
			contests = contestService.getContests();
			matters = matterService.getMatters();
		
		} catch (Exception ex) {
			
			errorMessage = ex.getMessage();
			throw ex;
			
		} return "success";
		
	}
	
	// GETTERS AND SETTERS :
		
	public ContestService getContestService() {
		return contestService;
	}

	public void setContestService(ContestService contestService) {
		this.contestService = contestService;
	}

	public MatterService getMatterService() {
		return matterService;
	}

	public void setMatterService(MatterService matterService) {
		this.matterService = matterService;
	}

	public List<Contest> getContests() {
		return contests;
	}

	public void setContests(List<Contest> contests) {
		this.contests = contests;
	}

	public List<Matter> getMatters() {
		return matters;
	}

	public void setMatters(List<Matter> matters) {
		this.matters = matters;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
}
