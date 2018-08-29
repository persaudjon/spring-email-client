package com.spring.email;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String ShowHomePage() {
		return "main-page";
	}
	@RequestMapping("/signuppage")
	public String showLoginInfo(HttpServletRequest req , Model model ){
		return "sign-up";
	}
	@RequestMapping("/homepage")
	public String showHomePage(HttpServletRequest req, Model model) {
		String emailAddress = req.getParameter("email") + "@" + req.getParameter("domainName");
		String password = req.getParameter("psw");
		model.addAttribute("emailAddress", emailAddress);
		model.addAttribute("password", password);
		return "showlogininfo";
	}
}
