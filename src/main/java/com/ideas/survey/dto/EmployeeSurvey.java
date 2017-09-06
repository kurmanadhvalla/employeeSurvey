package com.ideas.survey.dto;

/**
 * Created by idnvlr on 8/31/2017.
 */
public class EmployeeSurvey {


    String empID;


    public Integer getSurveyID() {
        return surveyID;
    }

    public void setSurveyID(Integer surveyID) {
        this.surveyID = surveyID;
    }

    Integer surveyID;
    String aspectName;
    Integer aspectID;

    Integer rating;
    Integer ranking;
    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }


    public String getAspectName() {
        return aspectName;
    }

    public void setAspectName(String aspectName) {
        this.aspectName = aspectName;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getAspectID() {
        return aspectID;
    }

    public void setAspectID(Integer aspectID) {
        this.aspectID = aspectID;
    }


}
