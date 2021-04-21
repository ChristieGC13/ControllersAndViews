package com.cc.loginreg.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cc.loginreg.models.User;
import com.cc.loginreg.services.UserService;
import com.cc.loginreg.validators.UserValidator;

@Controller
public class MainController {
	
	private final UserService userServ;
	private final UserValidator userValidator;
	public MainController(UserService userServ, UserValidator userValidator) {
		this.userServ = userServ;
		this.userValidator = userValidator;
	}
	
//***************************************
//	Register User
//***************************************

	@GetMapping("/registration")
	public String registerPage(Model model) {
		model.addAttribute("user", new User());
		return "register.jsp";
	}
	
	@PostMapping("/registration")
	public String register(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session, RedirectAttributes redirect) {
		userValidator.validate(user, result);
		if (result.hasErrors()) {
			return "register.jsp";
		} else {
			User u = userServ.registerUser(user);
			//put user in session
			session.setAttribute("userId", u.getId());
			redirect.addFlashAttribute("success","You're officially registered!");
			return "redirect:/dashboard";
		}
	}
	
//***************************************
//	Login User
//***************************************
	
	@GetMapping("/login")
	public String loginPage() {
		return "login.jsp";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam(value="email") String email, @RequestParam(value="password") String password, HttpSession session, RedirectAttributes redirect ) {
        if (userServ.authenticateUser(email, password)) {
        		User u = userServ.findByEmail(email);
        		session.setAttribute("userId", u.getId());
        		return "redirect:/dashboard";
        } else {
        		redirect.addFlashAttribute("error","Invalid Login");
        		return "redirect:/login";
        }
	}
	
//***************************************
//	Dashboard
//***************************************
	
	@GetMapping("/dashboard")
	public String dashboard(HttpSession session, Model model, RedirectAttributes redirect) {	
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			redirect.addFlashAttribute("force","Please login first. ");
			return "redirect:/login";
		}
		model.addAttribute("user", userServ.findUserById(userId));
		return "dashboard.jsp";
	}
	
	
//***************************************
//	Logout
//***************************************
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}
}
