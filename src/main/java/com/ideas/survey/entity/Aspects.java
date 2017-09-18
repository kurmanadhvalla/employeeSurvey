package com.ideas.survey.entity;



import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Aspects implements Serializable {
	 	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		@Id
	    @GeneratedValue(strategy=GenerationType.AUTO)
		@Column(name ="aspect_id")
	    private Integer aspectId;
		@Column(name ="aspect")
	 	private String aspectName;
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

		public Aspects(String aspectName) {
			super();
			this.aspectName = aspectName;
		}

		public Integer getAspectId() {
			return aspectId;
		}

		public void setAspectId(Integer aspectId) {
			this.aspectId = aspectId;
		}

		public String getAspectName() {
			return aspectName;
		}

		public void setAspectName(String aspectName) {
			this.aspectName = aspectName;
		}

	 	
	 	
}
