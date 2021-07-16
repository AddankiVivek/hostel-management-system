package com.vikash.controller;

import org.springframework.web.bind.annotation.GetMapping;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vikash.modal.User;
import com.vikash.repository.UserRepository;

/*import org.springframework.web.bind.annotation.RequestParam;

import com.vikash.modal.User;
import com.vikash.services.UserService;*/

@org.springframework.web.bind.annotation.RestController
public class RestController {
	@Autowired
	private final UserRepository userRepo;
	
	public RestController(UserRepository userRepository) {
		this.userRepo=userRepository;
		
	}
	/*@Autowired
	private UserService userService;*/

	@GetMapping("/")
	public String hello() {
		return "This is Home page";
	}
	
	@RequestMapping(value="/download", method=RequestMethod.GET) 
	public ResponseEntity<Object> downloadFile() throws IOException  {
		FileWriter filewriter =  null;
		try {
			String allocationStatus="Allocated";
		List<User> csvDataList = new ArrayList<>();
		for(User user : userRepo.findByAllocationStatus(allocationStatus))
			csvDataList.add(user);
		
		
		
		StringBuilder filecontent = new StringBuilder("REGISTRATIONID,STUDENTID, FIRST NAME,LAST NAME,PHONE NUMBER,FLOOR,ROOM\n");
		for(User csv:csvDataList) {
			filecontent.append(csv.getSid()).append(",").append(csv.getStdid()).append(",").append(csv.getSname()).append(",").append(csv.getLname()).append(",").append(csv.getPhno()).append(",").append(csv.allocation.getFloor()).append(",").append(csv.allocation.getRoom()).append("\n");
		}
		
		String filename = "C:\\Users\\sai.addanki\\Downloads\\Reports\\Report.csv";
		
	 filewriter = new FileWriter(filename);
		filewriter.write(filecontent.toString());
		filewriter.flush();
		
		File file = new File(filename);
		
		InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");
		
		ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(headers).contentLength(file.length()).contentType(MediaType.parseMediaType("application/txt")).body(resource);
		return responseEntity;
		} catch (Exception e ) {
			return new ResponseEntity<>("error occurred", HttpStatus.INTERNAL_SERVER_ERROR);	
		} finally {
			if(filewriter!=null)
				filewriter.close();
		}
	}

	
	/*@GetMapping("/saveuser")
	public String saveUser(@RequestParam long phno, @RequestParam String sname, @RequestParam int year, @RequestParam int sem) {
		User user = new User(phno, sname, year, sem);
		userService.saveMyUser(user);
		return "User Saved";
	}*/
	@RequestMapping(value="/download1", method=RequestMethod.GET) 
	public ResponseEntity<Object> downloadFile1() throws IOException  {
		FileWriter filewriter =  null;
		try {
			
		List<User> csvDataList = new ArrayList<>();
		for(User user : userRepo.findAllByIdNotInAllocationId())
			csvDataList.add(user);
		
		
		
		StringBuilder filecontent = new StringBuilder("REGISTRATIONID,STUDENTID, FIRST NAME,LAST NAME,PHONE NUMBER,YEAR,SEMISTER\n");
		for(User csv:csvDataList) {
			filecontent.append(csv.getSid()).append(",").append(csv.getStdid()).append(",").append(csv.getSname()).append(",").append(csv.getLname()).append(",").append(csv.getPhno()).append(",").append(csv.getYear()).append(",").append(csv.getSem()).append("\n");
		}
		
		String filename = "C:\\Users\\sai.addanki\\Downloads\\Reports\\NoActionReport.csv";
		
	 filewriter = new FileWriter(filename);
		filewriter.write(filecontent.toString());
		filewriter.flush();
		
		File file = new File(filename);
		
		InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");
		
		ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(headers).contentLength(file.length()).contentType(MediaType.parseMediaType("application/txt")).body(resource);
		return responseEntity;
		} catch (Exception e ) {
			return new ResponseEntity<>("error occurred", HttpStatus.INTERNAL_SERVER_ERROR);	
		} finally {
			if(filewriter!=null)
				filewriter.close();
		}
	}
	
}
