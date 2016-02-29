package com.employeemgmt.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.employeemgmt.entity.EmployeeEntity;
import com.employeemgmt.entity.Response;
import com.employeemgmt.service.EmployeeManager;

@Controller
public class EmployeeController {

	@Autowired
	private com.employeemgmt.service.EmployeeManager employeeManager;

	/*
	 * @RequestMapping(value = "/updateEmployee", method = RequestMethod.POST,
	 * produces = MediaType.APPLICATION_JSON_VALUE, consumes =
	 * MediaType.APPLICATION_JSON_VALUE)
	 * 
	 * @ResponseBody public String deleteEmployee(@RequestBody EmployeeEntity
	 * employee){ System.out.println("Prem Update = " +
	 * employee.getFirstname()); return ""; }
	 */

	@RequestMapping(value = "/updateEmployee", method = RequestMethod.GET)
	public String updateEmployee(@RequestParam(value="employeeId") int employeeId, ModelMap map){
		System.out.println("Prem Update employeeId = " + employeeId);
		map.addAttribute("employee",employeeManager.getEmployee(employeeId));
		return "addEmployee";
	}

	@RequestMapping(value = "/deleteEmployee", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String deleteEmployee(
			@RequestParam(value = "employeeId") int employeeId)
			throws JsonGenerationException, JsonMappingException, IOException {
		System.out.println("Prem deleteEmployee called");
		ObjectWriter ow = new ObjectMapper().writer()
				.withDefaultPrettyPrinter();
		String response = null;
		try {
			employeeManager.deleteEmployee(employeeId);
			response = ow.writeValueAsString(new Response("0", "SUCCESS"));
		} catch (Exception e) {
			System.out.println("Prem Delete Failed = " + e);
			response = ow.writeValueAsString(new Response("10", "FAILED"));

		}

		return response;
	}

	@RequestMapping(value = "/listemployee", method = RequestMethod.GET)
	public String listEmployees(ModelMap map) {
		// map.addAttribute("employee", new
		// com.employeemgmt.entity.EmployeeEntity());
		map.addAttribute("employeeList", employeeManager.getAllEmployees());
		System.out.println("prem employeeList = "
				+ employeeManager.getAllEmployees());
		return "listemployee";
	}

	@RequestMapping(value = "/addemployee", method = RequestMethod.GET)
	public String newEmployee(ModelMap map) {
		map.addAttribute("employee",
				new com.employeemgmt.entity.EmployeeEntity());
		return "addEmployee";
	}

	@RequestMapping(value = "/addemployee", method = RequestMethod.POST)
	public String addEmployee(
			@ModelAttribute(value = "employee") EmployeeEntity employee,
			BindingResult result) {
		employeeManager.addEmployee(employee);
		return "redirect:/listemployee";
	}

	@RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
	public ModelAndView defaultPage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security + Hibernate Example");
		model.addObject("message", "This is default page!");
		model.setViewName("mainpage");
		return model;

	}

	@RequestMapping(value = "/admin**", method = RequestMethod.GET)
	public ModelAndView adminPage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security + Hibernate Example");
		model.addObject("message", "This page is for ROLE_ADMIN only!");
		model.setViewName("admin");

		return model;

	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout,
			HttpServletRequest request) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error",
					getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");

		return model;

	}

	// customize the error message
	private String getErrorMessage(HttpServletRequest request, String key) {

		Exception exception = (Exception) request.getSession()
				.getAttribute(key);

		String error = "";
		if (exception instanceof BadCredentialsException) {
			error = "Invalid username and password!";
		} else if (exception instanceof LockedException) {
			error = exception.getMessage();
		} else {
			error = "Invalid username and password!";
		}

		return error;
	}

	// for 403 access denied page
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

		ModelAndView model = new ModelAndView();

		// check if user is login
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			System.out.println(userDetail);

			model.addObject("username", userDetail.getUsername());

		}

		model.setViewName("403");
		return model;

	}

}