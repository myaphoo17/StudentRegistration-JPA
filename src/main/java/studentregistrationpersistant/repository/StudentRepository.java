package studentregistrationpersistant.repository;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import studentregistrationpersistant.entitymodel.CommentEntityModel;
import studentregistrationpersistant.entitymodel.ReactionEntityModel;
import studentregistrationpersistant.entitymodel.StuActivitiesEntityModel;
import studentregistrationpersistant.entitymodel.StudentEntityModel;
import studentregistrationpersistant.entitymodel.TransationEntityModel;

import studentregistrationpersistant.model.StuActivitiesModel;
import studentregistrationpersistant.model.StudentModel;
import studentregistrationpersistant.service.JPAUtil;
@Repository
public class StudentRepository {
	public int createStudent(StudentEntityModel student) {
		int i = 0;
		EntityManager em = null;
		try {
			em = JPAUtil.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();
			em.persist(student);
			em.getTransaction().commit();
			i = 1;
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
		return i;
	}
	public int updateStudentProfile(StudentModel model) {
	    EntityManager em = null;
	    int i = 0;
	    try {
	      em = JPAUtil.getEntityManagerFactory().createEntityManager();
	      em.getTransaction().begin();
	      StudentEntityModel entity = em.find(StudentEntityModel.class, model.getId());
	      entity.setName(model.getName());
	      entity.setGender(model.getGender());
	      entity.setPhno(model.getPhno());
	      entity.setDob(model.getDob());
	      entity.setEducation(model.getEducation());
	      entity.setAddress(model.getAddress());
	      entity.setPhoto(model.getPhoto().getBytes());
	      em.merge(entity);
	      em.getTransaction().commit();
	      i = 1;

	    } catch (Exception e) {
	      em.getTransaction().rollback();
	      System.out.println(e.getMessage());

	    } finally {
	      em.close();
	    }return i;
	  }
	public int transationStudent(TransationEntityModel transation) {
		int i = 0;
		EntityManager em = null;
		try {
			em = JPAUtil.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();
			em.persist(transation);
			em.getTransaction().commit();
			i = 1;
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
		return i;
	}
	public int updateTransitionPayment(int studentId, byte[] newPhoto) {
	      int i = 0;
	      EntityManager em = null;
	      try {
	          em = JPAUtil.getEntityManagerFactory().createEntityManager();
	          em.getTransaction().begin();
	          
	          Query query = em.createQuery("UPDATE TransationEntityModel t SET t.photo = :newPhoto WHERE t.student.id = :studentId");
	          query.setParameter("newPhoto", newPhoto);
	          query.setParameter("studentId", studentId);
	          
	          i = query.executeUpdate(); // Execute the update query
	          
	          em.getTransaction().commit();
	      } catch (Exception e) {
	          if (em != null && em.getTransaction().isActive()) {
	              em.getTransaction().rollback();
	          }
	          System.out.println(e.getMessage());
	      } finally {
	          if (em != null) {
	              em.close();
	          }
	      }
	      return i;
	  }
	public int selectStudentIdByEmail(String email) {
	    EntityManager em = null;
	    int studentId = 0; 
	    try {
	        em = JPAUtil.getEntityManagerFactory().createEntityManager();

	        studentId = em.createQuery("SELECT s.id FROM StudentEntityModel s WHERE s.email=:email", Integer.class)
	                .setParameter("email", email)
	                .getSingleResult();
	    } catch (NoResultException e) {
	        System.out.println("No student found for email: " + email);
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        if (em != null) {
	            em.close();
	        }
	    }
	    return studentId;
	}
	
	public StudentEntityModel getStudentById(int stuId) {
	    EntityManager em = null;
	    try {
	        em = JPAUtil.getEntityManagerFactory().createEntityManager();
	        StudentEntityModel student = em.find(StudentEntityModel.class, stuId);
	        System.out.println("Retrieved user: " + student); // Add this line for debugging
	        return student;
	    } finally {
	        if (em != null) {
	            em.close();
	        }
	    }
	}
	//get all register student
	public List<TransationEntityModel> getRegisterStudents() {
	    EntityManager em = null;
	    List<TransationEntityModel> transitionlist = null;
	    try {
	        em = JPAUtil.getEntityManagerFactory().createEntityManager();
	        transitionlist = em.createQuery(
	                "SELECT t FROM TransationEntityModel t JOIN t.student s JOIN t.course c WHERE s.isaccept = 0 ORDER BY t.id DESC",
	                TransationEntityModel.class)
	            .getResultList();
	    } catch (Exception e) {
	 
	        e.printStackTrace();
	    } finally {
	        if (em != null && em.isOpen()) {
	            em.close();
	        }
	    }
	    return transitionlist;
	}
	
	//get all student
		public List<TransationEntityModel> getStudents() {
		    EntityManager em = null;
		    List<TransationEntityModel> transitionlist = null;
		    try {
		        em = JPAUtil.getEntityManagerFactory().createEntityManager();
		        transitionlist = em.createQuery(
		                "SELECT t FROM TransationEntityModel t JOIN t.student s JOIN t.course c WHERE s.isaccept = 1 AND s.isdelete = 0 ORDER BY t.id DESC",
		                TransationEntityModel.class)
		            .getResultList();
		    } catch (Exception e) {
		 
		        e.printStackTrace();
		    } finally {
		        if (em != null && em.isOpen()) {
		            em.close();
		        }
		    }
		    return transitionlist;
		}
	
	//insertUserPost
		 public int insertActivitiesPost(StuActivitiesEntityModel post) {
		        int i = 0;
		        EntityManager em = null;
		        try {
		            em = JPAUtil.getEntityManagerFactory().createEntityManager();
		            em.getTransaction().begin();
		            em.persist(post);
		            em.getTransaction().commit();
		            i = 1;
		        } catch (Exception e) {
		            if (em != null && em.getTransaction().isActive()) {
		                em.getTransaction().rollback();
		            }
		            System.out.println(e.getMessage());
		        } finally {
		            if (em != null) {
		                em.close();
		            }
		        }
		        return i;
		    }

		 public List<StuActivitiesModel> selectAllTimeLineActivitiesPost(int studentId) {
			    EntityManager em = null;
			    List<StuActivitiesModel> postDTOList = null;
			    try {
			        em = JPAUtil.getEntityManagerFactory().createEntityManager();

			        List<StuActivitiesEntityModel> postBeanList = em.createQuery(
			                "SELECT st FROM StuActivitiesEntityModel st JOIN st.student s WHERE s.id = :studentId AND st.isdelete = 0 ORDER BY st.id DESC", StuActivitiesEntityModel.class)
			                .setParameter("studentId", studentId)
			                .getResultList();
			        postDTOList = new ArrayList<>(); // Create an empty list of UserPostDTOs

			        for (StuActivitiesEntityModel postBean : postBeanList) {
			            StuActivitiesModel postModel = new StuActivitiesModel();
			            postModel.setId(postBean.getId());
			            postModel.setTitle(postBean.getTitle());
			            postModel.setDetail(postBean.getDetail());
			            postModel.setDate(postBean.getDate());
			            // Process photo for each post
			            byte[] photoData = postBean.getPhoto(); // Assuming the photo is stored as a byte array
			            if (photoData != null) {
			                String base64Photo = Base64.getEncoder().encodeToString(photoData);
			                postModel.setStuphotoactivities(base64Photo);
			            }

			            postModel.setStuname(postBean.getStudent().getName());
			            byte[] profilephoto = postBean.getStudent().getPhoto();
			            if (profilephoto != null) {
			                String base64Photo = Base64.getEncoder().encodeToString(profilephoto);
			                postModel.setStuphotoprofile(base64Photo);
			            }

			            postDTOList.add(postModel);
			        }
			    } catch (Exception e) {
			        if (em != null && em.getTransaction().isActive()) {
			            em.getTransaction().rollback();
			        }
			        System.out.println("Error occurred while selecting all posts: " + e.getMessage());
			    } finally {
			        if (em != null) {
			            em.close();
			        }
			    }
			    return postDTOList;
			}

		 
		 public List<StuActivitiesModel> selectAllActivitiesPost() {
			    EntityManager em = null;
			    List<StuActivitiesModel> postDTOList = null;
			    try {
			        em = JPAUtil.getEntityManagerFactory().createEntityManager();

			        List<StuActivitiesEntityModel> postBeanList = em.createQuery(
			        	    "SELECT st FROM StuActivitiesEntityModel st JOIN st.student s WHERE st.isdelete = 0 AND s.isdelete = 0 ORDER BY st.date DESC", StuActivitiesEntityModel.class)
			        	    .getResultList();
			        	postDTOList = new ArrayList<>();
 // Create an empty list of UserPostDTOs

			        for (StuActivitiesEntityModel postBean : postBeanList) {
			        	StuActivitiesModel postModel = new StuActivitiesModel();
			        	postModel.setId(postBean.getId());
			        	postModel.setTitle(postBean.getTitle());
			        	postModel.setDetail(postBean.getDetail());
			        	postModel.setDate(postBean.getDate());
			        	// Process photo for each post
			            byte[] photoData = postBean.getPhoto(); // Assuming the photo is stored as a byte array
			            if (photoData != null) {
			                String base64Photo = Base64.getEncoder().encodeToString(photoData);
			                postModel.setStuphotoactivities(base64Photo);
			            }
			            
			        	postModel.setStuname(postBean.getStudent().getName());
			        	byte[] profilephoto=postBean.getStudent().getPhoto();
			        	if (profilephoto != null) {
			                String base64Photo = Base64.getEncoder().encodeToString(profilephoto);
			                postModel.setStuphotoprofile(base64Photo);
			            }
			            
			            postDTOList.add(postModel);
			        }
			    } catch (Exception e) {
			        if (em != null && em.getTransaction().isActive()) {
			            em.getTransaction().rollback();
			        }
			        System.out.println("Error occurred while selecting all posts: " + e.getMessage());
			    } finally {
			        if (em != null) {
			            em.close();
			        }
			    }
			    return postDTOList;
			}
		
		 //haveChackingEmail
	     public boolean checkingEmailHaveDataBase(String email) {
	          EntityManager em = null;
	          boolean condition = false;
	          try {
	              em = JPAUtil.getEntityManagerFactory().createEntityManager();
	              List<String> allEmails = em.createQuery("SELECT s.email FROM StudentEntityModel s UNION SELECT u.email FROM UserEntityModel u", String.class)
	                                        .getResultList();
	              condition = allEmails.contains(email);
	          } catch (Exception e) {
	              e.printStackTrace();
	          } finally {
	              if (em != null) {
	                  em.close();
	              }
	          }
	          return condition;
	      }
	     //confirm update password
	     public int confirmUpdatePassword(String email, String password) {
	          EntityManager em = null;
	          int updatedCount = 0; // Counter for the number of updated records
	          try {
	              em = JPAUtil.getEntityManagerFactory().createEntityManager();
	              em.getTransaction().begin();

	              // Update password for the user with the given email in StudentEntityModel
	              updatedCount += em.createQuery("UPDATE StudentEntityModel s SET s.password = :password WHERE s.email = :email")
	                              .setParameter("password", password)
	                              .setParameter("email", email)
	                              .executeUpdate();

	              // Update password for the user with the given email in UserEntityModel
	              updatedCount += em.createQuery("UPDATE UserEntityModel u SET u.password = :password WHERE u.email = :email")
	                              .setParameter("password", password)
	                              .setParameter("email", email)
	                              .executeUpdate();

	              em.getTransaction().commit();
	          } catch (Exception e) {
	              if (em != null && em.getTransaction().isActive()) {
	                  em.getTransaction().rollback();
	              }
	              e.printStackTrace();
	          } finally {
	              if (em != null) {
	                  em.close();
	              }
	          }
	          return updatedCount;
	      }
		 
	   //confirm student
	     public int confirmStudent(int id) {
	         EntityManager em = null;
	         int result = 0; 
	         try {
	             em = JPAUtil.getEntityManagerFactory().createEntityManager();
	             em.getTransaction().begin();

	             result = em.createQuery("UPDATE StudentEntityModel s SET s.isaccept = 1 WHERE s.id = :id ")
	                     .setParameter("id", id)
	                     .executeUpdate();

	             em.getTransaction().commit();
	         } catch (Exception e) {
	             e.printStackTrace();
	             // Handle exceptions
	         } finally {
	             if (em != null) {
	                 em.close();
	             }
	         }
	         return result;
	     }
	     
			/*
			 * //SoftDelete public int softDeletePost(int postId) { int i = 0; EntityManager
			 * em = null; try { em =
			 * JPAUtil.getEntityManagerFactory().createEntityManager();
			 * em.getTransaction().begin();
			 * 
			 * StuActivitiesEntityModel post = em.find(StuActivitiesEntityModel.class,
			 * postId); if (post != null) { // Update the isDelete attribute to true (or 1)
			 * post.setIsdelete(false); // Merge the entity to update the database
			 * em.merge(post); em.getTransaction().commit(); i = 1; } else { // Post not
			 * found System.out.println("Post with ID " + postId + " not found."); } } catch
			 * (Exception e) { if (em != null && em.getTransaction().isActive()) {
			 * em.getTransaction().rollback(); }
			 * System.out.println("Error occurred while soft deleting post: " +
			 * e.getMessage()); } finally { if (em != null) { em.close(); } } return i; }
			 */
		 //giving like  
		 public int giveLike(int postId, int studentId) {
			    int result = 0;
			    EntityManager em = null;
			    EntityTransaction transaction = null;

			    try {
			        em = JPAUtil.getEntityManagerFactory().createEntityManager();
			        transaction = em.getTransaction();
			        transaction.begin();

			        // Find the post by postId
			        StuActivitiesEntityModel post = em.find(StuActivitiesEntityModel.class, postId);
			        if (post != null) {
			            // Create a new ReactionEntityModel instance for the like
			            ReactionEntityModel like = new ReactionEntityModel();
			            like.setLikereact(1); // Assuming 1 represents a like
			            like.setStuActivitiesEntityModel(post);

			            // Set the student ID for the like
			            // This is assuming studentId is the ID retrieved from the session
			            StudentEntityModel student = em.find(StudentEntityModel.class, studentId);
			            like.setStudent(student);

			            // Persist the like
			            em.persist(like);

			            transaction.commit();
			            result = 1; // Like successfully added
			        } else {
			            System.out.println("Post with ID " + postId + " not found.");
			        }
			    } catch (Exception e) {
			        if (transaction != null && transaction.isActive()) {
			            transaction.rollback();
			        }
			        System.out.println("Error occurred while giving like: " + e.getMessage());
			    } finally {
			        if (em != null) {
			            em.close();
			        }
			    }

			    return result;
			}

		  //check liked 
		 public boolean hasStudentLikedPost(int postId, int studentId) {
		        EntityManager em = null;
		        try {
		            em = JPAUtil.getEntityManagerFactory().createEntityManager();

		            // Execute the query to check if the given student has liked the post
		            Long count = em.createQuery("SELECT COUNT(r) FROM ReactionEntityModel r " +
		                                         "WHERE r.stuActivitiesEntityModel.id = :postId " +
		                                         "AND r.student.id = :studentId", Long.class)
		                         .setParameter("postId", postId)
		                         .setParameter("studentId", studentId)
		                         .getSingleResult();

		            // If count is greater than 0, it means the student has liked the post
		            return count > 0;
		        } catch (Exception e) {
		            e.printStackTrace();
		            return false;
		        } finally {
		            if (em != null) {
		                em.close();
		            }
		        }
		    }

		    // Method to retrieve all the posts liked by a specific student
		    public List<StuActivitiesModel> getLikedPostsByStudentId(int studentId) {
		        EntityManager em = null;
		        List<StuActivitiesModel> likedPosts = new ArrayList<>();
		        try {
		            em = JPAUtil.getEntityManagerFactory().createEntityManager();

		            // Execute the query to retrieve all the posts liked by the student
		            List<ReactionEntityModel> reactions = em.createQuery("SELECT r FROM ReactionEntityModel r " +
		                                                                 "WHERE r.student.id = :studentId", ReactionEntityModel.class)
		                                                     .setParameter("studentId", studentId)
		                                                     .getResultList();

		            // Iterate through the reactions and extract the liked posts
		            for (ReactionEntityModel reaction : reactions) {
		                StuActivitiesEntityModel likedPostEntity = reaction.getStuActivitiesEntityModel();
		                StuActivitiesModel likedPostModel = new StuActivitiesModel();
		                likedPostModel.setId(likedPostEntity.getId());
		                likedPostModel.setTitle(likedPostEntity.getTitle());
		                likedPostModel.setDetail(likedPostEntity.getDetail());
		                likedPostModel.setDate(likedPostEntity.getDate());
		                
		                // Process photo for the post
		                byte[] photoData = likedPostEntity.getPhoto();
		                if (photoData != null) {
		                    String base64Photo = Base64.getEncoder().encodeToString(photoData);
		                    likedPostModel.setStuphotoactivities(base64Photo);
		                }

		                // Set student name and profile photo
		                likedPostModel.setStuname(likedPostEntity.getStudent().getName());
		                byte[] profilephoto = likedPostEntity.getStudent().getPhoto();
		                if (profilephoto != null) {
		                    String base64Photo = Base64.getEncoder().encodeToString(profilephoto);
		                    likedPostModel.setStuphotoprofile(base64Photo);
		                }

		                likedPosts.add(likedPostModel);
		            }
		        } catch (Exception e) {
		            e.printStackTrace();
		        } finally {
		            if (em != null) {
		                em.close();
		            }
		        }
		        return likedPosts;
		    }
		//total like display for each post
		    public int getTotalLikesForPost(int postId) {
		        EntityManager em = null;
		        int totalLikes = 0;

		        try {
		            em = JPAUtil.getEntityManagerFactory().createEntityManager();

		            totalLikes = em.createQuery("SELECT COUNT(r) FROM ReactionEntityModel r WHERE r.stuActivitiesEntityModel.id = :postId", Long.class)
		                    .setParameter("postId", postId)
		                    .getSingleResult()
		                    .intValue();
		        } catch (Exception e) {
		            System.out.println("Error occurred while getting total likes for post: " + e.getMessage());
		        } finally {
		            if (em != null) {
		                em.close();
		            }
		        }

		        return totalLikes;
		    }

		    public int softDelete(int id) {
		        int result = 0;
		        EntityManager em = null;
		        try {
		            em = JPAUtil.getEntityManagerFactory().createEntityManager();
		            em.getTransaction().begin();
		            StudentEntityModel student = em.find(StudentEntityModel.class, id);
		            if (student != null) {
		                student.setIsdelete(true); 
		                em.merge(student);
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
		  //SoftDelete
			 public int softDeletePost(int postId) {
				    int i = 0;
				    EntityManager em = null;
				    try {
				        em = JPAUtil.getEntityManagerFactory().createEntityManager();
				        em.getTransaction().begin();

				        StuActivitiesEntityModel post = em.find(StuActivitiesEntityModel.class, postId);
				        if (post != null) {
				            // Update the isDelete attribute to true (or 1)
				            post.setIsdelete(true);
				            // Merge the entity to update the database
				            em.merge(post);
				            em.getTransaction().commit();
				            i = 1;
				        } else {
				            // Post not found
				            System.out.println("Post with ID " + postId + " not found.");
				        }
				    } catch (Exception e) {
				        if (em != null && em.getTransaction().isActive()) {
				            em.getTransaction().rollback();
				        }
				        System.out.println("Error occurred while soft deleting post: " + e.getMessage());
				    } finally {
				        if (em != null) {
				            em.close();
				        }
				    }
				    return i;
				}

		 // Display deleted student posts
	        public List<StuActivitiesModel> getDeletedPostsByStudentId(int studentId) {
	            EntityManager em = null;
	            List<StuActivitiesModel> deletedPosts = new ArrayList<>();
	            try {
	                em = JPAUtil.getEntityManagerFactory().createEntityManager();

	                // Retrieve deleted posts by student ID
	                List<StuActivitiesEntityModel> deletedPostEntities = em.createQuery(
	                    "SELECT st FROM StuActivitiesEntityModel st JOIN st.student s WHERE s.id = :studentId AND st.isdelete = true",
	                    StuActivitiesEntityModel.class)
	                    .setParameter("studentId", studentId)
	                    .getResultList();

	                for (StuActivitiesEntityModel postEntity : deletedPostEntities) {
	                    StuActivitiesModel postModel = new StuActivitiesModel();
	                    // Populate postModel from postEntity
	                    postModel.setId(postEntity.getId());
	                    postModel.setTitle(postEntity.getTitle());
	                    postModel.setDetail(postEntity.getDetail());
	                    postModel.setDate(postEntity.getDate());
	                    // You can set other properties as needed

	                    // Assuming the photo is stored as a byte array, convert it to base64
	                    byte[] photoData = postEntity.getPhoto();
	                    if (photoData != null) {
	                        String base64Photo = Base64.getEncoder().encodeToString(photoData);
	                        postModel.setStuphotoactivities(base64Photo);
	                    }

	                    // Assuming the student's profile photo is also stored as a byte array, convert it to base64
	                    byte[] profilePhotoData = postEntity.getStudent().getPhoto();
	                    if (profilePhotoData != null) {
	                        String base64ProfilePhoto = Base64.getEncoder().encodeToString(profilePhotoData);
	                        postModel.setStuphotoprofile(base64ProfilePhoto);
	                    }
	                    int totalLikes = getTotalLikesForPost(postEntity.getId()); 
	                    postModel.setTotalLikes(totalLikes);

	                    deletedPosts.add(postModel);
	                }
	            } catch (Exception e) {
	                System.out.println("Error occurred while retrieving deleted posts by student ID: " + e.getMessage());
	            } finally {
	                if (em != null) {
	                    em.close();
	                }
	            }
	            return deletedPosts;
	        }
	     // Recover
	        public int recoverDeletedPost(int postId) {
	            int i = 0;
	            EntityManager em = null;
	            try {
	                em = JPAUtil.getEntityManagerFactory().createEntityManager();
	                em.getTransaction().begin();

	                StuActivitiesEntityModel post = em.find(StuActivitiesEntityModel.class, postId);
	                if (post != null) {
	                    // Update the isDelete attribute to false (or 0) to recover the post
	                    post.setIsdelete(false);
	                    // Merge the entity to update the database
	                    em.merge(post);
	                    em.getTransaction().commit();
	                    i = 1;
	                } else {
	                    // Post not found
	                    System.out.println("Post with ID " + postId + " not found.");
	                }
	            } catch (Exception e) {
	                if (em != null && em.getTransaction().isActive()) {
	                    em.getTransaction().rollback();
	                }
	                System.out.println("Error occurred while recovering deleted post: " + e.getMessage());
	            } finally {
	                if (em != null) {
	                    em.close();
	                }
	            }
	            return i;
	        }
	      //selectbyPostId
	    	public StuActivitiesModel selectById(int postId) {
	    	    EntityManager em = null;
	    	    StuActivitiesModel postModel = null;
	    	    try {
	    	        em = JPAUtil.getEntityManagerFactory().createEntityManager();

	    	        StuActivitiesEntityModel postBean = em.find(StuActivitiesEntityModel.class, postId);
	    	        if (postBean != null) {
	    	            postModel = new StuActivitiesModel();
	    	            postModel.setId(postBean.getId());
	    	            postModel.setTitle(postBean.getTitle());
	    	            postModel.setDetail(postBean.getDetail());
	    	            postModel.setDate(postBean.getDate());

	    	            // Process photo for the post
	    	            byte[] photoData = postBean.getPhoto();
	    	            if (photoData != null) {
	    	                String base64Photo = Base64.getEncoder().encodeToString(photoData);
	    	                postModel.setStuphotoactivities(base64Photo);
	    	            }

	    	            // Set student name and profile photo
	    	            postModel.setStuname(postBean.getStudent().getName());
	    	            byte[] profilephoto = postBean.getStudent().getPhoto();
	    	            if (profilephoto != null) {
	    	                String base64ProfilePhoto = Base64.getEncoder().encodeToString(profilephoto);
	    	                postModel.setStuphotoprofile(base64ProfilePhoto);
	    	            }
	    	        }
	    	    } catch (Exception e) {
	    	        System.out.println("Error occurred while selecting post by id: " + e.getMessage());
	    	    } finally {
	    	        if (em != null) {
	    	            em.close();
	    	        }
	    	    }
	    	    return postModel;
	    	}
	    	
	    	 //updatepost
		    public int updatePost(StuActivitiesEntityModel postEntity) {
		        int i = 0;
		        EntityManager em = null;
		        try {
		            em = JPAUtil.getEntityManagerFactory().createEntityManager();
		            em.getTransaction().begin();

		            StuActivitiesEntityModel existingPostEntity = em.find(StuActivitiesEntityModel.class, postEntity.getId());
		            if (existingPostEntity != null) {
		                // Update existingPostEntity with values from postEntity
		                existingPostEntity.setTitle(postEntity.getTitle());
		                existingPostEntity.setDetail(postEntity.getDetail());
		                
		                // Check if a new photo is uploaded
		                if (postEntity.getPhoto() != null && postEntity.getPhoto().length > 0) {
		                    existingPostEntity.setPhoto(postEntity.getPhoto());
		                }
		                // Update other properties as needed

		                em.getTransaction().commit();
		                i = 1;
		            } else {
		                System.out.println("Post with ID " + postEntity.getId() + " not found.");
		            }
		        } catch (Exception e) {
		            if (em != null && em.getTransaction().isActive()) {
		                em.getTransaction().rollback();
		            }
		            System.out.println("Error occurred while updating post: " + e.getMessage());
		        } finally {
		            if (em != null) {
		                em.close();
		            }
		        }
		        return i;
		    }
		    
		    public int giveComment(int postId, int studentId, String commentText) {
	            int result = 0;
	            EntityManager em = null;
	            EntityTransaction transaction = null;

	            try {
	                em = JPAUtil.getEntityManagerFactory().createEntityManager();
	                transaction = em.getTransaction();
	                transaction.begin();

	                // Find the post by postId
	                StuActivitiesEntityModel post = em.find(StuActivitiesEntityModel.class, postId);
	                if (post != null) {
	                    // Create a new CommentEntityModel instance for the comment
	                    CommentEntityModel comment = new CommentEntityModel();
	                    comment.setText(commentText);
	                    comment.setPost(post);

	                    // Set the student ID for the comment
	                    // This is assuming studentId is the ID retrieved from the session
	                    StudentEntityModel student = em.find(StudentEntityModel.class, studentId);
	                    comment.setStudent(student);

	                    // Persist the comment
	                    em.persist(comment);

	                    transaction.commit();
	                    result = 1; // Comment successfully added
	                } else {
	                    System.out.println("Post with ID " + postId + " not found.");
	                }
	            } catch (Exception e) {
	                if (transaction != null && transaction.isActive()) {
	                    transaction.rollback();
	                }
	                System.out.println("Error occurred while giving comment: " + e.getMessage());
	            } finally {
	                if (em != null) {
	                    em.close();
	                }
	            }

	            return result;
	        }
	        //get comments by post id
	        public List<CommentEntityModel> getAllCommentsByStuActivitiesId(int stuactivitiesId) {
	            EntityManager em = null;
	            List<CommentEntityModel> comments = null;
	            try {
	                em = JPAUtil.getEntityManagerFactory().createEntityManager();
	                comments = em.createQuery("SELECT c FROM CommentEntityModel c WHERE c.post.id = :stuactivitiesId", CommentEntityModel.class)
	                            .setParameter("stuactivitiesId", stuactivitiesId)
	                            .getResultList();
	            } catch (Exception e) {
	                System.out.println("Error occurred while retrieving comments by stuactivitiesID: " + e.getMessage());
	            } finally {
	                if (em != null) {
	                    em.close();
	                }
	            }
	            return comments;
	        }
		    
}
