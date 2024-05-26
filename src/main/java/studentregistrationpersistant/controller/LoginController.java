package studentregistrationpersistant.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import studentregistrationpersistant.entitymodel.UserEntityModel;

import studentregistrationpersistant.entitymodel.StudentEntityModel;
import studentregistrationpersistant.model.LoginModel;
import studentregistrationpersistant.service.LoginService;
@Controller
public class LoginController {
	
	@Autowired
	private LoginService service;
	
	@RequestMapping("/login")
	public ModelAndView loginPage() {
		return new ModelAndView("login","user",new LoginModel());
	}
	
	@PostMapping(value = "/login")
	public String login(@ModelAttribute("user") LoginModel user, HttpSession session, BindingResult bResult,
	                    ModelMap model) {
	    if (bResult.hasErrors()) {
	        return "redirect:/login";
	    }

	    // Check if the user exists in either the user or student database
	    UserEntityModel loggedInUser = service.getUserByEmail(user.getEmail());
	    StudentEntityModel loggedInStudent = service.getStudentByEmail(user.getEmail());
	 
	    if (loggedInUser == null && loggedInStudent == null) {
	        model.addAttribute("error", "Account not found!");
	        model.addAttribute("user",user);
	        return "login";
	    }

	    // Check if the user or student has been banned
	    if (loggedInUser != null && loggedInUser.isActive()==true) {
	        model.addAttribute("error", "Account has been banned!");
	        
	        return "login";
	    }
	    if(loggedInStudent != null) {
	    if (loggedInStudent.getIsaccept()==false) {
	    	System.out.println("data1");
	        model.addAttribute("error", "You are not a student!");
	        return "login";
	    }
	    if (loggedInStudent.getIsdelete()==true) {
	    	System.out.println("data2");
            model.addAttribute("error", "Account has been deleted!");
            return "login";
        }
	    }
	    // Check if the provided password matches
	    if (loggedInUser != null && user.getPassword().equals(loggedInUser.getPassword())) {
	        LoginModel logModel = service.userentityToModel(loggedInUser);
	        session.setAttribute("loggedInData", logModel);
	        if ("Admin".equalsIgnoreCase(loggedInUser.getRole())) {
	            return "redirect:/adminhome";
	        }
	        return "redirect:/"; // Redirect to user home page
	    }

	    if (loggedInStudent != null && user.getPassword().equals(loggedInStudent.getPassword())) {
	        LoginModel logModel = service.studententityToModel(loggedInStudent);
	        session.setAttribute("loggedInData", logModel);
	        return "redirect:/"; // Redirect to user home page
	    }

	    // If password doesn't match for either user or student
	    model.addAttribute("error", "Password doesn't match!");
	    model.addAttribute("user",user);
	    return "login";
	}


}
