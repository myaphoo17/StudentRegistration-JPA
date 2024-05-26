package studentregistrationpersistant.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class StudentModel {
	private int id;
	private String name;
	private String email;
	private String dob;
	private String password;
	private String phno;
	private String address;
	private String gender;
	private String education;
	private List<String> courses;
	private MultipartFile photo;
	private String bindprofilephoto;
	private Boolean isDelete;
	private Boolean isaccept;
	private int course_id;
	private MultipartFile payphoto;
	private String binpaymentphoto;
	private String date;
    
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
	public String getPhno() {
		return phno;
	}
	public void setPhno(String phno) {
		this.phno = phno;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public List<String> getCourses() {
		return courses;
	}
	public void setCourses(List<String> courses) {
		this.courses = courses;
	}
	public MultipartFile getPhoto() {
		return photo;
	}
	public void setPhoto(MultipartFile photo) {
		this.photo = photo;
	}
	public Boolean getIsDelete() {
		return isDelete;
	}
	public Boolean getIsaccept() {
		return isaccept;
	}
	public void setIsaccept(Boolean isaccept) {
		this.isaccept = isaccept;
	}
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public int getCourse_id() {
		return course_id;
	}
	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}
	public MultipartFile getPayphoto() {
		return payphoto;
	}
	public void setPayphoto(MultipartFile payphoto) {
		this.payphoto = payphoto;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getBindprofilephoto() {
		return bindprofilephoto;
	}
	public void setBindprofilephoto(String bindprofilephoto) {
		this.bindprofilephoto = bindprofilephoto;
	}
	public String getBinpaymentphoto() {
		return binpaymentphoto;
	}
	public void setBinpaymentphoto(String binpaymentphoto) {
		this.binpaymentphoto = binpaymentphoto;
	}
	
}
