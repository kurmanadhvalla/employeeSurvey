package com.ideas.survey.entity;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FeedbackstatsRepository extends CrudRepository<FeedbackStats, Long> {
    List<FeedbackStats> findByEmployeeSurvey(@Param("employeeSurvey") EmployeeSurvey survey);




    FeedbackStats findByEmployeeSurvey_surveyIdAndAspect_aspectId(Integer surveyID, Integer aspectID);
}
