package com.ideas.survey.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


import javax.persistence.*;

@Entity
@Table(name="employee_feedback")
public class EmployeeSurvey implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<FeedbackStats> getFeedbackStats() {
		return feedbackStats;
	}

	public void setFeedbackStats(List<FeedbackStats> feedbackStats) {
		this.feedbackStats = feedbackStats;
	}

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name ="survey_id")
	 private Integer surveyId;

	@OneToMany
	@JoinColumn(name="survey_id")
	private List<FeedbackStats> feedbackStats;

	@Column(name ="empid")
	private String empid;
	@Column(name ="creation_date")
	private Date creationDate;
	@Column(name ="submission_date")
	private Date submissionDate;
	@Column(name ="status")
	private boolean status;
	

	public EmployeeSurvey() {
		super();
	}
	public Integer getSurveyId() {
		return surveyId;
	}

	public EmployeeSurvey(String empid, Date creationDate, boolean status) {
		this.empid = empid;
		this.creationDate = creationDate;
		this.status = status;
	}

	public void setSurveyId(Integer surveyId) {
		this.surveyId = surveyId;
	}
	public String getEmpid() {
		return empid;
	}
	public void setEmpid(String empid) {
		this.empid = empid;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Date getSubmissionDate() {
		return submissionDate;
	}
	public void setSubmissionDate(Date submissionDate) {
		this.submissionDate = submissionDate;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
	
	
	
	
	
	
	
	
}
