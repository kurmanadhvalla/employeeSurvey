package com.ideas.survey.service;

import com.ideas.survey.dto.EmployeeSurveyDetailsDTO;
import com.ideas.survey.endpoint.EmpSurveyController;
import com.ideas.survey.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Component
public class EmpSurveyService {

    @Autowired
    private AspectRepository aspectRepository;
    @Autowired
    private EmployeeSurveyRepository employeeSurveyRepository;
    @Autowired
    private FeedbackstatsRepository feedbackstatsRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public void updateRating(@RequestParam("empID") String empid, @RequestParam("surveyID") Integer surveyID, @RequestParam("aspectID") Integer aspectID, @RequestParam("rating") Integer rating) {
        FeedbackStats feedbackStats = feedbackstatsRepository.findByEmployeeSurvey_surveyIdAndAspect_aspectId(surveyID, aspectID);
        feedbackStats.setAspectRating(rating);

        feedbackstatsRepository.save(feedbackStats);
    }
    public void setTime(@RequestParam("empID") String empid, @RequestParam("surveyID") String surveyID) {

        Date date = new Date();
        jdbcTemplate.update("UPDATE `employee_feedback` SET \n" +
                "submission_date = ? ,status= 1 WHERE empid =? AND survey_id = ?;", new Object[]{date, empid, surveyID});

    }
    public void updateRanking(@RequestParam("empID") String empid, @RequestParam("surveyID") Integer surveyID, @RequestParam("aspectID") Integer aspectID, @RequestParam("ranking") Integer ranking) {
        FeedbackStats feedbackStats = feedbackstatsRepository.findByEmployeeSurvey_surveyIdAndAspect_aspectId(surveyID, aspectID);

        feedbackStats.setAspectRanking(ranking);
        feedbackstatsRepository.save(feedbackStats);
    }
    public void updateReason(@RequestParam("empID") String empid, @RequestParam("surveyID") Integer surveyID, @RequestParam("aspectID") Integer aspectID, @RequestParam("reason") String reason) {
        FeedbackStats feedbackStats = feedbackstatsRepository.findByEmployeeSurvey_surveyIdAndAspect_aspectId(surveyID, aspectID);

        feedbackStats.setRatingReason(reason);
        feedbackstatsRepository.save(feedbackStats);
    }
    public EmployeeSurvey saveEmployeeSurvey(@RequestParam("empID") String empid) {
        Date date = new Date();
        return employeeSurveyRepository.save(new EmployeeSurvey(empid, date, false));
    }

    public List<EmployeeSurveyDetailsDTO> getEmployeeSurvey(@RequestParam("empID") String empid) {

        List<EmployeeSurvey> employeeSurveys = employeeSurveyRepository.findByEmpidAndStatus(empid, true);
        if (employeeSurveys != null && !employeeSurveys.isEmpty()) {
            List<FeedbackStats> feedbackStats = employeeSurveys.get(employeeSurveys.size()-1).getFeedbackStats();
            employeeSurveys.get(employeeSurveys.size()-1).getSubmissionDate();
           
            return getEmployeeSurveyDetailsDTO(employeeSurveys.get(employeeSurveys.size()-1));
        } else {
            return getNewSurvey(empid);
        }
    }

    List<EmployeeSurveyDetailsDTO> getEmployeeSurveyDetailsDTO(EmployeeSurvey employeeSurvey) {
        ArrayList<EmployeeSurveyDetailsDTO> employeeSurveyDetailsDTOS = new ArrayList<>();
        List<FeedbackStats> feedbackStats = employeeSurvey.getFeedbackStats();
        feedbackStats.forEach(feedbackStat -> {
            EmployeeSurveyDetailsDTO dto = new EmployeeSurveyDetailsDTO();
            EmployeeSurveyDetailsDTO employeeSurveyDetailsDTO = new EmployeeSurveyDetailsDTO();
            employeeSurveyDetailsDTO.setEmpID(feedbackStat.getEmployeeSurvey().getEmpid());
            employeeSurveyDetailsDTO.setSurveyID(feedbackStat.getEmployeeSurvey().getSurveyId());
            employeeSurveyDetailsDTO.setAspectID(feedbackStat.getAspect().getAspectId());
            employeeSurveyDetailsDTO.setAspectName(feedbackStat.getAspect().getAspectName());
            employeeSurveyDetailsDTO.setAspectDescription(feedbackStat.getAspect().getAspectDetails());
            employeeSurveyDetailsDTO.setRating(feedbackStat.getAspectRating());
            employeeSurveyDetailsDTO.setRanking(feedbackStat.getAspectRanking());
            employeeSurveyDetailsDTO.setRatingreason(feedbackStat.getRatingReason());
            String submissionDate = (employeeSurvey.getSubmissionDate() == null) ? "not submitted" : employeeSurvey.getSubmissionDate().toString();
            employeeSurveyDetailsDTO.setSubmissionDate(submissionDate);
            employeeSurveyDetailsDTOS.add(employeeSurveyDetailsDTO);
        });
        Collections.sort(employeeSurveyDetailsDTOS, new MyRankingComp());
        return employeeSurveyDetailsDTOS;
    }


    public List<EmployeeSurveyDetailsDTO> getNewSurvey(@RequestParam("empID") String empid) {
        Date date = new Date();
        EmployeeSurvey survey = employeeSurveyRepository.save(new EmployeeSurvey(empid, date, false));
        int rating = 10;
        int ranking = 1;
        Iterable<Aspects> allAspects = aspectRepository.findAll();
        for (Aspects aspect : allAspects) {
            feedbackstatsRepository.save(new FeedbackStats(rating, ranking, aspect, survey));
            ranking++;
        }
        List<FeedbackStats> feedbackStats = feedbackstatsRepository.findByEmployeeSurvey(survey);

        survey.setFeedbackStats(feedbackStats);
//        EmployeeSurvey newsurvey = employeeSurveyRepository.findByEmpidAndSurveyId(empid,survey.getSurveyId());
        return getEmployeeSurveyDetailsDTO(survey);
    }

    public List<List<EmployeeSurveyDetailsDTO>> getAllSurveysofEmployee(@RequestParam("empID") String empid) {
        List<EmployeeSurvey> employeeSurveys = employeeSurveyRepository.findByEmpidAndStatusOrderBySurveyIdDesc(empid, true);
        List<List<EmployeeSurveyDetailsDTO>> finallist = new ArrayList<>();
        for (int i = 0; i < employeeSurveys.size(); i++) {

            List<FeedbackStats> feedbackStats = employeeSurveys.get(i).getFeedbackStats();
            finallist.add(getEmployeeSurveyDetailsDTO(employeeSurveys.get(i)));
        }
        return finallist;
    }
    class MyRankingComp extends com.ideas.survey.service.MyRankingComp {


    }

}
