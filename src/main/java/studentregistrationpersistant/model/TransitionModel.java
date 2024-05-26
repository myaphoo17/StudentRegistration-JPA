package studentregistrationpersistant.model;

import org.springframework.web.multipart.MultipartFile;

public class TransitionModel {
	private int id;
	private MultipartFile photo;
	private int user_id;
	private int student_id;
	private int course_id;
	private String date;
	private String bindphoto;
	private StudentModel student;
	private CourseModel course;
	public TransitionModel() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public MultipartFile getPhoto() {
		return photo;
	}
	public void setPhoto(MultipartFile photo) {
		this.photo = photo;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getStudent_id() {
		return student_id;
	}
	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}
	public int getCourse_id() {
		return course_id;
	}
	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public StudentModel getStudent() {
		return student;
	}
	public void setStudent(StudentModel student) {
		this.student = student;
	}
	public CourseModel getCourse() {
		return course;
	}
	public void setCourse(CourseModel course) {
		this.course = course;
	}
	public String getBindphoto() {
		return bindphoto;
	}
	public void setBindphoto(String bindphoto) {
		this.bindphoto = bindphoto;
	}
	
}
