package studentregistrationpersistant.model;

public class UserPostModel {

private int id;
	
	private String title;
	
	private String base64photo;
	
	private String detail;
	
	public UserPostModel() {}

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

	public String getBase64photo() {
		return base64photo;
	}

	public void setBase64photo(String base64photo) {
		this.base64photo = base64photo;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
}
