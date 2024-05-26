package studentregistrationpersistant.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import studentregistrationpersistant.entitymodel.CourseEntityModel;
import studentregistrationpersistant.service.CourseService;
@Controller

public class CourseCardController {
	@Autowired
	private CourseService service;
	@GetMapping("coursecard")
	public String  courseCard(HttpServletRequest request,Model model,@ModelAttribute("error") String data) {
		List<CourseEntityModel> courses=service.selectAllCouree();
		 request.setAttribute("allcourses", courses);
		 return "coursecard";
	}
	
	 @RequestMapping(value = "/courses", method = RequestMethod.GET)
	    public String displayView(ModelMap model) {
	        List<CourseEntityModel> list = service.selectAllCouree();
	        if (list.isEmpty()) {
	            model.addAttribute("msg", "Data not found");
	        } else {
	            model.addAttribute("list", list);
	        }

	        return "displaycourse";
	    }

	    @RequestMapping(value = "/setupaddcourse", method = RequestMethod.GET)
	    public ModelAndView setUpAddCourse() {
	        return new ModelAndView("addCourse", "bean", new CourseEntityModel());
	    }

	    @RequestMapping(value = "/addcourse", method = RequestMethod.POST)
	    public String addCourse(@ModelAttribute("bean") @Validated CourseEntityModel bean, BindingResult result, ModelMap model) {
	        if (result.hasErrors()) {
	            return "addCourse";
	        } else {
	            int rowsAffected = service.insertData(bean);
	            if (rowsAffected == 0) {
	                model.addAttribute("error", "Course is not added.");
	                return "addCourse";
	            }
	        }
	        return "redirect:/courses";
	    }

	    @RequestMapping(value = "/setupcourseupdate/{id}", method = RequestMethod.GET)
	    public ModelAndView setUpUpdate(@PathVariable int id) {
	        return new ModelAndView("update_course", "bean", service.selectOne(id));
	    }

	    @RequestMapping(value = "/updatecourse", method = RequestMethod.POST)
	    public String updateCourse(@ModelAttribute("bean") @Validated CourseEntityModel bean, BindingResult result, ModelMap model) {
	        if (result.hasErrors()) {
	            return "update_course";
	        } else {
	            int rowsAffected = service.updateData(bean);
	            if (rowsAffected == 0) {
	                model.addAttribute("error", "Update Fail!!");
	                return "update_course";
	            }
	        }
	        return "redirect:/courses";
	    }
	    
	    @RequestMapping(value = "/deletecourse/{id}", method = {RequestMethod.GET, RequestMethod.POST})
	    public String deleteCourse(@PathVariable int id, ModelMap model) {
	        int rowsAffected = service.softDelete(id);
	        if (rowsAffected == 0) {
	            model.addAttribute("error", "Delete Fail!!");
	        }
	        return "redirect:/courses";
	    }
}
