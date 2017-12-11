package com.ideas.survey.endpoint;

import com.ideas.survey.dto.ActiveDirectoryDAO;
import com.ideas.survey.dto.Data;
import com.ideas.survey.dto.EmployeeSurveyDetailsDTO;
import com.ideas.survey.entity.*;
import com.ideas.survey.service.ActiveDirectoryService;
import com.ideas.survey.service.EmpSurveyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

import java.util.*;


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
    private EmpSurveyService empSurveyService;
    @Autowired
    private ActiveDirectoryService activeDirectoryService;
    @Autowired
    JdbcTemplate jdbcTemplate;
    ActiveDirectoryDAO activeDirectoryDAO = new ActiveDirectoryDAO();

    @RequestMapping("/getName")
    public String getUserName(HttpServletRequest request) {

        String username = request.getUserPrincipal().getName();
        int pSlash = username.indexOf('\\');
        if (pSlash > 0) {

            username = username.substring(pSlash + 1);
        }
        return activeDirectoryDAO.getUserName("sAMAccountName", username);
    }

    @RequestMapping("/directReports")
    public ArrayList getDirectReports(@RequestParam("searchfield") String searchfield, @RequestParam("searchvalue") String accountName) {
        return activeDirectoryService.getDirectReportsfromDirectory(searchfield, accountName);
    }

    @RequestMapping("/empID")
    public String getEmployeeID(@RequestParam("searchfield") String searchfield, @RequestParam("searchvalue") String accountName) {
        return activeDirectoryService.getEmployeeIDfromDirectory(searchfield, accountName);
    }

    @RequestMapping("/getData")

    public List<Data> getDataEntries(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return activeDirectoryService.getDataEntriesfromDirectory(request, response);
    }


    @RequestMapping("/getDataofindividualmanager")
    public List<Data> getDataEntriesofIndividualManager(@RequestParam("cn") String employeename, HttpServletRequest request, HttpServletResponse response) throws IOException {
      return activeDirectoryService.getDataEntriesofIndividualManager(employeename,request,response);
    }

    @RequestMapping("/setTime")
    public void setTime(@RequestParam("empID") String empid, @RequestParam("surveyID") String surveyID) {

        empSurveyService.updateFeedbackTime(empid, surveyID);

    }

    @RequestMapping("/updateRating")
    public void updateRating(@RequestParam("empID") String empid, @RequestParam("surveyID") Integer surveyID, @RequestParam("aspectID") Integer aspectID, @RequestParam("rating") Integer rating) {
        empSurveyService.updateRating(empid, surveyID, aspectID, rating);
    }

    @RequestMapping("/updateRanking")
    public void updateRanking(@RequestParam("empID") String empid, @RequestParam("surveyID") Integer surveyID, @RequestParam("aspectID") Integer aspectID, @RequestParam("ranking") Integer ranking) {
        empSurveyService.updateRanking(empid, surveyID, aspectID, ranking);
    }

    @RequestMapping("/updateReason")
    public void updateReason(@RequestParam("empID") String empid, @RequestParam("surveyID") Integer surveyID, @RequestParam("aspectID") Integer aspectID, @RequestParam("reason") String reason) {
        empSurveyService.updateReason(empid, surveyID, aspectID, reason);
    }

    @RequestMapping("/saveEmpSurvey")
    public EmployeeSurvey saveEmployeeSurvey(@RequestParam("empID") String empid) {
        return empSurveyService.saveEmployeeSurvey(empid);
    }

    @RequestMapping("/getSurvey")
    public @ResponseBody
    List<EmployeeSurveyDetailsDTO> getEmployeeSurvey(@RequestParam("empID") String empid) {
        return empSurveyService.getEmployeeSurvey(empid);
    }

//    @RequestMapping("/getNewSurvey")
//    public List<EmployeeSurveyDetailsDTO> getNewSurvey(@RequestParam("empID") String empid) {
//        return empSurveyService.getNewSurvey(empid);
//
//    }

    @RequestMapping("/getAllSurveys")
    public List<List<EmployeeSurveyDetailsDTO>> getAllSurveysofEmployee(@RequestParam("empID") String empid) {
        return empSurveyService.getAllSurveysofEmployee(empid);
    }

}