package studentregistrationpersistant.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import studentregistrationpersistant.service.StudentService;
import java.util.Timer;
import java.util.TimerTask;

@Controller
public class ForgetPasswordController {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private StudentService studentservice;
    private String generateOTP() {
        // Generate a random 6-digit OTP
        int otp = (int) (Math.random() * 900000) + 100000;
        return String.valueOf(otp);
    }
    private class InvalidateSessionTask extends TimerTask {
        private HttpSession session;

        public InvalidateSessionTask(HttpSession session) {
            this.session = session;
        }
        @Override
        public void run() {
            session.invalidate();
        }
    }
    // Method to send OTP via email
    private void sendOTPEmail(String userEmail, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(userEmail);
        message.setSubject("Forget Password");
        message.setText("Don't Share This OTP Code Another One !  " + otp);
        mailSender.send(message);
    }
    @GetMapping("/forgetpassword")
    public String ForgetPassword(ModelMap model, HttpServletRequest request) {
        int num = 1;
        model.addAttribute("num", num);
        return "resetpassword";
    }
    @PostMapping("/forgetpassword")
    public String ForgetPassword(@RequestParam("email") String email, ModelMap model, HttpServletRequest request) {
        boolean condition = studentservice.checkingEmailHaveDataBase(email);
        if (!condition) {
            int num = 1;
            model.addAttribute("num", num);
            model.addAttribute("error", "E-Mail Not Found!");
            return "resetpassword";
        } else {
            HttpSession session = request.getSession();
            String otp = generateOTP(); 
            session.setAttribute("otpcode", otp);
            sendOTPEmail(email, otp);
            // Set session timeout to 5 minutes (in seconds)
            session.setMaxInactiveInterval(300);
            Timer timer = new Timer();
            timer.schedule(new InvalidateSessionTask(session), 300000);
            model.addAttribute("emailvalue", email);
            model.addAttribute("alert", "OTP Was Sent Your E-Mail Address Fill OTP Code!");
            return "resetpassword";
        }
    }
    @PostMapping("/updatepassword")
    public String updatePassword(@RequestParam("email") String email, ModelMap model, HttpServletRequest request,@RequestParam("otpcode") String code) {
    	HttpSession session = request.getSession(false);
		if(session != null && session.getAttribute("otpcode") != null) {
			String sessionotpstr = (String) session.getAttribute("otpcode");
			int sessionotp=Integer.parseInt(sessionotpstr);
			int receiveotp=Integer.parseInt(code);
			if(sessionotp == receiveotp) {
				int num = 2;
	            model.addAttribute("num", num);
	            model.addAttribute("email",email);
	            return "resetpassword";
			}
			if(sessionotp != receiveotp) {
		     
		              model.addAttribute("email",email);
		              model.addAttribute("otpfail","Your OTP code Is Wrong !");
		              return "resetpassword";
		      }
		}
    	return "resetpassword";
    }
    @PostMapping("/confirmupdatepassword")
    public String confirmUpdatePassword(@RequestParam("password") String password,@RequestParam("email") String email, ModelMap model, HttpServletRequest request) {
    	int reslut=studentservice.confirmUpdatePassword(email, password);
    	request.setAttribute("result", reslut);
   // 	model.addAttribute("successful","Your Are Successfully Update Password");
    	return "resetpassword";
    }
}
