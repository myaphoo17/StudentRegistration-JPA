package studentregistrationpersistant.entitymodel;
import java.io.Serializable;

import java.util.List;


import jakarta.persistence.JoinColumn;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;

import jakarta.persistence.Table;

@Entity
@Table(name ="student")
public class StudentEntityModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name ="name" ,length=100)
	private String name;
	@Column(name ="dob" ,length=100)
	private String dob;
	@Column(name ="gender" ,length=50)
	private String gender;
	@Column(name ="date" ,length=50)
	private String join_date;
	@Column(name ="email" ,length=50)
	private String email;
	@Column(name ="password" ,length=50)
	private String password;
	@Column(name ="address" ,length=50)
	private String address;
	@Column(name ="isdelete")
	private Boolean isdelete;
	@Column(name ="isaccept")
	private Boolean isaccept;
	@Column(name ="phno" ,length=60)
	private String phno;
	@Column(name ="education" ,length=100)
	private String education;
	@Column(name ="photo")
	@Lob
	private byte[] photo;
	@JoinTable(name = "courses_has_student",joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
	@ManyToMany(cascade = CascadeType.MERGE)
    private List<CourseEntityModel> courses ;
	public StudentEntityModel() {
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
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhno() {
		return phno;
	}
	public void setPhno(String phno) {
		this.phno = phno;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public List<CourseEntityModel> getCourses() {
		return courses;
	}
	public void setCourses(List<CourseEntityModel> courses) {
		this.courses = courses;
	}
	
	public String getJoin_date() {
		return join_date;
	}
	public void setJoin_date(String join_date) {
		this.join_date = join_date;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Boolean getIsdelete() {
		return isdelete;
	}
	public void setIsdelete(Boolean isdelete) {
		this.isdelete = isdelete;
	}
	public Boolean getIsaccept() {
		return isaccept;
	}
	public void setIsaccept(Boolean isaccept) {
		this.isaccept = isaccept;
	}
	
}
