package studentregistrationpersistant.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import studentregistrationpersistant.entitymodel.CourseEntityModel;
import studentregistrationpersistant.entitymodel.StuActivitiesEntityModel;
import studentregistrationpersistant.entitymodel.StudentEntityModel;
import studentregistrationpersistant.entitymodel.TransationEntityModel;

import studentregistrationpersistant.model.LoginModel;
import studentregistrationpersistant.model.StuActivitiesModel;
import studentregistrationpersistant.model.StudentModel;
import studentregistrationpersistant.model.TransitionModel;
import studentregistrationpersistant.service.CourseService;
import studentregistrationpersistant.service.StudentService;

@Controller

public class StudentController {
	@Autowired
	private CourseService courseService;
	@Autowired
	private StudentService studentservice;
	@Autowired
	private JavaMailSender mailSender;

	private String DateTime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String datetime = dtf.format(now);
		return datetime;
	}

	@GetMapping("/createstudent")
	public ModelAndView createStudent(HttpServletRequest request, HttpSession session, Model model,
			@ModelAttribute("error") String data, @RequestParam("cid") int id) {
		CourseEntityModel course = courseService.getCourseById(id);
		model.addAttribute("coursedata", course);
		model.addAttribute("courseid", id);
		return new ModelAndView("createstudent", "studentinform", new StudentModel());
	}

	@PostMapping("/addstudent")
	public Object createStudent(@ModelAttribute("studentinform") StudentModel studentdata, HttpSession session,
			Model model) throws IOException {
		boolean condition=studentservice.checkingEmailHaveDataBase(studentdata.getEmail());
	    System.out.println("course id post "+ studentdata.getCourse_id());
	    if(condition == true) {
	      CourseEntityModel course=courseService.getCourseById(studentdata.getCourse_id());
	      model.addAttribute("coursedata",course);
	        model.addAttribute("studentdata",studentdata);
	        model.addAttribute("courseid",studentdata.getCourse_id());
	        model.addAttribute("emailerror","E-Mail Is Already Exists! ");
	        return new ModelAndView("createstudent","studentinform",new StudentModel());
	      }
		StudentEntityModel student = new StudentEntityModel();
		student.setName(studentdata.getName());
		student.setDob(studentdata.getDob());
		student.setPhno(studentdata.getPhno());
		student.setGender(studentdata.getGender());
		student.setAddress(studentdata.getAddress());
		student.setEmail(studentdata.getEmail());
		student.setPassword(studentdata.getPassword());
		student.setDob(studentdata.getDob());
		student.setPhoto(studentdata.getPhoto().getBytes());
		student.setJoin_date(DateTime());
		student.setEducation(studentdata.getEducation());
		student.setIsaccept(false);
		student.setIsdelete(false);
		List<CourseEntityModel> lists = new ArrayList<CourseEntityModel>();
		CourseEntityModel centity = new CourseEntityModel();
		centity.setId(studentdata.getCourse_id());
		lists.add(centity);
		student.setCourses(lists);
		int i = studentservice.CreateStudent(student);

		if (i != 0) {
			TransationEntityModel transation = new TransationEntityModel();
			int stuid = studentservice.GetStudentId(student.getEmail());
			StudentEntityModel entity = new StudentEntityModel();
			entity.setId(stuid);
			CourseEntityModel courseentity = new CourseEntityModel();
			courseentity.setId(studentdata.getCourse_id());
			transation.setPhoto(studentdata.getPayphoto().getBytes());
			transation.setStudent(entity);
			transation.setCourse(courseentity);
			studentservice.CreateTransation(transation);
			return "redirect:/";
		}
		return "addstudent";
	}

	@GetMapping("/regstudents")
	public String displayRegisterStudents(HttpSession session, Model model) {
		LoginModel loggedInData = (LoginModel) session.getAttribute("loggedInData");
		if (loggedInData == null) {
			model.addAttribute("error", "Please Login Again!");
			return "login";
		}
		model.addAttribute("loggedInData", loggedInData);

		List<TransationEntityModel> transitions = studentservice.getRegisterTransitions();
		List<TransitionModel> transitionModels = new ArrayList<TransitionModel>();
		for (TransationEntityModel tranentity : transitions) {
			TransitionModel transitionModel = studentservice.entityToModel(tranentity);
			transitionModels.add(transitionModel);
		}
		if (transitionModels != null && !transitionModels.isEmpty()) {
			model.addAttribute("registerStudents", transitionModels);
		} else {
			model.addAttribute("msg", "Data not found");
		}

		return "stuaccdisplay";
	}

	@GetMapping("/students")
	public String displayStudents(HttpSession session, Model model) {
		/*
		 * LoginModel loggedInData = (LoginModel) session.getAttribute("loggedInData");
		 * if (loggedInData == null && loggedInData.getRole().equalsIgnoreCase("admin"))
		 * { model.addAttribute("error", "Please Login Again!"); return "login"; }
		 * model.addAttribute("loggedInData",loggedInData);
		 */
		List<TransationEntityModel> transitions = studentservice.getTransitions();
		List<TransitionModel> transitionModels = new ArrayList<TransitionModel>();
		for (TransationEntityModel tranentity : transitions) {
			TransitionModel transitionModel = studentservice.entityToModel(tranentity);
			transitionModels.add(transitionModel);
		}
		if (transitionModels != null && !transitionModels.isEmpty()) {
			model.addAttribute("registerStudents", transitionModels);
		} else {
			model.addAttribute("msg", "Data not found");
		}
		return "displaystudent";
	}

	@RequestMapping(value = "/timeLine", method = RequestMethod.GET)
	public ModelAndView timeLineForm(HttpSession session, Model model) {
		LoginModel loggedInData = (LoginModel) session.getAttribute("loggedInData");
		if (loggedInData == null) {
			model.addAttribute("error", "Please Login Again!");
			return new ModelAndView("login", "stupost", new StuActivitiesModel());
		}
		model.addAttribute("loggedInData", loggedInData);
		List<StuActivitiesModel> postDTOList = studentservice.selectAllTimelineActivitiesPost(loggedInData.getId());
		for (StuActivitiesModel post : postDTOList) {
			int totalLikes = studentservice.getTotalLikesForPost(post.getId());
			post.setTotalLikes(totalLikes);
		}
		if (postDTOList != null && !postDTOList.isEmpty()) {
			model.addAttribute("postDTOList", postDTOList);
		} else {
			model.addAttribute("message", "No posts found.");
		}

		return new ModelAndView("timeline", "stupost", new StuActivitiesModel());
	}

	// Handle GET request to display the form for adding a post
	@RequestMapping(value = "/createActivities", method = RequestMethod.GET)
	public ModelAndView addPostForm(HttpSession session, Model model) {
		LoginModel loggedInData = (LoginModel) session.getAttribute("loggedInData");

		model.addAttribute("loggedInData", loggedInData);

		
		
		List<StuActivitiesModel> postDTOList = studentservice.selectAllActivitiesPost();
		for (StuActivitiesModel post : postDTOList) {
			int totalLikes = studentservice.getTotalLikesForPost(post.getId());
			post.setTotalLikes(totalLikes);
		}
		if (postDTOList != null && !postDTOList.isEmpty()) {
			model.addAttribute("postDTOList", postDTOList);
		} else {
			model.addAttribute("message", "No posts found.");
		}

		return new ModelAndView("activitycreate", "stupost", new StuActivitiesModel());
	}

	@RequestMapping(value = "/createActivities", method = RequestMethod.POST)
	public String addPost(@ModelAttribute("stupost") StuActivitiesModel post, BindingResult result, ModelMap model,
			HttpSession session) throws IOException {
		// Retrieve userId from session
		LoginModel loggedInData = (LoginModel) session.getAttribute("loggedInData");
		if (loggedInData == null) {
			model.addAttribute("error", "Please Login Again!");
			return "login";
		}
		model.addAttribute("loggedInData", loggedInData);

		List<StuActivitiesModel> postDTOList = studentservice.selectAllActivitiesPost();
		if (postDTOList != null && !postDTOList.isEmpty()) {
			model.addAttribute("postDTOList", postDTOList);
		} else {
			model.addAttribute("message", "No posts found.");
		}

		// Check if required fields are filled
		if (post.getTitle() == null || post.getTitle().isEmpty() || post.getDetail() == null
				|| post.getDetail().isEmpty()) {
			model.addAttribute("error", "Please fill in all the required fields.");
			return "activitycreate";
		}

		StuActivitiesEntityModel stumodel = new StuActivitiesEntityModel();
		stumodel.setTitle(post.getTitle());
		stumodel.setDetail(post.getDetail());
		stumodel.setPhoto(post.getPhoto().getBytes());
		stumodel.setDate(DateTime());
		stumodel.setIsdelete(false);
		if (loggedInData.getRole().equals("User")) {
			stumodel.setStudent(null);
		} else {
			StudentEntityModel entity = new StudentEntityModel();
			entity.setId(loggedInData.getId());
			stumodel.setStudent(entity);
		}
		// Add the post using the service
		int i = studentservice.insertActivitiesPost(stumodel);
		if (i == 0) {
			System.out.println("error");
			model.addAttribute("error", "Failed to add post!");
			return "activitycreate"; // Return to the add post form with an error message
		}
		return "redirect:/createActivities"; // Redirect to the home page after adding the post
	}

	@RequestMapping(value = "student_profile", method = RequestMethod.GET)
	public String userProfile(ModelMap model, HttpSession session) {
		LoginModel loggedInData = (LoginModel) session.getAttribute("loggedInData");
		if (loggedInData == null) {
			model.addAttribute("error", "Please Login Again!");
			return "login";
		}
		model.addAttribute("loggedInData", loggedInData);

		StudentEntityModel umodel = studentservice.GetStudentById(loggedInData.getId());
		if (umodel != null) {
			String photoBase64 = Base64.getEncoder().encodeToString(umodel.getPhoto());
			model.addAttribute("photoBase64", photoBase64);
			model.addAttribute("studata", umodel); // Add photoBase64 attribute to model
			List<CourseEntityModel> course = courseService.getCourseByStudentId(loggedInData.getId());
			model.addAttribute("stucourse", course);
			model.addAttribute("stuid",loggedInData.getId());
			return "stuprofile"; // Assuming userProfile.jsp is your view page
		}
		return "login";
	}
	@GetMapping("/updateprofile")
    public ModelAndView profileUpdate(@RequestParam("stuid") int id,ModelMap model) {
      StudentEntityModel umodel = studentservice.GetStudentById(id);
      String photoBase64 = Base64.getEncoder().encodeToString(umodel.getPhoto());
      model.addAttribute("photoBase64", photoBase64);
        model.addAttribute("studata", umodel); 
        model.addAttribute("stuid",id);
       return new ModelAndView("studentprofileupdate","studentinform",new StudentModel());
    }
    @PostMapping("/stuprofileupdate")
    public String updateStudentProfile(@ModelAttribute("studentinform")StudentModel studentdata,Model model) throws IOException {
    //  byte[] studephoto=studentdata.getPhoto().getBytes();
      studentdata.setPhoto(studentdata.getPhoto());
      int restult=studentservice.updateProfileStudent(studentdata);
        return "redirect:/student_profile";       
    }
	// StudentAccessWithEMailReply
    @PostMapping("/replystudent")
    public String replyStudent(HttpServletRequest request,ModelMap model,@RequestParam("studentid") int stuid,@RequestParam("description")String description,
        @RequestParam("email")String email,@RequestParam("status")String status,@RequestParam("courseid") int cosid) {
    if(status.equals("Accept")) {
      String link="http://localhost:8080/studentregistrationspringjpa/login";
        sendEmail(email,description,link);
        int result=studentservice.ConfirmStudent(stuid);
        
        List<TransationEntityModel> transitions = studentservice.getRegisterTransitions();
        List<TransitionModel> transitionModels=new ArrayList<TransitionModel>();
        for (TransationEntityModel tranentity : transitions) {
          TransitionModel transitionModel = studentservice.entityToModel(tranentity);
          transitionModels.add(transitionModel);
        }
        model.addAttribute("registerStudents",transitionModels);
           return "redirect:/regstudents";
        }else if(status.equals("Reject")) {
          String link = "http://localhost:8080/studentregistrationspringjpa/misstatus?cd=" + cosid + "&sd=" + stuid;
            sendEmail(email,description,link);
           
            List<TransationEntityModel> transitions = studentservice.getTransitions();
            List<TransitionModel> transitionModels=new ArrayList<TransitionModel>();
            for (TransationEntityModel tranentity : transitions) {
              TransitionModel transitionModel = studentservice.entityToModel(tranentity);
              transitionModels.add(transitionModel);
            }
            model.addAttribute("registerStudents",transitionModels);
            return "redirect:/regstudents";
        } 
    return "regstudents";
    }
    
	@GetMapping("/misstatus")
    public String rejectStudent(HttpServletRequest request, HttpSession session, Model model,@ModelAttribute("error") String data,@RequestParam("cd") int id,@RequestParam("sd") int sid) {
        CourseEntityModel course=courseService.getCourseById(id);
        model.addAttribute("coursedata",course);
        model.addAttribute("courseid",id);
        model.addAttribute("stuid",sid);
       return "missingtransition";
    }
  //wrong transistion
    @PostMapping("/wrogtransition")
    public String wrongTransition(@ModelAttribute("studentinform")StudentModel studentdata,
        @RequestParam("studentid") int id,HttpSession session,Model model) throws IOException {
        studentservice.updateTransation(id, studentdata.getPayphoto().getBytes());
        return "redirect:/";       
    }
	// Method to send OTP via email
	private void sendEmail(String stuEmail, String description, String link) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(stuEmail);
		message.setSubject("Ace Inspiration Student Registraion Reply ");
		message.setText(description + "\n\n" + link);
		mailSender.send(message);
	}

	// givelikepost
	@RequestMapping(value = "/like/{postId}", method = RequestMethod.POST)
	public String giveLike(@PathVariable("postId") int postId, HttpSession session, ModelMap model) {

		LoginModel loggedInData = (LoginModel) session.getAttribute("loggedInData");
		if (loggedInData == null) {
			model.addAttribute("error", "Please Login Again!");
			return "login";
		}
		model.addAttribute("loggedInData", loggedInData);
		int studentId = loggedInData.getId();
		boolean alreadyLiked = studentservice.hasStudentLikedPost(postId, studentId);
		if (alreadyLiked) {
			model.addAttribute("error", "You have already liked this post!");
			return "redirect:/createActivities";
		}
		int result = studentservice.giveLike(postId, studentId);
		if (result == 1) {
			return "redirect:/createActivities";
		} else {

			model.addAttribute("error", "Failed to give like!");
			return "redirect:/createActivities";
		}
	}

	// likedpostdisplay
	@RequestMapping(value = "/likedposts", method = RequestMethod.GET)
	public String displayLikedPosts(HttpSession session, ModelMap model) {
		LoginModel loggedInData = (LoginModel) session.getAttribute("loggedInData");
		if (loggedInData == null) {
			model.addAttribute("error", "Please Login Again!");
			return "login";
		}
		model.addAttribute("loggedInData", loggedInData);
		int studentId = loggedInData.getId();
		List<StuActivitiesModel> likedPosts = studentservice.getLikedPostsByStudentId(studentId);
		System.out.println(likedPosts);
		for (StuActivitiesModel post : likedPosts) {
			int totalLikes = studentservice.getTotalLikesForPost(post.getId());
			post.setTotalLikes(totalLikes);
		}
		model.addAttribute("likedPosts", likedPosts);

		return "likedposts";
	}

	// delete student
	@RequestMapping(value = "/deletestudent/{id}", method = { RequestMethod.GET, RequestMethod.POST })
	public String deleteStudent(@PathVariable int id, ModelMap model) {
		System.out.println("id");
		int rowsAffected = studentservice.softDelete(id);
		if (rowsAffected == 0) {
			model.addAttribute("error", "Delete Fail!!");
		}
		return "redirect:/students";
	}
	
	 @GetMapping("/postsoftDelete/{postId}")
	    public String softDeletePost(@PathVariable("postId") int postId, HttpSession session, ModelMap model) {
	        // Retrieve student ID from session
		 LoginModel loggedInData = (LoginModel) session.getAttribute("loggedInData");
			if (loggedInData == null) {
				model.addAttribute("error", "Please Login Again!");
				return "login";
			}
			model.addAttribute("loggedInData", loggedInData);

	        // Soft delete the post
	        int result = studentservice.softDeletePost(postId);
	        if (result == 1) {
	            return "redirect:/timeLine"; // Redirect to the display student post page after successful soft delete
	        } else {
	            model.addAttribute("error", "Failed to soft delete post!");
	            return "redirect:/timeLine"; 
	        }
	    }
	 
	// Method to display deleted student posts
    @GetMapping("/displaydeletedposts")
    public String displayDeletedPosts(ModelMap model, HttpSession session) {
        // Retrieve logged-in user from session
    	LoginModel loggedInData = (LoginModel) session.getAttribute("loggedInData");
		if (loggedInData == null) {
			model.addAttribute("error", "Please Login Again!");
			return "login";
		}
		model.addAttribute("loggedInData", loggedInData);
        // Retrieve deleted posts of the logged-in user by calling the service method
        List<StuActivitiesModel> deletedPosts = studentservice.getDeletedPostsByStudentId(loggedInData.getId());

        // Add the deleted posts to the model
        model.addAttribute("postDTOList", deletedPosts);

        return "trashtimeline"; // Create a new JSP file to display deleted posts
    }
    @GetMapping("/recover/{postId}")
    public String recoverDeletedPost(@PathVariable("postId") int postId, HttpSession session, ModelMap model) {
        // Retrieve student ID from session
    	LoginModel loggedInData = (LoginModel) session.getAttribute("loggedInData");
		if (loggedInData == null) {
			model.addAttribute("error", "Please Login Again!");
			return "login";
		}
		model.addAttribute("loggedInData", loggedInData);
        // Recover the deleted post
        int result = studentservice.recoverDeletedPost(postId);
        if (result == 1) {
            return "redirect:/displaydeletedposts"; // Redirect to the display student post page after successful recovery
        } else {
            model.addAttribute("error", "Failed to recover deleted post!");
            return "redirect:/displaydeletedposts"; // Redirect to the display student post page with an error message
        }
    }
    
 // Handle GET request to display the form for updating a post
    @RequestMapping(value = "setupactivityupdatepost/{postId}", method = RequestMethod.GET)
    public String setupUpdatePost(@PathVariable("postId") int postId, Model model) {
        // Retrieve the post by its ID
        StuActivitiesModel post = studentservice.selectById(postId);
        
        // Check if the post exists
        if (post == null) {
            model.addAttribute("error", "Post not found!");
            return "redirect:/displaystudentpost"; // Redirect to the display page
        }
        
        // Add the post to the model
        model.addAttribute("post", post);
        
        // Return the view for updating the post
        return "updatepostform"; // Create a new JSP file for update post form
    }
    @RequestMapping(value = "/updatestudentpost", method = RequestMethod.POST)
    public String updateStudentPost(@ModelAttribute("post") StuActivitiesModel post,
                                    @RequestParam(value = "photoUpload", required = false) MultipartFile photoUpload,
                                    ModelMap model) throws IOException {
        // Check if any required fields are blank
        if (post.getTitle() == null || post.getTitle().isEmpty() ||
            post.getDetail() == null || post.getDetail().isEmpty()) {
            model.addAttribute("error", "Please fill in all the required fields.");
            return "updatepostform"; // Return to the update student post form if there are validation errors
        }

        // Process the photo upload
        if (photoUpload != null && !photoUpload.isEmpty()) {
            try {
                // Convert the uploaded file to byte array
                byte[] photoData = photoUpload.getBytes();
                post.setPhotoData(photoData);
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("error", "Error processing file: " + e.getMessage());
                return "updatepostform"; // Return to the update student post form with an error message
            }
        } else {
            // If no new photo is uploaded, no action is required
            // You may add other logic here if needed
        }

        // Create StuActivitiesEntityModel from StuActivitiesModel
        StuActivitiesEntityModel postEntity = new StuActivitiesEntityModel();
        postEntity.setId(post.getId());
        postEntity.setTitle(post.getTitle());
        postEntity.setDetail(post.getDetail());
        postEntity.setDate(post.getDate());
        postEntity.setIsdelete(post.getIsDelete());

        // You need to set the student here if it's not already set in the StuActivitiesModel

        // Set photo if uploaded
        if (post.getPhoto() != null) {
            postEntity.setPhoto(post.getPhoto().getBytes());
        }

        // Perform the update operation
        int i = studentservice.updatePost(postEntity);
        if (i == 0) {
            model.addAttribute("error", "Update Post Fail!!");
            return "updatepostform"; // Return to the update student post form with an error message
        }

        return "redirect:/timeLine"; // Redirect to the display student post page after successful update
    }
    
    @RequestMapping(value = "/comment/{postId}", method = RequestMethod.POST)
    public String giveComment(@PathVariable("postId") int postId, @RequestParam("commentText") String commentText, HttpSession session, ModelMap model) {
      LoginModel loggedInData = (LoginModel) session.getAttribute("loggedInData");
    if (loggedInData == null) {
      model.addAttribute("error", "Please Login Again!");
      return "login";
    }
    model.addAttribute("loggedInData", loggedInData);
    int studentId = loggedInData.getId();
        
        int result = studentservice.giveComment(postId, studentId, commentText);
        if (result == 1) {
            return "redirect:/createActivities";
        } else {
            model.addAttribute("error", "Failed to give comment!");
            return "redirect:/createActivities";
        }
    }
}
