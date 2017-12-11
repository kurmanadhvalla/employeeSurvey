package com.ideas.survey.endpoint;

import com.ideas.survey.entity.AspectRepository;
import com.ideas.survey.entity.EmployeeSurveyRepository;
import com.ideas.survey.entity.FeedbackstatsRepository;
import com.ideas.survey.service.ActiveDirectoryService;
import com.ideas.survey.service.EmpSurveyService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class EmpSurveyControllerTest {

    @Mock
    private AspectRepository aspectRepository;
    @Mock
    private EmployeeSurveyRepository employeeSurveyRepository;
    @Mock
    private FeedbackstatsRepository feedbackstatsRepository;

    @Mock
    private EmpSurveyService empSurveyService;
    @Mock
    private ActiveDirectoryService activeDirectoryService;


    @InjectMocks
    EmpSurveyController empSurveyController;

    @Test
    public void getUserName() throws Exception {

    }

    @Test
    public void getDirectReports() throws Exception {
        String searchField = "CN";
        String emplName = "Prasad Kunte";
        empSurveyController.getDirectReports(searchField, emplName);
        verify(activeDirectoryService).getDirectReportsfromDirectory(searchField,emplName );
    }

    @Test
    public void getEmployeeID() throws Exception {
        String searchField = "CN";
        String emplName = "Prasad Kunte";
        empSurveyController.getEmployeeID(searchField, emplName);
        verify(activeDirectoryService).getEmployeeIDfromDirectory(searchField,emplName );
    }

    @Test
    public void getDataEntries() throws Exception {

        HttpServletRequest request=null;
        HttpServletResponse response = null;
        empSurveyController.getDataEntries( request,response );
        verify(activeDirectoryService).getDataEntriesfromDirectory(request,response );
    }

    @Test
    public void getDataEntriesofIndividualManager() throws Exception {
        HttpServletRequest request=null;
        HttpServletResponse response = null;
        String employeename="Prasad Kunte";
        empSurveyController.getDataEntriesofIndividualManager(employeename,request,response);
        verify(activeDirectoryService).getDataEntriesofIndividualManager(employeename,request,response );

    }



    @Test
    public void setTime() throws Exception {
        String empid="E81065";
        String  surveyid="85";
        empSurveyController.setTime(empid,surveyid);
        verify(empSurveyService).updateFeedbackTime(empid,surveyid );
    }

    @Test
    public void updateRating() throws Exception {
        String empid="E81065";
        Integer surveyID=85;
        Integer aspectID=10;
        Integer rating=5;
        empSurveyController.updateRating(empid, surveyID, aspectID, rating);
        verify(empSurveyService).updateRating(empid, surveyID, aspectID, rating);
    }

    @Test
    public void updateRanking() throws Exception {
        String empid="E81065";
        Integer surveyID=85;
        Integer aspectID=10;
        Integer ranking=1;
        empSurveyController.updateRanking(empid, surveyID, aspectID, ranking);
        verify(empSurveyService).updateRanking(empid, surveyID, aspectID, ranking);
    }

    @Test
    public void updateReason() throws Exception {
        String empid="E81065";
        Integer surveyID=85;
        Integer aspectID=10;
        String reason="testing reason";
        empSurveyController.updateReason(empid, surveyID, aspectID, reason);
        verify(empSurveyService).updateReason(empid, surveyID, aspectID, reason);
    }

    @Test
    public void saveEmployeeSurvey() throws Exception {
        String empid="E81065";
        empSurveyController.saveEmployeeSurvey(empid);
        verify(empSurveyService).saveEmployeeSurvey(empid);
    }

    @Test
    public void getEmployeeSurvey() throws Exception {
        String empid="E81065";
        empSurveyController.getEmployeeSurvey(empid);
        verify(empSurveyService).getEmployeeSurvey(empid);
    }


    @Test
    public void getAllSurveysofEmployee() throws Exception {
        String empid="E81065";
        empSurveyController.getAllSurveysofEmployee(empid);
        verify(empSurveyService).getAllSurveysofEmployee(empid);
    }

}