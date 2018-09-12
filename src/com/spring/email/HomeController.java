package com.spring.email;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import antlr.collections.List;

@Controller
public class HomeController {
	
	public String emailAdd = "";

	@RequestMapping("/")
	public String ShowHomePage() {
		return "main-page";
	}
	@RequestMapping("/signup")
	public String showSignUpForm(HttpServletRequest req , Model model ){
		return "sign-up";
	}
	
	@RequestMapping("/login")
	public String showLoginForm(HttpServletRequest req, Model model) {	
		return "login";
	}
	@RequestMapping("/homepageSignUp")
	public String showHomePageSignUp(HttpServletRequest req, Model model) {
		// retrieves data from form & Connects @ symbol to email
		String emailAddress = req.getParameter("email") + "@" + req.getParameter("domainName");
		String password = req.getParameter("psw");
		//creates new row in DB
		EmailSignUpProcessing initialEmailUser = new EmailSignUpProcessing();
		boolean validSignup = initialEmailUser.createNewEmailUser(emailAddress, password);
		//tempCode
		emailAdd = emailAddress; 
		model.addAttribute("emailAddress", emailAddress);
		model.addAttribute("password", password);
		return "showlogininfo";
		
	}
	
	@RequestMapping("/homepage")
	public String showHomePage(HttpServletRequest req, Model model) {
		// retrieves data from form & Connects @ symbol to email
		String emailAddress = req.getParameter("email");
		String password = req.getParameter("psw");
		model.addAttribute("emailAddress", emailAddress);
		//creates new row in DB
		LoginProcessing loginUser = new LoginProcessing();
		loginUser.emailUniqueValidation(emailAddress, password);
		boolean loginValid = loginUser.isLoginValid();
		
		if(loginValid == true) {
			emailAdd = req.getParameter("email"); 
			InboxProcess inbx = new InboxProcess();
			java.util.List<EmailEntity> messages = inbx.messagesForUsers(emailAddress);
			
			model.addAttribute("messages", messages);
			return "showlogininfo";
		}
		else {
			// need to redirect back to login page
			return "invalid-login";
		}
	}
	
	@RequestMapping("/sendmail")
	public String showSendForm(HttpServletRequest req, Model model) {
		model.addAttribute("loggedInUser", emailAdd);
		return "sendmail";
	}
	
	@RequestMapping("/sendmailsuccess")
	public String showSendReceipt(HttpServletRequest req, Model model) {
		String subject = req.getParameter("subject");
		String to = req.getParameter("to");
		String message = req.getParameter("message");
		
		SendEmailProcessing newEmail = new SendEmailProcessing();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		String timeSent =  LocalDateTime.now().toString();
		newEmail.createNewEmailUser(subject, emailAdd, to, message, timeSent);
		return "message-sent";
	}
	
	   
	   
	 
	
}
