package com.ideas.survey.endpoint;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

import com.ideas.survey.dto.EmployeeSurvey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.ideas.survey.entity.AspectRepository;
import com.ideas.survey.entity.Aspects;
import com.ideas.survey.entity.Employeefeedback;
import com.ideas.survey.entity.EmployeefeedbackRepository;
import com.ideas.survey.entity.FeedbackStats;
import com.ideas.survey.entity.FeedbackstatsRepository;


@RestController
@RequestMapping("/survey")
public class EmpSurveyController {
	
	@Autowired private AspectRepository aspectRepository ;
	@Autowired private EmployeefeedbackRepository employee_feedbackRepository;
    @Autowired private FeedbackstatsRepository feedback_repository;

	@Autowired
	JdbcTemplate jdbcTemplate;


	@RequestMapping("/allSurveys")
	public List<Employeefeedback> getSurveys()
	{
		Iterable<Employeefeedback> Surveys = employee_feedbackRepository.findAll();
		List<Employeefeedback> target1 = new ArrayList<>();
		Surveys.forEach(target1::add);
		return target1;
	}




	@RequestMapping("/allStats")
	public List<FeedbackStats> getStats()
	{
		Iterable<FeedbackStats> Stats = feedback_repository.findAll();
		List<FeedbackStats> target2 = new ArrayList<>();
		Stats.forEach(target2::add);
		return target2;
	}


	@RequestMapping("/allAspects")
		public List<Aspects> getAspects()
		{
			Iterable<Aspects> itAscpects = aspectRepository.findAll();
			List<Aspects> target = new ArrayList<>();
			itAscpects.forEach(target::add);
			return target;
		}
	@RequestMapping("/save")
	public Aspects saveEmployee(@RequestParam("aspect") String aspect)
	{
		return aspectRepository.save(new Aspects(aspect));
	}


	@RequestMapping("/updateRating")
	public void updateRating(@RequestParam("empID") String empid,@RequestParam("surveyID") String surveyID, @RequestParam("aspectID") String aspectID, @RequestParam("rating") String rating)
	{

		jdbcTemplate.update(
				"UPDATE `employee_feedback_stats` SET aspect_rating =? WHERE survey_id=? AND aspect_id=?;",rating,surveyID,aspectID);

	}

	  @RequestMapping("/getSurvey")
			public List<EmployeeSurvey> getEmployeeSurvey(@RequestParam("empID") String empid)
			{
				ArrayList<EmployeeSurvey> employeeSurveys = new ArrayList<>();
 				// ifEMpdoesNotexit
//				 populate with materdata

				FetchData(empid, employeeSurveys);
//
                if (employeeSurveys.size()==0)
				{
					LocalDate date = LocalDate.now();
					final Integer[] survey_id = {null};
					jdbcTemplate.update("INSERT INTO `employee_feedback`(creation_date,empid,STATUS,submission_date) VALUES(?,?,0,?);",new Object[]{date ,empid,date});
					jdbcTemplate.query("SELECT survey_id FROM `employee_feedback` WHERE empid =?;", new Object[]{empid}, new RowMapper<Integer>() {
						@Override
						public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
							survey_id[0] = rs.getInt(1);

							return survey_id[0];
						}

					});
					for (int i = 1; i <=10; i++) {
						jdbcTemplate.update("\n" +
								"INSERT INTO `employee_feedback_stats`(aspect_ranking ,aspect_rating,aspect_id,survey_id)\n" +
								"VALUES(?,?,?,?);",new Object[]{i,1,i, survey_id[0]});
					}
					FetchData(empid, employeeSurveys);


				}

				return employeeSurveys;
}

	private void FetchData(@RequestParam("empID") String empid, ArrayList<EmployeeSurvey> employeeSurveys) {
		jdbcTemplate.query(" SELECT empid,survey_id,aspect_id, aspect,aspect_rating,aspect_ranking FROM `employee_feedback_stats` \n" +
                        " INNER JOIN `aspects` USING (aspect_id)\n" +
                        " INNER JOIN `employee_feedback` USING (survey_id) where empid =?", new  Object[] { empid }, new RowMapper<EmployeeSurvey>() {
                    @Override
                    public EmployeeSurvey mapRow(ResultSet rs, int rowNum) throws SQLException {
                        EmployeeSurvey employeeSurvey = new EmployeeSurvey();
                        employeeSurvey.setEmpID(rs.getNString(1));
                        employeeSurvey.setSurveyID(rs.getInt(2));
                        employeeSurvey.setAspectID(rs.getInt(3));
                        employeeSurvey.setAspectName(rs.getNString(4));
                        employeeSurvey.setRating(rs.getInt(5));
                        employeeSurvey.setRanking(rs.getInt(6));

                        employeeSurveys.add(employeeSurvey);

                       Collections.sort(employeeSurveys,new MyRankingComp());




                        return employeeSurvey;
                    }
                }
        );
	}



	class MyRankingComp extends com.ideas.survey.endpoint.MyRankingComp {

	}

}