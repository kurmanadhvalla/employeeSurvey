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
	 private Integer aspect_rating;
 	 @Column(name="aspect_ranking")
	 private Integer aspect_ranking;
	 @ManyToOne
	 @JoinColumn(name="aspect_id")
	 private Aspects aspect;
	 @ManyToOne
	 @JoinColumn(name="survey_id")
	 private Employeefeedback emp_fdback;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAspect_rating() {
		return aspect_rating;
	}
	public void setAspect_rating(Integer aspect_rating) {
		this.aspect_rating = aspect_rating;
	}
	public Integer getAspect_ranking() {
		return aspect_ranking;
	}
	public void setAspect_ranking(Integer aspect_ranking) {
		this.aspect_ranking = aspect_ranking;
	}
	public Aspects getAspect() {
		return aspect;
	}
	public void setAspect(Aspects aspect) {
		this.aspect = aspect;
	}
	public Employeefeedback getEmp_fdback() {
		return emp_fdback;
	}
	public void setEmp_fdback(Employeefeedback emp_fdback) {
		this.emp_fdback = emp_fdback;
	}
  
}
