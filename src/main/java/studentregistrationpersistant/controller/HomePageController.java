package studentregistrationpersistant.controller;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import studentregistrationpersistant.model.LoginModel;
import studentregistrationpersistant.model.UserPostModel;
import studentregistrationpersistant.service.UserService;
@Controller
public class HomePageController {
	@Autowired
	private UserService service;
	
	@RequestMapping("/")
	public String homePage(ModelMap model, HttpSession session) {
	    @SuppressWarnings("unused")
		LoginModel loggedInData = (LoginModel) session.getAttribute("loggedInData");
	    
	    List<UserPostModel> postDTOList = service.selectAllPost();
        if (postDTOList != null && !postDTOList.isEmpty()) {
            model.addAttribute("postDTOList", postDTOList);
        } 
	    return "home";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	private String logOut(HttpSession session) {
		@SuppressWarnings("unused")
		LoginModel loggedInData = (LoginModel) session.getAttribute("loggedInData");
		session.invalidate();
		return "redirect:/";
	}

}
