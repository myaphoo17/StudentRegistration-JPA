package studentregistrationpersistant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import studentregistrationpersistant.entitymodel.UserEntityModel;
import studentregistrationpersistant.entitymodel.UserPostEntityModel;
import studentregistrationpersistant.model.UserModel;
import studentregistrationpersistant.model.UserPostModel;
import studentregistrationpersistant.repository.StudentRepository;
import studentregistrationpersistant.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository repo;
	@Autowired
	StudentRepository sturepo;
	public int insertData(UserEntityModel umodel) {
		return repo.insertData(umodel);
	}
	
	public boolean isEmailAlreadyExist(String email) {
		return sturepo.checkingEmailHaveDataBase(email);
	}
	
	public UserEntityModel getUserById(int userID) {
		return repo.getUserById(userID);
	}
	public int insertPost(UserPostEntityModel post) {
		return repo.insertPost(post);
	}
	 public int deletePost(int PostId) {
		 return repo.deletePost(PostId);
	 }
	 public int updatePost(UserPostEntityModel post) {
		 return repo.updatePost(post);
	 }
	 public UserPostModel selectOnePost(int PostId) {
		 return repo.selectOnePost(PostId);
	 }
	 public UserModel selectOne(int PostId) {
		 return repo.selectOne(PostId);
	 }
	 public int updateData(UserEntityModel post) {
		 return repo.updateData(post);
	 }
	 public int deleteData(int PostId) {
		 return repo.deleteData(PostId);
	 }
	 public List<UserPostModel> selectAllPost(){
		 return repo.selectAllPost();
	 }
	 public List<UserModel> selectAll(){
		 return repo.selectAll();
	 }
	 public List<UserModel> selectAllAdmin(){
		 return repo.selectAllAdmin();
	 }
}
