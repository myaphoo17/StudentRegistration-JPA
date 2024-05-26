package studentregistrationpersistant.model;

import org.springframework.web.multipart.MultipartFile;

public class UserModel {
	private int id;
    private String name;
    private String email;
    private String password;
    private String confirm_password;
    private MultipartFile photo;
    private boolean active;
    private String role;
    private String bindphoto;
	public UserModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getBindphoto() {
		return bindphoto;
	}

	public void setBindphoto(String bindphoto) {
		this.bindphoto = bindphoto;
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
	
	public MultipartFile getPhoto() {
		return photo;
	}
	public void setPhoto(MultipartFile photo) {
		this.photo = photo;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getConfirm_password() {
		return confirm_password;
	}
	public void setConfirm_password(String confirm_password) {
		this.confirm_password = confirm_password;
	}
}
