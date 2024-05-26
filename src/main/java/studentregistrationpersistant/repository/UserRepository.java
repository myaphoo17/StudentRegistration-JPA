package studentregistrationpersistant.repository;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import studentregistrationpersistant.entitymodel.UserEntityModel;
import studentregistrationpersistant.entitymodel.UserPostEntityModel;
import studentregistrationpersistant.model.UserModel;
import studentregistrationpersistant.model.UserPostModel;
import studentregistrationpersistant.service.JPAUtil;

@Repository
public class UserRepository {


	/*
	 * public boolean isEmailAlreadyExist(String email) { EntityManager em = null;
	 * try { em = JPAUtil.getEntityManagerFactory().createEntityManager(); return
	 * isEmailAlreadyExist(em, email); } finally { if (em != null) { em.close(); } }
	 * }
	 */
	/*
	 * private boolean isEmailAlreadyExist(EntityManager em, String email) { try {
	 * Long count = em.
	 * createQuery("SELECT COUNT(u) FROM UserEntityModel u WHERE u.email = :email",
	 * Long.class) .setParameter("email", email) .getSingleResult(); return count >
	 * 0; } catch (NoResultException e) { // If there is no result (email doesn't
	 * exist), return false return false; } }
	 */
	
	public UserEntityModel getUserById(int userID) {
	    EntityManager em = null;
	    try {
	        em = JPAUtil.getEntityManagerFactory().createEntityManager();
	        UserEntityModel user = em.find(UserEntityModel.class, userID);
	        System.out.println("Retrieved user: " + user); // Add this line for debugging
	        return user;
	    } finally {
	        if (em != null) {
	            em.close();
	        }
	    }
	}
	
