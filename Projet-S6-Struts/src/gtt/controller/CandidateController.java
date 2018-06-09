package gtt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import gtt.service.CandidateService;

@Controller
public class CandidateController {
	
	@Autowired
	private CandidateService serv;
	
	@RequestMapping("/getCandidates")
	public String getCandidates(Model model) throws Exception {
		
		model.addAttribute("candidates", serv.getCandidates());
		
		return "candidate-list"; // ICI DOIT ETRE PRECISE LE FICHIER JSP A ENVOYER
		
	}

}
