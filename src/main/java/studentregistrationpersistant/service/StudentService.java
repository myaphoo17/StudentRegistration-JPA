package studentregistrationpersistant.service;
import java.nio.charset.StandardCharsets;

import java.util.List;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import studentregistrationpersistant.entitymodel.CommentEntityModel;
import studentregistrationpersistant.entitymodel.StuActivitiesEntityModel;
import studentregistrationpersistant.entitymodel.StudentEntityModel;
import studentregistrationpersistant.entitymodel.TransationEntityModel;
import studentregistrationpersistant.model.*;
import studentregistrationpersistant.repository.StudentRepository;

@Service
public class StudentService {
	@Autowired
	StudentRepository repo;

	public int CreateStudent(StudentEntityModel student) {
		return repo.createStudent(student);
	}
	public int updateTransation(int sid ,byte[] photo) {
	    return repo.updateTransitionPayment(sid,photo);
	  }
	public int CreateTransation(TransationEntityModel transation) {
		return repo.transationStudent(transation);
	}
	public int GetStudentId(String  email) {
		return repo.selectStudentIdByEmail(email);
	}
	public int updateProfileStudent(StudentModel student) {
	    return repo.updateStudentProfile(student);
	  }
	public StudentEntityModel GetStudentById(int id) {
		return repo.getStudentById(id);
	}
	public List<TransationEntityModel> getTransitions() {
		return repo.getStudents();
	}
	public List<TransationEntityModel> getRegisterTransitions() {
		return repo.getRegisterStudents();
	}
	public int insertActivitiesPost(StuActivitiesEntityModel post) {
		return repo.insertActivitiesPost(post);
	}
	 public List<StuActivitiesModel> selectAllActivitiesPost(){
		 return repo.selectAllActivitiesPost();
	 }
	 public List<StuActivitiesModel> selectAllTimelineActivitiesPost(int id){
		 return repo.selectAllTimeLineActivitiesPost(id);
	 }
	
	 public boolean checkingEmailHaveDataBase(String  email) {
		 return repo.checkingEmailHaveDataBase(email);
	 }
	 public int confirmUpdatePassword(String email,String password) {
		 return repo.confirmUpdatePassword(email,password);
	 }
	 public int ConfirmStudent(int  id) {
		 return repo.confirmStudent(id);
	}
	 public int giveLike(int postId, int studentId) {
		 return repo.giveLike(postId, studentId);
	 }
	 public boolean hasStudentLikedPost(int postId, int studentId) {
		 return repo.hasStudentLikedPost(postId, studentId);
	 }
	 public List<StuActivitiesModel> getLikedPostsByStudentId(int studentId){
		 return repo.getLikedPostsByStudentId(studentId);
	 }
	 public int getTotalLikesForPost(int postId) {
		 return repo.getTotalLikesForPost(postId);
	 }
	 public int softDelete(int id) {
			
		return repo.softDelete(id);
	}
	 public int softDeletePost(int id) {
			
			return repo.softDeletePost(id);
		}

	public List<StuActivitiesModel> getDeletedPostsByStudentId(int studentId) {
		return repo.getDeletedPostsByStudentId(studentId);
	}

	public int recoverDeletedPost(int postId) {
		return repo.recoverDeletedPost(postId);
	}

	public StuActivitiesModel selectById(int postId) {
		return repo.selectById(postId);
	}

	public int updatePost(StuActivitiesEntityModel postEntity) {
		return repo.updatePost(postEntity);
	}
	public int giveComment(int postId, int studentId, String commentText) {
        return repo.giveComment(postId, studentId, commentText);
      }
      public List<CommentEntityModel> getAllCommentsByStuActivitiesId(int stuactivitiesId) {
        return repo.getAllCommentsByStuActivitiesId(stuactivitiesId);
      }

	public TransitionModel entityToModel(TransationEntityModel entitymodel) {
		TransitionModel tranmodel=new TransitionModel();
		StudentModel student=new StudentModel();
		student.setName(entitymodel.getStudent().getName());
		student.setEmail(entitymodel.getStudent().getEmail());
		student.setEducation(entitymodel.getStudent().getEducation());
		student.setDob(entitymodel.getStudent().getDob());
		student.setGender(entitymodel.getStudent().getGender());
		student.setAddress(entitymodel.getStudent().getAddress());
		student.setPhno(entitymodel.getStudent().getPhno());
		student.setId(entitymodel.getStudent().getId());
		student.setDate(entitymodel.getStudent().getJoin_date());
		byte[] bytes = entitymodel.getStudent().getPhoto();
		byte[] encodeBase64 = Base64.encodeBase64(bytes);
		String base64EncodedString = new String(encodeBase64, StandardCharsets.UTF_8);
		student.setBindprofilephoto(base64EncodedString);
		CourseModel course =new CourseModel();
		course.setId(entitymodel.getCourse().getId());
		course.setAmount(entitymodel.getCourse().getCourseFees());
		course.setName(entitymodel.getCourse().getName());
		tranmodel.setStudent(student);
		tranmodel.setCourse(course);
		byte[] transitionbytes = entitymodel.getPhoto();
		byte[] transitionencodeBase64 = Base64.encodeBase64(transitionbytes);
		String transitionbase64EncodedString = new String(transitionencodeBase64, StandardCharsets.UTF_8);
		tranmodel.setBindphoto(transitionbase64EncodedString);
	    return tranmodel;
	  }
}