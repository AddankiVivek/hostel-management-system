package com.vikash.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vikash.modal.Allocation;
import com.vikash.modal.User;
import com.vikash.services.AllocationService;
import com.vikash.services.UserService;

@RestController
public class ApplicationController {

	@Autowired
	UserService userService;
	@Autowired
	AllocationService allocService;
	

	@RequestMapping("/welcome")
	public String Welcome(HttpServletRequest request) {
		request.setAttribute("mode", "MODE_HOME");
		return "welcomepage";
	}

	@RequestMapping("/register")
	public String registration(HttpServletRequest request) {
		request.setAttribute("mode", "MODE_REGISTER");
		return "welcomepage";
	}

	@PostMapping("/save-user")
	public String registerUser(@ModelAttribute User user, BindingResult bindingResult, HttpServletRequest request) {
		userService.saveMyUser(user);
		request.setAttribute("mode", "MODE_SUCCESS");
		return "welcomepage";
	}
	@PostMapping("/save-Allocation")
	public String allocateUser(@ModelAttribute Allocation alloc,BindingResult bindingResult, HttpServletRequest request) {
		allocService.saveAlloc(alloc);
		int sid=alloc.getSid();
		int room=alloc.getRoom();
		int floor=alloc.getFloor();
		if(room == 0 && floor==0) {
			allocService.deleteAlloc(sid);
		}
		request.setAttribute("users", userService.showAllUsers());
		request.setAttribute("msg1", "*Allocation was Successful");
		request.setAttribute("mode", "ALL_USERS");
		return "welcomepage";
	}

	@GetMapping("/show-users")
	public String showAllUsers(HttpServletRequest request) {
		if(userService.showAllUsers().isEmpty())
		{
			request.setAttribute("msg", "*There is no one pending with the action");
		}
		request.setAttribute("users", userService.showAllUsers());
		request.setAttribute("mode", "ALL_USERS");
		return "welcomepage";
	}
	
