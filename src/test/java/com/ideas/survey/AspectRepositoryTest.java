package com.ideas.survey;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.ideas.survey.endpoint.EmpSurveyController;
import com.ideas.survey.entity.Aspects;

public class AspectRepositoryTest {

	private EmpSurveyController empSurveyController = new EmpSurveyController();

	@Test
	public void shouldFetchAllaspects() {
     List<Aspects> aspects = null;
     assertEquals(aspects, empSurveyController.getAspects());
	}

}
