package gtt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;

import gtt.model.setting.Contest;
import gtt.model.setting.Matter;
import gtt.service.ContestService;
import gtt.service.MatterService;


@Controller
public class MatterController {

	// ATTRIBUTES :
	
	@Autowired
	private MatterService matterService;
	
	@Autowired
	private ContestService contestService;
	
	
	// METHODS :
	
	@RequestMapping(value = "/insertMatter", method = RequestMethod.GET)
	public String defaultMtd(Model model)  throws Exception {
	
		model.addAttribute("contests", contestService.getContests());
						
		return "matter-save";
	
	}
	
	/*public ModelAndView defaultMtd(Model model)  throws Exception {
		
		model.addAttribute("contests", contestService.getContests());
		
		
		ModelAndView mav=new ModelAndView("matter-save", "command", new Matter());
	    //mav.addObject("contests", contestService.getContests());
		
		return mav;
		
	}*/
	
	@RequestMapping(value = "/insertMatter", method = RequestMethod.POST)
	public String insertMatter(
			@RequestParam("contest") String contest,
			@RequestParam("description") String description,
			@RequestParam("coefficient") String coefficient,
			@RequestParam("average") String average,
			@RequestParam("dateBegin") String dateBegin,
			@RequestParam("timeBegin") String timeBegin,
			@RequestParam("dateEnd") String dateEnd,
			@RequestParam("timeEnd") String timeEnd,
			Model model
			)  throws Exception{
		
				try {
					String datetimeBegin = dateBegin + " " + timeBegin;
					String datetimeEnd = dateEnd + " " + timeEnd;
					Matter matter = new Matter(Integer.parseInt(contest), description, Integer.parseInt(coefficient), Float.parseFloat(average), datetimeBegin, datetimeEnd);
					matterService.addMatter(matter);
					
				} catch (Exception ex) {
					
					model.addAttribute("errorMessage", ex.getMessage());
					return "error-springmvc";
					
				} return "redirect:/insertMatter";
   }

	// GETTERS AND SETTERS :
	
	public MatterService getMatterService() {
		return matterService;
	}

	public void setMatterService(MatterService matterService) {
		this.matterService = matterService;
	}

	public ContestService getContestService() {
		return contestService;
	}

	public void setContestService(ContestService contestService) {
		this.contestService = contestService;
	}

	
}