	@RequestMapping("/delete-user")
	public String deleteUser(@RequestParam int sid, HttpServletRequest request) {
		userService.deleteMyUser(sid);
		request.setAttribute("users", userService.showAllUsers());
		request.setAttribute("mode", "ALL_USERS");
		return "welcomepage";
	}
	@RequestMapping("/edit-user")
	public String updateUser(@RequestParam int sid,HttpServletRequest request) {
		request.setAttribute("user", userService.updateUser(sid));
		request.setAttribute("mode", "MODE_UPDATE");
		return "welcomepage";
	} 
	@RequestMapping("/Allocate-user")
	public String AllocatingUser(@RequestParam int sid, @RequestParam String stdid,@RequestParam String sname,@RequestParam String lname,@ModelAttribute Allocation alloc,HttpServletRequest request) {
		/*Allocation allocc= new Allocation(sid,stdid);
		allocService.saveAllocation(allocc);*/
		request.setAttribute("sid", sid);
		request.setAttribute("stdid", stdid);
		request.setAttribute("sname", sname);
		request.setAttribute("lname", lname);
		/*request.setAttribute("alloc", allocService.AllocatingUser(sid));*/
		request.setAttribute("mode", "MODE_Allocation");
		return "welcomepage";
	} 
	


	
	@RequestMapping("/login")
	public String login(HttpServletRequest request) {
		request.setAttribute("mode", "MODE_LOGIN");
		return "welcomepage";
	}
	@GetMapping("/show-Allocated")
	public String showAllAllocation(@ModelAttribute Allocation alloc,HttpServletRequest request) {
		if(allocService.showAllAllocation().isEmpty()) {
			request.setAttribute("msg", "*No one is allocated yet");
		}
		request.setAttribute("allocs", allocService.showAllAllocation());
		request.setAttribute("mode", "ALL_ALLOCATED");
		return "welcomepage";
	}
	
	
	@GetMapping("/search")
	public String showSearchOptions(HttpServletRequest request) {
		request.setAttribute("mode", "REPORTS");
		return "test";
	}
	/*@GetMapping("/search-by-room")
	public String showRoomBase(HttpServletRequest request) {
		request.setAttribute("mode", "SEARCH_ROOM");
		return "test";
	}*/
	@RequestMapping("/search-Room")
	public String showRecordOfRoom(@RequestParam int floor, @RequestParam int room,@RequestParam String sidTemp,@RequestParam String status,HttpServletRequest request) {
		int sid = 0;
		if(sidTemp=="")
		{
			sid = 0; 
		}
		else {
			sid = Integer.parseInt(sidTemp);
		}
		if(sid == 0 && room == 0 && floor==0) {
			request.setAttribute("msg", "*Please enter any cridential for search you entered nothing");
			request.setAttribute("allocs", allocService.showRecordOfFloor(floor, status));
		}
		else if(sid == 0 && room == 0) {
			if(allocService.showRecordOfFloor(floor, status).isEmpty()) {
				request.setAttribute("msg", "*There is no room occupied in this floor");
			}

			request.setAttribute("allocs", allocService.showRecordOfFloor(floor, status));
			
		}
		else if(sid==0)
		{
			if(allocService.showRecordOfRoom(floor,room, status).isEmpty()) {
				request.setAttribute("msg", "*This  room was not occupied yet");
			}
			request.setAttribute("allocs", allocService.showRecordOfRoom(floor, room,status));
		}
		else {
			if(allocService.showRecordOfSid(sid, status).isEmpty()) {
				request.setAttribute("msg", "*There is no record of specified sid");
			}
			request.setAttribute("allocs", allocService.showRecordOfSid(sid,status));
		}
		request.setAttribute("mode", "SEARCH_ROOM");
		return "test";
	}
	@GetMapping("/search-by-floor")
	public String showFloorBase(HttpServletRequest request) {
		
		request.setAttribute("mode", "SEARCH_FLOOR");
		return "test";
	}
	@RequestMapping("/search-floor")
	public String showRecordOfFloor(@RequestParam int floor,@RequestParam String status,HttpServletRequest request) {
		
		request.setAttribute("allocs", allocService.showRecordOfFloor(floor,status));
		request.setAttribute("mode", "SEARCH_FLOOR");
		return "test";
	}
	@GetMapping("/search-by-sid")
	public String showsidBase(HttpServletRequest request) {
		request.setAttribute("mode", "SEARCH_SID");
		return "test";
	}
	@RequestMapping("/search-sid")
	public String showRecordOfSid(@RequestParam int sid,@RequestParam String status,HttpServletRequest request) {
		request.setAttribute("allocs", allocService.showRecordOfSid(sid,status));
		request.setAttribute("mode", "SEARCH_SID");
		return "test";
	}
	@RequestMapping("/DeAllocate-user")
	public String DeAllocation( @ModelAttribute @Validated Allocation alloc,@RequestParam int sid,@RequestParam String stdid,BindingResult bindingResult,HttpServletRequest request) {
		request.setAttribute("alloc", allocService.AllocatingUser(sid));
		request.setAttribute("mode", "MODE_HOME");
		return "welcomepage";
	}
	@RequestMapping("/save-Deallocation")
	public String saveDeallocation(@ModelAttribute Allocation alloc,BindingResult bindingResult,HttpServletRequest request) {
		allocService.saveDeallocation(alloc);
		request.setAttribute("mode", "ALL_ALLOCATED");
		return "welcomepage";
	}
	@Temporal(value = TemporalType.DATE)
	public Date allocdate;
	@RequestMapping("Deallocating-user")
	public String invokedeallocation(@RequestParam Integer sid,@RequestParam String stdid,@RequestParam Integer room,@RequestParam Integer floor,@RequestParam String aldate,@ModelAttribute Allocation alloc,HttpServletRequest request) {
		
		try {
			 SimpleDateFormat in = new SimpleDateFormat("yyyy-MM-dd");
			  allocdate = in.parse(aldate);
			alloc.setAllocdate(allocdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("alloc", allocService.AllocatingUser(sid));
		allocService.saveDeallocation(alloc);
		request.setAttribute("mode", "MODE_HOME");
		return "welcomepage";
	} 
	@GetMapping("/details")
	public String showDetails(HttpServletRequest request) {
		request.setAttribute("mode", "ALL_ALLOCATED_DETAILS");
		return "welcomepage";
	}
	
	@RequestMapping("/show-Details")
	public String showAllAllocated(@RequestParam String status,HttpServletRequest request) {
		request.setAttribute("users", userService.showAllocatedDetails(status));
		request.setAttribute("mode", "ALL_ALLOCATED_DETAILS");
		return "welcomepage";
	}

	
	
}
