package com.ideas.survey.endpoint;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.ideas.survey.entity.AspectRepository;
import com.ideas.survey.entity.Aspects;
import com.ideas.survey.entity.Employeefeedback;
import com.ideas.survey.entity.EmployeefeedbackRepository;
import com.ideas.survey.entity.FeedbackStats;
import com.ideas.survey.entity.FeedbackstatsRepository;




@Controller
@RequestMapping("/survey")
public class EmpSurveyController {
	
	/*@Autowired private AspectRepository aspectRepository ;
	@Autowired private EmployeefeedbackRepository employee_feedbackRepository;
    @Autowired private FeedbackstatsRepository feedback_repository;
		
	@RequestMapping("/hello")
	public String welcome(){		
		return "welcome";
	}
	
	  @RequestMapping("/allAspects")
		public List<Aspects> getAspects()
		{
			Iterable<Aspects> itAscpects = aspectRepository.findAll();
			List<Aspects> target = new ArrayList<>();
			itAscpects.forEach(target::add);
			return target;
		}
	  
	  
	  
	  @RequestMapping("/count")	  
	  public int getCount(){
		  return  (int) aspectRepository.count();
	  }
	  
	  @RequestMapping("/save")
		public Aspects saveEmployee(@RequestParam("aspect") String aspect)
		{
			return aspectRepository.save(new Aspects(aspect));
		}

	 
	  
	  @RequestMapping("/countSurvey")
	  
	  public int getCountofSurvey(){
		  return  (int) employee_feedbackRepository.count();
	  }
	  
	  @RequestMapping("/allSurveys")
		public List<Employeefeedback> getSurveys()
		{
			Iterable<Employeefeedback> Surveys = employee_feedbackRepository.findAll();
			List<Employeefeedback> target1 = new ArrayList<>();
			Surveys.forEach(target1::add);
			return target1;
		}
		
	
	  @RequestMapping ("/countStats")
	  public int  getStatscount(){
		  return  (int) feedback_repository.count();
	  }

	  @RequestMapping("/allStats")
		public List<FeedbackStats> getStats()
		{
			Iterable<FeedbackStats> Stats = feedback_repository.findAll();
			List<FeedbackStats> target2 = new ArrayList<>();
			Stats.forEach(target2::add);
			return target2;
		}*/
	  
	  @Value("${welcome.message:test}")
		private String message = "Hello World";
	  @RequestMapping("/home")
		public String welcome(Map<String, Object> model) {
			model.put("message", this.message);
			return "NewFile";
		}

//		@RequestMapping(value = "/candidateProfile/{name}", method = RequestMethod.GET,produces = "text/html")
////		public String hello(Model model,@PathVariable("name") String name) {
////			model.addAttribute("msg", name);
////			return "NewFile";
////
////		}
	
	  

}