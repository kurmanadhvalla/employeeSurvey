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
public class FeedbackstatsRepositoryTest {

@Autowired
    private FeedbackstatsRepository feedbackstatsRepository;

 @Test
    public void  shouldfindByEmployeeSurvey_surveyIdAndAspect_aspectId()
 {
     FeedbackStats  feedbackStats = feedbackstatsRepository.findByEmployeeSurvey_surveyIdAndAspect_aspectId(97,1);
     assertEquals("Salary" ,feedbackStats.getAspect().getAspectName());
 }

}