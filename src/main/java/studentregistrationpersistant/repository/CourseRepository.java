package studentregistrationpersistant.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import studentregistrationpersistant.entitymodel.CourseEntityModel;
import studentregistrationpersistant.service.JPAUtil;

@Repository
public class CourseRepository {
    
    public CourseEntityModel getCourseById(int id) {
		EntityManager em = null;
		CourseEntityModel course = null;
		try {
			em = JPAUtil.getEntityManagerFactory().createEntityManager();

			course = em.createQuery("SELECT c FROM CourseEntityModel c WHERE c.id=:id", CourseEntityModel.class)
					.setParameter("id", id).getSingleResult();
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());

		} finally {
			em.close();
		}
		return course;
	}
    
    public List<CourseEntityModel> getCoursesByStudentId(int studentId) {
        EntityManager em = null;
        List<CourseEntityModel> courses = null;
        try {
            em = JPAUtil.getEntityManagerFactory().createEntityManager();
            
            TypedQuery<CourseEntityModel> query = em.createQuery(
                "SELECT c FROM CourseEntityModel c JOIN c.students s WHERE s.id = :studentId",
                CourseEntityModel.class);
            query.setParameter("studentId", studentId);
            courses = query.getResultList();
        } catch (NoResultException e) {
            // Handle if no course found for the student
            System.out.println("No course found for student with id: " + studentId);
        } catch (Exception e) {
            // Handle other exceptions
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Error occurred: " + e.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return courses;
    }

    public int insertData(CourseEntityModel course) {
        int result = 0;
        EntityManager em = null;
        try {
            em = JPAUtil.getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            em.persist(course);
            em.getTransaction().commit();
            result = 1;
        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println("this "+ e.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return result;
    }

    public int updateData(CourseEntityModel course) {
        int result = 0;
        EntityManager em = null;
        try {
            em = JPAUtil.getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            CourseEntityModel existingCourse = em.find(CourseEntityModel.class, course.getId());
            existingCourse.setName(course.getName());
            existingCourse.setCourseFees(course.getCourseFees());
            existingCourse.setCourseDescription(course.getCourseDescription());
            em.merge(existingCourse);
            em.getTransaction().commit();
            result = 1;
        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println(e.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return result;
    }

    public int deleteData(int id) {
        int result = 0;
        EntityManager em = null;
        try {
            em = JPAUtil.getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            CourseEntityModel course = em.find(CourseEntityModel.class, id);
            if (course != null) {
                em.remove(course);
                em.getTransaction().commit();
                result = 1;
            } else {
                em.getTransaction().rollback();
            }
        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println(e.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return result;
    }
    
    public int softDelete(int id) {
        int result = 0;
        EntityManager em = null;
        try {
            em = JPAUtil.getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            CourseEntityModel course = em.find(CourseEntityModel.class, id);
            if (course != null) {
                course.setDeleted(true); 
                em.merge(course);
                em.getTransaction().commit();
                result = 1;
            } else {
                em.getTransaction().rollback();
            }
        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println(e.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return result;
    }

    public CourseEntityModel selectOne(int id) {
        EntityManager em = null;
        CourseEntityModel course = null;
        try {
            em = JPAUtil.getEntityManagerFactory().createEntityManager();
            course = em.find(CourseEntityModel.class, id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return course;
    }

    public List<CourseEntityModel> getAllCourses() {
        EntityManager em = null;
        List<CourseEntityModel> courses = null;
        try {
            em = JPAUtil.getEntityManagerFactory().createEntityManager();
            courses = em.createQuery("SELECT c FROM CourseEntityModel c WHERE c.deleted = false", CourseEntityModel.class).getResultList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return courses;
    }

    
  

    
}


