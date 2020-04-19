package com.nitesh.springdemo.mvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorldController {
	@RequestMapping("/showForm")
	public String showForm() {
		return "helloworld-form";
	}
	
	@RequestMapping("/processForm")
	public String processForm() {
		return "helloworld";
	}
	
	@RequestMapping("/processFormV2")
	public String letsShouldDude(HttpServletRequest request, Model model) {
		String theName= request.getParameter("studentName");
		
		theName= theName.toUpperCase();
		
		String result= "Yo! "+ theName;
		
		model.addAttribute("message", result);
		
		return "helloworld";
	}
}
