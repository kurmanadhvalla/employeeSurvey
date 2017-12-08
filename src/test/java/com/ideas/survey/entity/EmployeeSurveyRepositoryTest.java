package com.ideas.survey.entity;

import com.ideas.survey.config.EmpSurveyApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by idnvlr on 11/15/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EmpSurveyApplication.class)
public class EmployeeSurveyRepositoryTest {

    @Autowired
    private EmployeeSurveyRepository employeeSurveyRepository;

    @Test
    public void shouldfindByEmpidOrderBySurveyId()
    {
        System.out.println(employeeSurveyRepository.findByEmpidOrderBySurveyId("c36687").size());
    }
    @Test
    public void shouldfindByEmpidAndSurveyId()
    {
      EmployeeSurvey empsurvey =   employeeSurveyRepository.findByEmpidAndSurveyId("c36687",97);
      System.out.println(empsurvey.getEmpid() + empsurvey.getSurveyId() + empsurvey.toString());

    }
    @Test
    public void shouldfindByfindByEmpidAndStatus()
    {
        System.out.println(employeeSurveyRepository.findByEmpidAndStatus("c36687", true).size());
    }

}