package com.ideas.survey.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="employee_feedback_stats")
public class FeedbackStats implements Serializable {
	
	
	
 	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public FeedbackStats() {
		super();
	}
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	 private Integer id;
//	 @Column(name="aspect_id")
//	 private Integer aspect_id;
// 	 @Column(name="survey_id")
//	 private Integer survey_id;
 	 @Column(name="aspect_rating")
	 private Integer aspectRating;
 	 @Column(name="aspect_ranking")
	 private Integer aspectRanking;
	 @ManyToOne
	 @JoinColumn(name="aspect_id")
	 private Aspects aspect;
	 @ManyToOne
	 @JoinColumn(name="survey_id")
	 private EmployeeSurvey employeeSurvey;
	@Column(name="aspect_rating_reason")
     private String ratingReason;

	public String getRatingReason() {
		return ratingReason;
	}

	public void setRatingReason(String ratingReason) {
		this.ratingReason = ratingReason;
	}

	public FeedbackStats(Integer aspectRating, Integer aspectRanking, Aspects aspect, EmployeeSurvey employeeSurvey) {
		this.aspectRating = aspectRating;
		this.aspectRanking = aspectRanking;
		this.aspect = aspect;
		this.employeeSurvey = employeeSurvey;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAspectRating() {
		return aspectRating;
	}
	public void setAspectRating(Integer aspectRating) {
		this.aspectRating = aspectRating;
	}
	public Integer getAspectRanking() {
		return aspectRanking;
	}
	public void setAspectRanking(Integer aspectRanking) {
		this.aspectRanking = aspectRanking;
	}
	public Aspects getAspect() {
		return aspect;
	}
	public void setAspect(Aspects aspect) {
		this.aspect = aspect;
	}
	public EmployeeSurvey getEmployeeSurvey() {
		return employeeSurvey;
	}
	public void setEmployeeSurvey(EmployeeSurvey employeeSurvey) {
		this.employeeSurvey = employeeSurvey;
	}
  
}
