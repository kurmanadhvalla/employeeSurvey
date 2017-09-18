package com.ideas.survey.entity;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FeedbackstatsRepository extends CrudRepository<FeedbackStats, Long> {
    List<FeedbackStats> findByEmployeeSurvey(@Param("employeeSurvey") EmployeeSurvey survey);

//    @Modifying
//    @Query(value = "update employee_feedback_stats u set aspect_ranking = ?1 where survey_id = ?2 and aspect_id = ?3" , nativeQuery = true)
//    int setRatingForSurveyIdAndAspectId(Integer aspectRating, Integer surveyId, Integer aspectId);


    FeedbackStats findByEmployeeSurvey_surveyIdAndAspect_aspectId(Integer surveyID, Integer aspectID);
}
