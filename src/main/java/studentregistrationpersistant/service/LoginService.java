package studentregistrationpersistant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import studentregistrationpersistant.entitymodel.StudentEntityModel;

import studentregistrationpersistant.entitymodel.UserEntityModel;
import studentregistrationpersistant.model.LoginModel;

import studentregistrationpersistant.repository.LoginRepository;

@Service
public class LoginService {

	@Autowired
	LoginRepository repo;
	
	public boolean isUserAccountExist(String email) {
	    return repo.isAccountExist(email, UserEntityModel.class);
	}

	public boolean isStudentAccountExist(String email) {
	    return repo.isAccountExist(email, StudentEntityModel.class);
	}

	public UserEntityModel getUserByEmail(String email) {
	    return (UserEntityModel) repo.getAccountByEmail(email, UserEntityModel.class);
	}

	public StudentEntityModel getStudentByEmail(String email) {
	    return (StudentEntityModel) repo.getAccountByEmail(email, StudentEntityModel.class);
	}
	
	public LoginModel userentityToModel(UserEntityModel entitymodel) {
		LoginModel tranmodel=new LoginModel();
		tranmodel.setEmail(entitymodel.getEmail());
		tranmodel.setId(entitymodel.getId());
		tranmodel.setRole(entitymodel.getRole());	
		
		return tranmodel;
		
	}
	public LoginModel studententityToModel(StudentEntityModel entitymodel) {
		LoginModel tranmodel=new LoginModel();
		tranmodel.setEmail(entitymodel.getEmail());
		tranmodel.setId(entitymodel.getId());
		tranmodel.setRole("Student");	
		
		return tranmodel;
		
	}

}
