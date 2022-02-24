package com.minh.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.minh.project.model.Login;

@Controller
public class IndexController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getLoginPage() {
		return "index";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		return "index";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String submit(Model model, @ModelAttribute("login") Login login) {
		if (login != null && (login.getUsername() != null && !login.getUsername().equals("")) && (login.getPassword() != null && !login.getPassword().equals(""))) {
			model.addAttribute("user", login.getUsername().toUpperCase());
			return "home";
		} else {
			model.addAttribute("error", "Please enter username and password");
			return "index";
		}
	}
}


