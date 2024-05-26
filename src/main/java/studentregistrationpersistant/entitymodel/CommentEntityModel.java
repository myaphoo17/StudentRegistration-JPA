package studentregistrationpersistant.entitymodel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "comments")
public class CommentEntityModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String text;

    @ManyToOne
    @JoinColumn(name = "stu_activities_id", nullable = false)
    private StuActivitiesEntityModel post;

    @ManyToOne
	@JoinColumn(name = "student_id")
	private StudentEntityModel student;
    
    public CommentEntityModel() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public StuActivitiesEntityModel getPost() {
		return post;
	}

	public void setPost(StuActivitiesEntityModel post) {
		this.post = post;
	}

	public StudentEntityModel getStudent() {
		return student;
	}

	public void setStudent(StudentEntityModel student) {
		this.student = student;
	}
    
}
