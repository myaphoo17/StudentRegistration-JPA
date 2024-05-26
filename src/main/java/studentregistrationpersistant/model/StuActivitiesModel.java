package studentregistrationpersistant.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import studentregistrationpersistant.entitymodel.CommentEntityModel;

public class StuActivitiesModel {
	
	private int id;
	private String title;
	private MultipartFile photo;
	private String stuphotoactivities;
	private String detail;
	private String date;
	private String stuname;
	private MultipartFile stuphoto;
	private String stuphotoprofile;
	private Boolean isDelete;
	private int totalLikes;
	private List<CommentEntityModel> comments;
	private byte[] photoData;
	  
	public int getTotalLikes() {
		return totalLikes;
	}

	public void setTotalLikes(int totalLikes) {
		this.totalLikes = totalLikes;
	}

	public StuActivitiesModel() {
		
	}

	public String getStuphotoactivities() {
		return stuphotoactivities;
	}

	public void setStuphotoactivities(String stuphotoactivities) {
		this.stuphotoactivities = stuphotoactivities;
	}

	public String getStuphotoprofile() {
		return stuphotoprofile;
	}

	public void setStuphotoprofile(String stuphotoprofile) {
		this.stuphotoprofile = stuphotoprofile;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStuname() {
		return stuname;
	}

	public void setStuname(String stuname) {
		this.stuname = stuname;
	}

	public MultipartFile getPhoto() {
		return photo;
	}

	public void setPhoto(MultipartFile photo) {
		this.photo = photo;
	}

	public MultipartFile getStuphoto() {
		return stuphoto;
	}

	public void setStuphoto(MultipartFile stuphoto) {
		this.stuphoto = stuphoto;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public byte[] getPhotoData() {
		return photoData;
	}

	public void setPhotoData(byte[] photoData) {
		this.photoData = photoData;
	}

	public List<CommentEntityModel> getComments() {
		return comments;
	}

	public void setComments(List<CommentEntityModel> comments) {
		this.comments = comments;
	}

	
	
}
