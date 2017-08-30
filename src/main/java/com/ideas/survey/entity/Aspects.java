package com.ideas.survey.entity;



import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Aspects implements Serializable {
	 	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		@Id
	    @GeneratedValue(strategy=GenerationType.AUTO)
	    private Integer aspect_id;	 	
	 	private String aspect;
//	 	@OneToMany(mappedBy="aspects", fetch = FetchType.LAZY)
//	 	private Set<Feedback_Stats> Surveys = new HashSet<>(0);
//      
//		
//		public Set<Feedback_Stats> getSurveys() {
//			return Surveys;
//		}
//
//		public void setSurveys(Set<Feedback_Stats> surveys) {
//			Surveys = surveys;
//		}

		public Aspects() {
			super();
		}

		public Aspects(String aspect) {
			super();
			this.aspect = aspect;
		}

		public Integer getAspect_id() {
			return aspect_id;
		}

		public void setAspect_id(Integer aspect_id) {
			this.aspect_id = aspect_id;
		}

		public String getAspect() {
			return aspect;
		}

		public void setAspect(String aspect) {
			this.aspect = aspect;
		}

	 	
	 	
}
