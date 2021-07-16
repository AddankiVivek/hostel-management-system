package com.koushik.repository;



import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.koushik.modal.User;
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {	
	
	
	public  List<User> findByAllocationStatus(String allocationStatus);
	/*public List<User> findAllBySidNotInAllocationSid();*/	
	 /*@Query("select Distinct u FROM User u WHERE  EXISTS(select u from User u,Allocation a where a.sid!=u.sid)")*/
	@Query("select u from User u LEFT JOIN Allocation a ON u.sid =a.sid where a.sid IS NULL")
	   public List<User> findAllByIdNotInAllocationId();
}
