package studentregistrationpersistant.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import studentregistrationpersistant.entitymodel.UserEntityModel;
import studentregistrationpersistant.entitymodel.UserPostEntityModel;
import studentregistrationpersistant.model.LoginModel;
import studentregistrationpersistant.model.StuActivitiesModel;
import studentregistrationpersistant.model.UserModel;
import studentregistrationpersistant.model.UserPostModel;
import studentregistrationpersistant.service.StudentService;
import studentregistrationpersistant.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService service;
	@Autowired
	private StudentService stuservice;
	/*
	 * @RequestMapping(value = "/register_user", method = RequestMethod.GET) public
	 * ModelAndView register_user( Model model, HttpSession session) { LoginModel
	 * loggedInData = (LoginModel) session.getAttribute("loggedInData"); if
	 * (loggedInData == null) { model.addAttribute("error", "Please Login Again!");
	 * return new ModelAndView("login", "stupost", new StuActivitiesModel()); }
	 * model.addAttribute("loggedInData",loggedInData); return new
	 * ModelAndView("register_user", "user", new UserModel()); }
	 * 
	 * @RequestMapping(value = "/registerUser", method = RequestMethod.POST) public
	 * String registerUser(@ModelAttribute("user") UserModel user, Model model,
	 * HttpSession session) throws IOException { LoginModel loggedInData =
	 * (LoginModel) session.getAttribute("loggedInData"); if (loggedInData == null)
	 * { model.addAttribute("error", "Please Login Again!"); return "login"; }
	 * model.addAttribute("loggedInData",loggedInData);
	 * 
	 * if (service.isEmailAlreadyExist(user.getEmail())) {
	 * model.addAttribute("error", "Email already exists"); return "register_user";
	 * } else { UserEntityModel umodel = new UserEntityModel();
	 * umodel.setId(user.getId()); umodel.setName(user.getName());
	 * umodel.setEmail(user.getEmail()); umodel.setPassword(user.getPassword());
	 * umodel.setRole(user.getRole()); umodel.setPhoto(user.getPhoto().getBytes());
	 * int result = service.insertData(umodel);
	 * 
	 * if (result > 0) {
	 * 
	 * 
	 * // Redirect to a success page or display a success message return
	 * "activitiespage"; // Redirect to a success page } else { // Handle insertion
	 * failure model.addAttribute("error", "Failed to register user"); return
	 * "register_user"; } } }
	 * 
	 */
	@RequestMapping(value = "/usertimeline", method = RequestMethod.GET)
	public ModelAndView timeLineForm(HttpSession session, Model model) {
		LoginModel loggedInData = (LoginModel) session.getAttribute("loggedInData");

		model.addAttribute("loggedInData", loggedInData);

		List<UserPostModel> postDTOList = service.selectAllPost();
		if (postDTOList != null && !postDTOList.isEmpty()) {
			model.addAttribute("postDTOList", postDTOList);
		} else {
			model.addAttribute("message", "No posts found.");
		}

		return new ModelAndView("usertimeline", "stupost", new StuActivitiesModel());
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String displayView(ModelMap model) {
	    List<UserModel> userList = service.selectAll();
	    if (userList != null && !userList.isEmpty()) {
	        model.addAttribute("userList", userList);
	    } else {
	        model.addAttribute("msg", "Data not found");
	    }
	    return "displayuser"; 
	}
	
	@RequestMapping(value = "/admins", method = RequestMethod.GET)
	public String displayAdmins(ModelMap model) {
	    List<UserModel> userList = service.selectAllAdmin();
	    if (userList != null && !userList.isEmpty()) {
	        model.addAttribute("userList", userList);
	    } else {
	        model.addAttribute("msg", "Data not found");
	    }
	    return "displayadmin"; 
	}
	
	@RequestMapping(value="/setadduser",method = RequestMethod.GET)
	public ModelAndView updateUser() {
		return new ModelAndView("adduser","bean", new UserEntityModel());
	}
	
	@RequestMapping(value = "/adduser", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("bean") @Valid UserEntityModel bean, BindingResult bindingResult,
	        @RequestParam("photo") MultipartFile photoFile, ModelMap model) {
	   
	    // Check if email already exists
	    if (stuservice.checkingEmailHaveDataBase(bean.getEmail())) {
	        model.addAttribute("error", "Email already exists!"); 
	        model.addAttribute("data",bean);
	        return "adduser";
	        
	    }

	    try {
	        // Convert the uploaded file to byte array
	        byte[] photoData = photoFile.getBytes();
	        bean.setPhoto(photoData);
	        
	        // Insert the user data
	        int i = service.insertData(bean);
	        if (i == 0) {
	            model.addAttribute("error", "Add Fail!!");
	            return "adduser";
	        }
	    } catch (IOException e) {
	        // Handle file processing error
	        model.addAttribute("error", "Error processing file: " + e.getMessage());
	        return "adduser";
	    }
if(bean.getRole().equalsIgnoreCase("user")) {
	    return "redirect:/users";
}return "redirect:/admins";
	}

	
	@RequestMapping(value = "/setupupdate/{id}", method = RequestMethod.GET)
	public ModelAndView setUpUpdate(@PathVariable int id) {

	    UserModel userDTO = service.selectOne(id);

	    return new ModelAndView("updateuser", "bean", userDTO);
	}
	@RequestMapping(value = "/updateuser", method = RequestMethod.POST)
	public String updateUser(@ModelAttribute("bean") @Validated UserEntityModel bean,
	                         BindingResult bs,
	                         @RequestParam(value = "photoUpload", required = false) MultipartFile photoUpload,
	                         ModelMap model) {
	    if (bs.hasErrors()) {
	        return "updateuser";
	    } else {
	        if (photoUpload != null && !photoUpload.isEmpty()) {
	            try {
	                // Convert the uploaded file to byte array
	                byte[] photoData = photoUpload.getBytes();
	                bean.setPhoto(photoData);
	            } catch (IOException e) {
	                e.printStackTrace();
	                model.addAttribute("error", "Error processing file: " + e.getMessage());
	                return "updateuser";
	            }
	        } else {
	            // If no new photo is uploaded, retrieve the existing photo from the database and set it to bean
	            UserModel userDTO = service.selectOne(bean.getId());
	            if (userDTO != null && userDTO.getBindphoto() != null) {
	                // Decode Base64 string to byte array
	                byte[] photoData = Base64.getDecoder().decode(userDTO.getBindphoto());
	                bean.setPhoto(photoData);
	            }
	        }

	        int i = service.updateData(bean);
	        if (i == 0) {
	            model.addAttribute("error", "Update Fail!!");
	            return "updateuser";
	        }
	    }
	    return "redirect:/";
	}
	
	@RequestMapping(value = "/deleteuser/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable int id, ModelMap model) {

		int i = service.deleteData(id);
		if (i == 0) {
			model.addAttribute("error", "Delete Fail!!");
		}
		return "redirect:/users";
	}
	
	
	@RequestMapping(value = "user_profile", method = RequestMethod.GET)
	public String userProfile(ModelMap model, HttpSession session) {
		LoginModel loggedInData = (LoginModel) session.getAttribute("loggedInData");
		if (loggedInData == null) {
			model.addAttribute("error", "Please Login Again!");
			return "login";
		}
		model.addAttribute("loggedInData", loggedInData);

		UserEntityModel umodel = service.getUserById(loggedInData.getId());
		if (umodel != null) {
			// Convert the photo byte array to Base64 encoded string
			String photoBase64 = Base64.getEncoder().encodeToString(umodel.getPhoto());
			model.addAttribute("user", umodel);
			model.addAttribute("photoBase64", photoBase64); // Add photoBase64 attribute to model
			return "userProfile"; // Assuming userProfile.jsp is your view page
		}
		return "login";
	}

	// Handle POST request to add a new post
	// Controller method to add a post
	@RequestMapping(value = "/adduserpost", method = RequestMethod.POST)
	public String addPost(@ModelAttribute("post") @Validated UserPostEntityModel post, BindingResult result,
			@RequestParam("photo") MultipartFile photoFile, ModelMap model, HttpSession session) {
		// Retrieve userId from session
		LoginModel loggedInData = (LoginModel) session.getAttribute("loggedInData");
		if (loggedInData == null) {
			model.addAttribute("error", "Please Login Again!");
			return "login";
		}
		model.addAttribute("loggedInData", loggedInData);

		// Check if required fields are filled
		if (post.getTitle() == null || post.getTitle().isEmpty() || post.getDetail() == null
				|| post.getDetail().isEmpty()) {
			model.addAttribute("error", "Please fill in all the required fields.");
			return "/";
		}

		try {
			if (photoFile == null || photoFile.isEmpty()) {
				model.addAttribute("error", "Please upload a photo.");
				return "/";
			}
			// Convert the uploaded file to byte array
			byte[] photoData = photoFile.getBytes();
			post.setPhoto(photoData);

			// Retrieve user by userId
			UserEntityModel user = service.getUserById(loggedInData.getId());
			if (user == null) {
				model.addAttribute("error", "User not found.");
				return "/";
			}

			// Set the user to the post
			post.setUser(user);

			// Add the post using the service
			int i = service.insertPost(post);
			if (i == 0) {
				model.addAttribute("error", "Failed to add post!");
				return "/"; // Return to the add post form with an error message
			}
		} catch (IOException e) {
			// Handle file processing error
			model.addAttribute("error", "Error processing file: " + e.getMessage());
			return "/";
		}
		model.addAttribute("success", "Post Create Success!");
		return "redirect:/"; // Redirect to the home page after adding the post
	}

	// deletpost
	@RequestMapping(value = "/deletepost/{postId}", method = RequestMethod.GET)
	public String deletePost(@PathVariable int postId, ModelMap model) {
		int i = service.deletePost(postId);
		if (i == 0) {
			model.addAttribute("error", "Delete Post Fail!!");
		}
		return "redirect:/usertimeline"; // Redirect to the displayuserpost page after deletion
	}

	// updatepost
	@RequestMapping(value = "/setupupdatepost/{postId}", method = RequestMethod.GET)
	public ModelAndView setUpUpdatePost(@PathVariable int postId, ModelMap model) {
		UserPostModel postDTO = service.selectOnePost(postId);
		model.addAttribute("post", postDTO);
		return new ModelAndView("updateuserpost", "postdata", postDTO);
	}

	@RequestMapping(value = "/updateuserposted", method = RequestMethod.POST)
	public String updateUserPost(@ModelAttribute("postdata") UserPostEntityModel post,
			@RequestParam(value = "photoUpload", required = false) MultipartFile photoUpload, ModelMap model) {
		// Check if any required fields are blank
		if (post.getTitle() == null || post.getTitle().isEmpty() || post.getDetail() == null
				|| post.getDetail().isEmpty()) {
			model.addAttribute("error", "Please fill in all the required fields.");
			return "setupupdatepost"; // Return to the update user post form if there are validation errors
		}
		// Process the photo upload
		if (photoUpload != null && !photoUpload.isEmpty()) {
			try {
				// Convert the uploaded file to byte array
				byte[] photoData = photoUpload.getBytes();
				post.setPhoto(photoData);
			} catch (IOException e) {
				e.printStackTrace();
				model.addAttribute("error", "Error processing file: " + e.getMessage());
				return "setupupdatepost"; // Return to the update user post form with an error message
			}
		} else {
			// If no new photo is uploaded, no action is required
			// You may add other logic here if needed
		}

		int i = service.updatePost(post);
		if (i == 0) {
			model.addAttribute("error", "Update Post Fail!!");
			return "setupupdatepost"; // Return to the update user post form with an error message
		}

		return "redirect:/usertimelines"; // Redirect to the displayuserpost page after successful update
	}

}
