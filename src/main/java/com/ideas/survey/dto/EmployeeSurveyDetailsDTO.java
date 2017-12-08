package com.ideas.survey.dto;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by idnvlr on 8/31/2017.
 */
public class EmployeeSurveyDetailsDTO {


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
    String  ratingreason;
    String submissionDate;
    String AspectDescription;

    public String getAspectDescription() {
        return AspectDescription;
    }

    public void setAspectDescription(String aspectDescription) {
        AspectDescription = aspectDescription;
    }

    public String getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(String submissionDate) {
        this.submissionDate =(submissionDate == null) ? "not submitted" : submissionDate;

    }

    public ArrayList<String> getDirectReports() {
        return directReports;
    }

    public void setDirectReports(ArrayList<String> directReports) {
        this.directReports = directReports;
    }

    ArrayList<String> directReports;
    public String getRatingreason() {
        return ratingreason;
    }

    public void setRatingreason(String ratingreason) {
        this.ratingreason = ratingreason;
    }

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
