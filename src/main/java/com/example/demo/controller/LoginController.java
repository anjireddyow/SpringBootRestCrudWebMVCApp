package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.User;

/**
 * 
 * 
 * @author 
 *
 */
@Controller
@SessionAttributes(value = { "user" }, types = { User.class })
// @SessionAttributes(value = {"bean1", "bean2" }, types = {Bean1.class,
// Bean2.class})
public class LoginController {

	/**
	 * SessionAttributes will work with ModelAttribute
	 * 
	 * @return
	 */
	@ModelAttribute("user")
	public User getUserDetails() {
		return new User();
	}
	
	/**
	 * Model: It is an Interface. It defines a holder for model attributes and
	 * primarily designed for adding attributes to the model.
	 * 
	 * @return default spring boot login page
	 */
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String welcome(Model model) {
		model.addAttribute("message", "Welcome to Spring Boot Rest CRUD MVC App");
		return "welcome";
	}

	/**
	 * ModelAndView is just a container for both a ModelMap and a view object. It
	 * allows a controller to return both as a single value.
	 * 
	 * @return home page
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView homePage(HttpServletRequest httpServletRequest, @ModelAttribute("user")User user) {
		String sessionValue = "";
		if(httpServletRequest.getSession(false) != null) {
			sessionValue = "session available already :"+httpServletRequest.getSession(false).getId();
		}else {
			sessionValue = "Session is null";
		}
		String appContextPath = httpServletRequest.getContextPath();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home");
		// Get authenticated user name from SecurityContext
		SecurityContext context = SecurityContextHolder.getContext();

		modelAndView.addObject("message", sessionValue + "You are logged in as " + context.getAuthentication().getName());
		return modelAndView;
	}
	
	/**
	 * ModelAndView is just a container for both a ModelMap and a view object. It
	 * allows a controller to return both as a single value.
	 * 
	 * @return home page
	 */
	@RequestMapping(value = "/homeadmin", method = RequestMethod.GET)
	public ModelAndView homeadmin(HttpServletRequest httpServletRequest, @ModelAttribute("user")User user) {
		String sessionValue = "";
		if(httpServletRequest.getSession(false) != null) {
			sessionValue = "session available already :"+httpServletRequest.getSession(false).getId();
		}else {
			sessionValue = "Session is null";
		}
		String appContextPath = httpServletRequest.getContextPath();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("homeadmin");
		// Get authenticated user name from SecurityContext
		SecurityContext context = SecurityContextHolder.getContext();

		modelAndView.addObject("message", sessionValue + "You are logged in as " + context.getAuthentication().getName());
		return modelAndView;
	}

	/**
	 * ModelMap: Implementation of Map for use when building model data for use with
	 * UI tools.Supports chained calls and generation of model attribute names.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(ModelMap modelMap) {
		String helloWorldMessage = "Hello world!";
		String welcomeMessage = "Welcome!";
		modelMap.addAttribute("helloMessage", helloWorldMessage);
		modelMap.addAttribute("welcomeMessage", welcomeMessage);
		return "login";
	}
	
	@RequestMapping(value = "/displayEmployees", method = RequestMethod.GET)
	public ModelAndView displayEmployees(HttpServletRequest httpServletRequest, @ModelAttribute("user")User user) {
		String sessionValue = "";
		if(httpServletRequest.getSession(false) != null) {
			sessionValue = "session available already :"+httpServletRequest.getSession(false).getId();
		}else {
			sessionValue = "Session is null";
		}
		String appContextPath = httpServletRequest.getContextPath();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("displayEmployees");
		// Get authenticated user name from SecurityContext
		SecurityContext context = SecurityContextHolder.getContext();

		modelAndView.addObject("message", sessionValue + "You are logged in as " + context.getAuthentication().getName());
		return modelAndView;
	}

}
