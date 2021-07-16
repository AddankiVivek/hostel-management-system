package com.vikash.services;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vikash.modal.Allocation;
import com.vikash.repository.AllocationRepository;

@Service
@Transactional(rollbackOn=SQLIntegrityConstraintViolationException.class)
public class AllocationService {
	@Autowired
	private final AllocationRepository allocationRepo;
	static int  i=1;

	
	public AllocationService(AllocationRepository allocationRepo) {
		this.allocationRepo=allocationRepo;
	}
	@Temporal(value = TemporalType.DATE)
	public void saveAlloc(Allocation alloc ) {
		alloc.setAllocdate(new Date());
		
		allocationRepo.save(alloc);
	}
	
	
	 public Allocation AllocatingUser(int sid) {
		   return allocationRepo.findById(sid).get();
		   
	   }
	 
	 
	 public void saveAllocation(Allocation alloc ) {
			alloc.setAllocdate(new Date());
			allocationRepo.save(alloc);
		}


	public  List<Allocation> showAllAllocation() {
		List<Allocation> allocs = new ArrayList<Allocation>();
		
		for(Allocation alloc : allocationRepo.findAll()) {
			allocs.add(alloc);
		}
		return allocs;
	}
	
	public void deleteAlloc(int sid) {
		allocationRepo.deleteById(sid);
	}


	public List<Allocation> showRecordOfRoom(int floor,int room, String status) {
		
	List<Allocation> allocs=new ArrayList<Allocation>();
	
	for(Allocation alloc : allocationRepo.findByFloorInAndRoomInAndStatusIn(floor,room,status)) {
		allocs.add(alloc);
	}
	   return allocs;
	}
	public List<Allocation> showRecordOfFloor(int floor,String status) {
		List<Allocation> allocs=new ArrayList<Allocation>();
		
		for(Allocation alloc : allocationRepo.findByFloorInAndStatusIn(floor,status)) {
			allocs.add(alloc);
		}
		   return allocs;
		}
	public List<Allocation> showRecordOfSid(int sid,String status) {
		List<Allocation> allocs=new ArrayList<Allocation>();
		
		for(Allocation alloc : allocationRepo.findBySidInAndStatusIn(sid,status)) {
			allocs.add(alloc);
		}
		   return allocs;
		}
	
	@Temporal(value = TemporalType.DATE)
	public void saveDeallocation(Allocation alloc) {
		    i=i+3;
		alloc.setDeallocdate(new Date());
		alloc.setStatus("DeAllocated");
		alloc.setBal(i);
		allocationRepo.save(alloc);
	} 
}
