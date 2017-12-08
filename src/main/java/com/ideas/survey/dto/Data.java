package com.ideas.survey.dto;

import com.ideas.survey.endpoint.EmpSurveyController;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by idnvlr on 10/13/2017.
 */
public class Data {
    String sasid;
    Integer surveyid;
    String Parameter;
    Integer satisfaction;
    Integer importance;
    String period;


    public String getSasid() {
        return sasid;
    }

    public void setSasid(String sasid) {
        this.sasid = sasid;
    }

    public Integer getSurveyid() {
        return surveyid;
    }

    public void setSurveyid(Integer surveyid) {
        this.surveyid = surveyid;
    }

    public String getParameter() {
        return Parameter;
    }

    public void setParameter(String parameter) {
        Parameter = parameter;
    }

    public Integer getSatisfaction() {
        return satisfaction;
    }

    public void setSatisfaction(Integer satisfaction) {
        this.satisfaction = satisfaction;
    }

    public Integer getImportance() {
        return importance;
    }

    public void setImportance(Integer importance) {
        this.importance = importance;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

public String toString(Data data)
{
    return this.getSasid()+","+this.getSurveyid()+","+this.getParameter()+","+this.getSatisfaction()+","+this.getImportance()+","+this.getPeriod();
}

}
