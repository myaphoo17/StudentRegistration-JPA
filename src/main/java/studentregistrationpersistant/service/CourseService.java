package studentregistrationpersistant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import studentregistrationpersistant.entitymodel.CourseEntityModel;
import studentregistrationpersistant.repository.CourseRepository;
@Service
public class CourseService {
	@Autowired
	CourseRepository repo;
	public List<CourseEntityModel> selectAllCouree() {
		return repo.getAllCourses();
	}
	
	public CourseEntityModel getCourseById(int id) {
		return repo.getCourseById(id);
	}
	public List<CourseEntityModel> getCourseByStudentId(int id) {
		return repo.getCoursesByStudentId(id);
	}
	public int insertData(CourseEntityModel course) {
        return repo.insertData(course);
    }

    public int updateData(CourseEntityModel course) {
        return repo.updateData(course);
    }

	/*
	 * public int deleteData(int id) { return repository.deleteData(id); }
	 */

    public CourseEntityModel selectOne(int id) {
        return repo.selectOne(id);
    }
	public int softDelete(int id) {
		
		return repo.softDelete(id);
	}

}
