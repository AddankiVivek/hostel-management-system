
package com.vikash.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.vikash.services.AllocationService;
import com.vikash.services.UserService;

@ControllerAdvice
public class GlobalHandling {

	@Autowired
	public AllocationService allocationService;

	@Autowired
	public UserService userService;

	public GlobalHandling(AllocationService allocationService, UserService userService) {
		super();
		this.allocationService = allocationService;
		this.userService = userService;
	}

	private static final Logger logger = LoggerFactory.getLogger(GlobalHandling.class);

	@ExceptionHandler(RuntimeException.class)
	public String handleSQLException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
		logger.info("Internal Server Exception Occured:: URL=" + request.getRequestURL());
		StringBuffer link = request.getRequestURL();
		System.out.println(link);

		if (("http://localhost:8080/save-Allocation").equals(link.toString())) {

			request.setAttribute("users", userService.showAllUsers());
			request.setAttribute("mode", "ALL_USERS");
			request.setAttribute("msg", "*The Room was already Allocated for someone");
			return "welcomepage";

		} else {
			request.setAttribute("mode", "MODE_REGISTER");
			request.setAttribute("msg", "*The student is already registered for this semister");
			return "welcomepage";
		}
	}

}
