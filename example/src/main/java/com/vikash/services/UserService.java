package com.vikash.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vikash.modal.User;
import com.vikash.repository.UserRepository;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private final UserRepository userRepository;
	
	
	public UserService(UserRepository userRepository) {
		this.userRepository=userRepository;
		
	}
	
	public void saveMyUser(User user ) {
		user.setRegdate(new Date());
		userRepository.save(user);
	}
	
	public List<User> showAllUsers(){
		List<User> users = new ArrayList<User>();
		for(User user : userRepository.findAllByIdNotInAllocationId()) {
			users.add(user);
		}
		return users;
		
	}
	

	public void deleteMyUser(int sid) {
		
		userRepository.deleteById(sid);
		
	}

   public User updateUser(int sid) {
	   return userRepository.findById(sid).get();
	   
   }
  
	
	  
	  public List<User> showAllocatedDetails(String allocationStatus){
		  List<User> users = new ArrayList<User>();
			for(User user : userRepository.findByAllocationStatus(allocationStatus)) {
				users.add(user);
			}
		 
		return users;
		  
	  }
	  
	 
	
	}

	