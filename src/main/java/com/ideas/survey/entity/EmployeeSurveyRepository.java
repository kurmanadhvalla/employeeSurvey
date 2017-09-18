package com.ideas.survey.entity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeSurveyRepository extends CrudRepository<EmployeeSurvey, Long> {
    List<EmployeeSurvey> findByEmpidOrderBySurveyId(@Param("empid") String empid);

    List<EmployeeSurvey> findByEmpidAndSurveyId(@Param("empid") String empid, @Param("surveyId") Integer surveyId);


    List<EmployeeSurvey> findByEmpidOrderBySurveyIdDesc(@Param("empid") String empid);

    List<EmployeeSurvey> findByEmpidAndStatus(String empid, boolean b);



}