package com.ideas.survey.endpoint;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.ideas.survey.dto.EmployeeSurveyDetailsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.ideas.survey.entity.AspectRepository;
import com.ideas.survey.entity.Aspects;
import com.ideas.survey.entity.EmployeeSurvey;
import com.ideas.survey.entity.EmployeeSurveyRepository;
import com.ideas.survey.entity.FeedbackStats;
import com.ideas.survey.entity.FeedbackstatsRepository;


@RestController
@RequestMapping("/survey")
public class EmpSurveyController {

    @Autowired
    private AspectRepository aspectRepository;
    @Autowired
    private EmployeeSurveyRepository employeeSurveyRepository;
    @Autowired
    private FeedbackstatsRepository feedbackstatsRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;


    @RequestMapping("/allSurveys")
    public List<EmployeeSurvey> getSurveys() {
        Iterable<EmployeeSurvey> Surveys = employeeSurveyRepository.findAll();
        List<EmployeeSurvey> target1 = new ArrayList<>();
        Surveys.forEach(target1::add);
        return target1;
    }


    @RequestMapping("/allStats")
    public List<FeedbackStats> getStats() {
        Iterable<FeedbackStats> Stats = feedbackstatsRepository.findAll();
        List<FeedbackStats> target2 = new ArrayList<>();
        Stats.forEach(target2::add);
        return target2;
    }


    @RequestMapping("/allAspects")
    public List<Aspects> getAspects() {
        Iterable<Aspects> itAscpects = aspectRepository.findAll();
        List<Aspects> target = new ArrayList<>();
        itAscpects.forEach(target::add);
        return target;
    }


    @RequestMapping("/save")
    public Aspects saveEmployee(@RequestParam("aspect") String aspect) {
        return aspectRepository.save(new Aspects(aspect));
    }

    @RequestMapping("/setTime")
    public void setTime(@RequestParam("empID") String empid, @RequestParam("surveyID") String surveyID) {
//		System.out.println(empid + " " + surveyID);
        Date date = new Date();
        jdbcTemplate.update("UPDATE `employee_feedback` SET \n" +
                "submission_date = ? ,status= 1 WHERE empid =? AND survey_id = ?;", new Object[]{date, empid, surveyID});
       System .out.println("it worked!");
    }

    @RequestMapping("/updateRating")
    public void updateRating(@RequestParam("empID") String empid, @RequestParam("surveyID") Integer surveyID, @RequestParam("aspectID") Integer aspectID, @RequestParam("rating") Integer rating) {
        FeedbackStats feedbackStats = feedbackstatsRepository.findByEmployeeSurvey_surveyIdAndAspect_aspectId(surveyID, aspectID);
        feedbackStats.setAspectRating(rating);
        feedbackstatsRepository.save(feedbackStats);
    }


    @RequestMapping("/getSurvey")
    public List<EmployeeSurveyDetailsDTO> getEmployeeSurvey(@RequestParam("empID") String empid) {

        List<EmployeeSurvey> employeeSurveys = employeeSurveyRepository.findByEmpidAndStatus(empid, false);
        if (employeeSurveys != null && !employeeSurveys.isEmpty()) {
            List<FeedbackStats> feedbackStats = employeeSurveys.get(0).getFeedbackStats();
            return getEmployeeSurveyDetailsDTO(feedbackStats);
        } else {
            return getNewSurvey(empid);
        }
    }

    List<EmployeeSurveyDetailsDTO> getEmployeeSurveyDetailsDTO(List<FeedbackStats> feedbackStats) {
        ArrayList<EmployeeSurveyDetailsDTO> employeeSurveyDetailsDTOS = new ArrayList<>();
        feedbackStats.forEach(feedbackStat -> {
            EmployeeSurveyDetailsDTO dto = new EmployeeSurveyDetailsDTO();
            EmployeeSurveyDetailsDTO employeeSurveyDetailsDTO = new EmployeeSurveyDetailsDTO();
            employeeSurveyDetailsDTO.setEmpID(feedbackStat.getEmployeeSurvey().getEmpid());
            employeeSurveyDetailsDTO.setSurveyID(feedbackStat.getEmployeeSurvey().getSurveyId());
            employeeSurveyDetailsDTO.setAspectID(feedbackStat.getAspect().getAspectId());
            employeeSurveyDetailsDTO.setAspectName(feedbackStat.getAspect().getAspectName());
            employeeSurveyDetailsDTO.setRating(feedbackStat.getAspectRating());
            employeeSurveyDetailsDTO.setRanking(feedbackStat.getAspectRanking());
            employeeSurveyDetailsDTOS.add(employeeSurveyDetailsDTO);
        });
        return employeeSurveyDetailsDTOS;
    }

    @RequestMapping("/getNewSurvey")
    public List<EmployeeSurveyDetailsDTO> getNewSurvey(@RequestParam("empID") String empid) {
        Date date = new Date();
        EmployeeSurvey survey = employeeSurveyRepository.save(new EmployeeSurvey(empid, date, false));
        int rating = 1;
        int ranking = 1;
        Iterable<Aspects> allAspects = aspectRepository.findAll();
        for (Aspects aspect : allAspects) {
            feedbackstatsRepository.save(new FeedbackStats(rating, ranking, aspect, survey));
            ranking++;
        }
        List<FeedbackStats> feedbackStats = feedbackstatsRepository.findByEmployeeSurvey(survey);
        return getEmployeeSurveyDetailsDTO(feedbackStats);
    }

@RequestMapping("/saveEmpSurvey")
public EmployeeSurvey saveEmployeeSurvey(@RequestParam("empID") String empid) {
        Date date = new Date();
        return employeeSurveyRepository.save(new EmployeeSurvey(empid, date,false));
    }


@RequestMapping("/getAllSurveys")
public List<List<EmployeeSurveyDetailsDTO>> getAllSurveysofEmployee(@RequestParam("empID") String empid)
{
    List<EmployeeSurvey> employeeSurveys = employeeSurveyRepository.findByEmpidAndStatus(empid, true);
    List<List<EmployeeSurveyDetailsDTO>> finallist = new ArrayList<>();
    for(int i=0;i<employeeSurveys.size();i++)
    {
        List<FeedbackStats> feedbackStats = employeeSurveys.get(i).getFeedbackStats();
       finallist.add(getEmployeeSurveyDetailsDTO(feedbackStats)) ;
    }
   return finallist;
}

    class MyRankingComp extends com.ideas.survey.endpoint.MyRankingComp {

    }

}