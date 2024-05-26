package studentregistrationpersistant.entitymodel;
import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
@Entity
@Table(name="course")
public class CourseEntityModel implements Serializable {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;

	    @NotEmpty(message = "Course name cannot be empty")
	    @Size(max = 255, message = "Course name cannot exceed 255 characters")
	    @Column(name = "name")
	    private String name;

	    @NotNull(message = "Course fees cannot be empty")
	    @Min(value = 40000, message = "Course fees must be at least 40000 Ks")
	    @Column(name = "course_fees")
	    private double courseFees;

	    @NotEmpty(message = "Course description cannot be empty")
	    @Size(max = 1000, message = "Course description cannot exceed 1000 characters")
	    @Column(name = "course_description", columnDefinition = "TEXT")
	    private String courseDescription;

	    @Column(name = "is_deleted")
	    private boolean deleted;
	    @ManyToMany(mappedBy = "courses")
		private List<StudentEntityModel> students;
	public CourseEntityModel() {
		super(); 
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<StudentEntityModel> getStudents() {
		return students;
	}
	public void setStudents(List<StudentEntityModel> students) {
		this.students = students;
	}
	public double getCourseFees() {
		return courseFees;
	}
	public void setCourseFees(double courseFees) {
		this.courseFees = courseFees;
	}
	public String getCourseDescription() {
		return courseDescription;
	}
	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	
}
