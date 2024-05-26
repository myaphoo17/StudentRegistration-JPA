package studentregistrationpersistant.controller;
import java.sql.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

    @RequestMapping("/adminhome")
    public String adminhomePage() {
        return "adminHome";
    }

    @RequestMapping("/admindashboard")
    public String showadmindash(Model model) throws ClassNotFoundException {
    	try {
    		 Class.forName("com.mysql.cj.jdbc.Driver");
    		    String url = "jdbc:mysql://localhost:3306/ojtstudentjpadb";
    		    String username = "root";
    		    String password ="myaphoothwe";
    		    Connection connection = DriverManager.getConnection(url, username, password);
    	    
    	    CallableStatement totalCoursesProcedure = connection.prepareCall("{call ojtstudentjpadb.CountCourses()}");
    	    ResultSet totalCoursesResult = totalCoursesProcedure.executeQuery();
    	    if (totalCoursesResult.next()) {
    	        int totalCourses = totalCoursesResult.getInt(1);
    	        model.addAttribute("totalCourses", totalCourses); 
    	    }
    	    totalCoursesResult.close(); // Close ResultSet

    	 
			
			  CallableStatement totalStudentsProcedure =
			  connection.prepareCall("{call ojtstudentjpadb.CountStudents()}"); ResultSet
			  totalStudentsResult = totalStudentsProcedure.executeQuery(); if
			  (totalStudentsResult.next()) { int totalStudents =
			  totalStudentsResult.getInt(1); model.addAttribute("totalStudents",
			  totalStudents);  } totalStudentsResult.close();
		
			 
    	    CallableStatement totalStudentManagersProcedure = connection.prepareCall("{call ojtstudentjpadb.CountUsers()}");
    	    ResultSet totalStudentManagersResult = totalStudentManagersProcedure.executeQuery();
    	    if (totalStudentManagersResult.next()) {
    	        int totalStudentManagers = totalStudentManagersResult.getInt(1);
    	        model.addAttribute("totalStudentManagers", totalStudentManagers); // Add totalStudentManagers to model
    	    }
    	    totalStudentManagersResult.close(); 


			 CallableStatement totalEarningsProcedure =
			  connection.prepareCall("{call ojtstudentjpadb.TotalCourseFees()}"); ResultSet
			  totalEarningsResult = totalEarningsProcedure.executeQuery(); if
			  (totalEarningsResult.next()) { double totalEarnings =
			  totalEarningsResult.getDouble(1); model.addAttribute("totalEarnings",
			  totalEarnings);  } totalEarningsResult.close();
			 


    	   
    	    connection.close();
    	} catch (SQLException e) {
    	    e.printStackTrace();
    	}

        return "adminDashboard";
    }
}
