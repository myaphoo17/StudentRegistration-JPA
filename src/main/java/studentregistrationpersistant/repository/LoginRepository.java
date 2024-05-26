package studentregistrationpersistant.repository;
import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import studentregistrationpersistant.entitymodel.StudentEntityModel;
import studentregistrationpersistant.entitymodel.UserEntityModel;
import studentregistrationpersistant.service.JPAUtil;

@Repository
public class LoginRepository {

	 public List<UserEntityModel> getAllUser() {
	        EntityManager em = null;
	        EntityTransaction transaction = null;
	        List<UserEntityModel> users = null;
	        try {
	            em = JPAUtil.getEntityManagerFactory().createEntityManager();
	            transaction = em.getTransaction();
	            transaction.begin();
	            users = em.createQuery("SELECT u FROM UserEntityModel u", UserEntityModel.class).getResultList();
	           
	            transaction.commit();
	        } catch (Exception e) {
	            if (transaction != null && transaction.isActive()) {
	                transaction.rollback();
	            }
	            e.printStackTrace(); 
	        } finally {
	            if (em != null) {
	                em.close();
	            }
	        }
	        return users;
	    }
	 public List<StudentEntityModel> getAllStudent() {
	        EntityManager em = null;
	        EntityTransaction transaction = null;
	        List<StudentEntityModel> students = null;
	        try {
	            em = JPAUtil.getEntityManagerFactory().createEntityManager();
	            transaction = em.getTransaction();
	            transaction.begin();
	            students = em.createQuery("SELECT s FROM StudentEntityModel s", StudentEntityModel.class).getResultList();
	           
	            transaction.commit();
	        } catch (Exception e) {
	            if (transaction != null && transaction.isActive()) {
	                transaction.rollback();
	            }
	            e.printStackTrace(); 
	        } finally {
	            if (em != null) {
	                em.close();
	            }
	        }
	        return students;
	    }
	 
	 
	 public boolean isAccountExist(String email, Class<?> entityClass) {
		    EntityManager em = null;
		    try {
		        em = JPAUtil.getEntityManagerFactory().createEntityManager();
		        return findAccountByEmail(email, entityClass) != null;
		    } finally {
		        if (em != null) {
		            em.close();
		        }
		    }
		}

	 public <T> T findAccountByEmail(String email, Class<T> entityClass) {
		    EntityManager em = null;
		    try {
		        em = JPAUtil.getEntityManagerFactory().createEntityManager();
		        TypedQuery<T> query = null;
		        if (entityClass.equals(UserEntityModel.class)) {
		            query = em.createQuery("SELECT u FROM UserEntityModel u WHERE u.email = :email", entityClass);
		        } else if (entityClass.equals(StudentEntityModel.class)) {
		            query = em.createQuery("SELECT u FROM StudentEntityModel u WHERE u.email = :email", entityClass);
		        }
		        query.setParameter("email", email);
		        return query.getSingleResult();
		    } catch (NoResultException e) {
		        return null; // Return null if no result found
		    } finally {
		        if (em != null) {
		            em.close();
		        }
		    }
		}

		
		public Object getAccountByEmail(String email, Class<?> entityClass) {
	        EntityManager em = null;
	        try {
	            em = JPAUtil.getEntityManagerFactory().createEntityManager();
	            TypedQuery<?> query = null;
	            if (entityClass.equals(UserEntityModel.class)) {
	                query = em.createQuery("SELECT u FROM UserEntityModel u WHERE u.email = :email", UserEntityModel.class);
	            } else if (entityClass.equals(StudentEntityModel.class)) {
	                query = em.createQuery("SELECT s FROM StudentEntityModel s WHERE s.email = :email", StudentEntityModel.class);
	            }
	            query.setParameter("email", email);
	            return query.getSingleResult();
	        } catch (NoResultException e) {
	            return null; // Return null if no result found
	        } finally {
	            if (em != null) {
	                em.close();
	            }
	        }
	    }
}
