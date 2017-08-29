package com.ideas.survey.entity;

import java.io.Serializable;
import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;
@Entity
@Table(name="employee_feedback")
public class Employeefeedback implements Serializable {

	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	 private Integer survey_id;
	@Column(name ="empid")
	private Integer empid;
	@Column(name ="creation_date")
	private Date creation_date;
	@Column(name ="submission_date")
	private Date submission_date;
	@Column(name ="status")
	private boolean status;
	
	/*@OneToMany(mappedBy="employee_feedback", fetch = FetchType.LAZY)
 	private Set<Feedback_Stats> Surveydetails = new HashSet<>(0);
	
	
	
	public Set<Feedback_Stats> getSurveydetails() {
		return Surveydetails;
	}
	public void setSurveydetails(Set<Feedback_Stats> surveydetails) {
		Surveydetails = surveydetails;
	}*/
	public Employeefeedback() {
		super();
	}
	public Integer getSurvey_id() {
		return survey_id;
	}
	public void setSurvey_id(Integer survey_id) {
		this.survey_id = survey_id;
	}
	public Integer getEmpid() {
		return empid;
	}
	public void setEmpid(Integer empid) {
		this.empid = empid;
	}
	public Date getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}
	public Date getSubmission_date() {
		return submission_date;
	}
	public void setSubmission_date(Date submission_date) {
		this.submission_date = submission_date;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
	
	
	
	
	
	
	
	
}
