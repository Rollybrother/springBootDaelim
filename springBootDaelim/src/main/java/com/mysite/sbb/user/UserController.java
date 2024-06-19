package com.mysite.sbb.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@ComponentScan
@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/login")
    public String login() {
        return "login_form";
    }
	
	 @PostMapping("/loginProcess")
	    public String loginCheck(@RequestParam String userid, @RequestParam String password, ModelMap model, HttpSession session) throws Exception {
	        LoginVO loginVO = new LoginVO();
	        loginVO.setUserid(userid);
	        loginVO.setPassword(password);

	        String result = "";

	        if (userService.loginExecute(loginVO)) {
	            session.setAttribute("user", loginVO.getUserid());
	            session.setAttribute("password", loginVO.getPassword());
	            result = "redirect:/daelim/list";
	        } else {
	            model.addAttribute("error", true);
	            result = "login_form";
	        }
	        return result;
	    }
}
