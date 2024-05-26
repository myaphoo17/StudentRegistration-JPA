package studentregistrationpersistant.entitymodel;


import javax.validation.constraints.NotNull;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;

@Entity
@Table(name="reaction")
public class ReactionEntityModel {
	@NotNull
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int likereact;
	
	 @ManyToOne
	 @JoinColumn(name = "stu_activities_id")
	 private StuActivitiesEntityModel stuActivitiesEntityModel;
	 
	 @ManyToOne
	 @JoinColumn(name = "student_id")
	 private StudentEntityModel student;


	
	public ReactionEntityModel() {}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLikereact() {
		return likereact;
	}
	public void setLikereact(int likereact) {
		this.likereact = likereact;
	}
	public StuActivitiesEntityModel getStuActivitiesEntityModel() {
		return stuActivitiesEntityModel;
	}
	public void setStuActivitiesEntityModel(StuActivitiesEntityModel stuActivitiesEntityModel) {
		this.stuActivitiesEntityModel = stuActivitiesEntityModel;
	}
	public StudentEntityModel getStudent() {
		return student;
	}
	public void setStudent(StudentEntityModel student) {
		this.student = student;
	}
	
	
	
	
	

}
