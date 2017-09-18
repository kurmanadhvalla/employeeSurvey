package com.ideas.survey.endpoint;

import com.ideas.survey.dto.EmployeeSurveyDetailsDTO;

import java.util.Comparator;

/**
 * Created by idnvlr on 9/6/2017.
 */
public abstract class MyRankingComp implements Comparator<EmployeeSurveyDetailsDTO> {
    @Override
    public int compare(EmployeeSurveyDetailsDTO e1, EmployeeSurveyDetailsDTO e2) {
        if(e1.getRanking() > e2.getRanking()){
            return 1;
        } else {
            return -1;
        }
    }
}