	 public int insertPost(UserPostEntityModel post) {
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

	//DelteUserPost
		 public int deletePost(int postId) {
			    int i = 0;
			    EntityManager em = null;
			    try {
			        em = JPAUtil.getEntityManagerFactory().createEntityManager();
			        em.getTransaction().begin();

			        UserPostEntityModel post = em.find(UserPostEntityModel.class, postId);
			        if (post != null) {
			            em.remove(post);
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
			        System.out.println("Error occurred while deleting post: " + e.getMessage());
			    } finally {
			        if (em != null) {
			            em.close();
			        }
			    }
			    return i;
			}
		 
		 //updateUserPost
		 public int updatePost(UserPostEntityModel post) {
			    int i = 0;
			    EntityManager em = null;
			    try {
			        em = JPAUtil.getEntityManagerFactory().createEntityManager();
			        em.getTransaction().begin();

			        UserPostEntityModel existingPost = em.find(UserPostEntityModel.class, post.getId());
			        if (existingPost != null) {
			            // Update the fields of the existing post
			            existingPost.setTitle(post.getTitle());
			            existingPost.setDetail(post.getDetail());
			            // You can update other fields as needed

			            // If a new photo is provided, update the photo
			            if (post.getPhoto() != null && post.getPhoto().length > 0) {
			                existingPost.setPhoto(post.getPhoto());
			            }

			            em.getTransaction().commit();
			            i = 1; // Indicates successful update
			        } else {
			            // Post not found
			            System.out.println("Post with ID " + post.getId() + " not found.");
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
		 
		//selectonepost
		 public UserPostModel selectOnePost(int id) {
			    EntityManager em = null;
			    UserPostModel postDTO = null;
			    try {
			        em = JPAUtil.getEntityManagerFactory().createEntityManager();

			        UserPostEntityModel postBean = em.createQuery("SELECT p FROM UserPostEntityModel p WHERE p.id=:id", UserPostEntityModel.class)
			                .setParameter("id", id).getSingleResult();

			        postDTO = new UserPostModel();
			        postDTO.setId(postBean.getId());
			        postDTO.setTitle(postBean.getTitle());
			        postDTO.setDetail(postBean.getDetail());

			        // Process photo (assuming byte array)
			        byte[] photoData = postBean.getPhoto();
			        if (photoData != null) {
			            String base64Photo = Base64.getEncoder().encodeToString(photoData);
			            postDTO.setBase64photo(base64Photo);
			        }
			    } catch (Exception e) {
			        // Handle exception
			        System.err.println("Error occurred while selecting user post: " + e.getMessage());
			    } finally {
			        if (em != null) {
			            em.close();
			        }
			    }
			    return postDTO;
			}
		 
		// insert
			public int insertData(UserEntityModel user) {
				int i = 0;
				EntityManager em = null;
				try {
					em = JPAUtil.getEntityManagerFactory().createEntityManager();
					em.getTransaction().begin();
					em.persist(user);
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

			// update
			public int updateData(UserEntityModel user) {
				EntityManager em = null;
				int i = 0;
				try {
					em = JPAUtil.getEntityManagerFactory().createEntityManager();
					em.getTransaction().begin();
					UserEntityModel bean = em.find(UserEntityModel.class, user.getId());
					bean.setName(user.getName());
					bean.setEmail(user.getEmail());
					bean.setPassword(user.getPassword());
					bean.setPhoto(user.getPhoto());

					em.merge(bean);
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

			// delete
			public int deleteData(int id) {
				EntityManager em = null;
				int i = 0;
				try {
					em = JPAUtil.getEntityManagerFactory().createEntityManager();
					em.getTransaction().begin();

					UserEntityModel bean = em.find(UserEntityModel.class, id);

					em.remove(bean);
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

			// selectById
			public UserModel selectOne(int id) {
				EntityManager em = null;
				UserModel userDTO = null;
				try {
					em = JPAUtil.getEntityManagerFactory().createEntityManager();

					UserEntityModel userBean = em.createQuery("SELECT u FROM UserEntityModel u WHERE u.id=:id", UserEntityModel.class)
							.setParameter("id", id).getSingleResult();

					userDTO = new UserModel();
					userDTO.setId(userBean.getId());
					userDTO.setName(userBean.getName());
					userDTO.setEmail(userBean.getEmail());
					userDTO.setPassword(userBean.getPassword());

					// Process photo (assuming byte array)
					byte[] photoData = userBean.getPhoto();
					if (photoData != null) {
						String base64Photo = Base64.getEncoder().encodeToString(photoData);
						userDTO.setBindphoto(base64Photo);
					}
				} catch (Exception e) {
					// Handle exception
					System.err.println("Error occurred while selecting user: " + e.getMessage());
				} finally {
					if (em != null) {
						em.close();
					}
				}
				return userDTO;
			}

			// selectAll
			public List<UserModel> selectAll() {
				EntityManager em = null;
				List<UserModel> userList = null;
				try {
					em = JPAUtil.getEntityManagerFactory().createEntityManager();

					List<UserEntityModel> userBeanList = em.createQuery("SELECT u FROM UserEntityModel u WHERE u.role='User' ", UserEntityModel.class).getResultList();
					userList = new ArrayList<>(); // Create an empty list of UserDTOs

					for (UserEntityModel userBean : userBeanList) {
						UserModel userDTO = new UserModel();
						userDTO.setId(userBean.getId());
						userDTO.setName(userBean.getName());
						userDTO.setEmail(userBean.getEmail());
						userDTO.setPassword(userBean.getPassword());

						// Process photo for each user
						byte[] photoData = userBean.getPhoto(); // Assuming the photo is stored as a byte array
						if (photoData != null) {
							String base64Photo = Base64.getEncoder().encodeToString(photoData);
							userDTO.setBindphoto(base64Photo);
						}

						userList.add(userDTO);
					}
				} catch (Exception e) {
					em.getTransaction().rollback();
					System.out.println(e.getMessage());
				} finally {
					em.close();
				}
				return userList;
			}
			
			// selectAllAdmin
			public List<UserModel> selectAllAdmin() {
			EntityManager em = null;
			List<UserModel> userList = null;
			try {
								em = JPAUtil.getEntityManagerFactory().createEntityManager();

								List<UserEntityModel> userBeanList = em.createQuery("SELECT u FROM UserEntityModel u WHERE u.role='Admin' ", UserEntityModel.class).getResultList();
								userList = new ArrayList<>(); // Create an empty list of UserDTOs

								for (UserEntityModel userBean : userBeanList) {
									UserModel userDTO = new UserModel();
									userDTO.setId(userBean.getId());
									userDTO.setName(userBean.getName());
									userDTO.setEmail(userBean.getEmail());
									userDTO.setPassword(userBean.getPassword());

									// Process photo for each user
									byte[] photoData = userBean.getPhoto(); // Assuming the photo is stored as a byte array
									if (photoData != null) {
										String base64Photo = Base64.getEncoder().encodeToString(photoData);
										userDTO.setBindphoto(base64Photo);
									}

									userList.add(userDTO);
								}
							} catch (Exception e) {
								em.getTransaction().rollback();
								System.out.println(e.getMessage());
							} finally {
								em.close();
							}
							return userList;
						}
			 
			// Select all posts
				public List<UserPostModel> selectAllPost() {
					EntityManager em = null;
					List<UserPostModel> postDTOList = null;
					try {
						em = JPAUtil.getEntityManagerFactory().createEntityManager();

						List<UserPostEntityModel> postBeanList = em.createQuery("SELECT p FROM UserPostEntityModel p", UserPostEntityModel.class)
								.getResultList();
						postDTOList = new ArrayList<>(); // Create an empty list of UserPostDTOs

						for (UserPostEntityModel postBean : postBeanList) {
							UserPostModel postDTO = new UserPostModel();
							postDTO.setId(postBean.getId());
							postDTO.setTitle(postBean.getTitle());
							postDTO.setDetail(postBean.getDetail());

							// Process photo for each post
							byte[] photoData = postBean.getPhoto(); // Assuming the photo is stored as a byte array
							if (photoData != null) {
								String base64Photo = Base64.getEncoder().encodeToString(photoData);
								postDTO.setBase64photo(base64Photo);
							}

							postDTOList.add(postDTO);
						}
					} catch (Exception e) {
						em.getTransaction().rollback();
						System.out.println("Error occurred while selecting all posts: " + e.getMessage());
					} finally {
						if (em != null) {
							em.close();
						}
					}
					return postDTOList;
				}

			 
	 
}
