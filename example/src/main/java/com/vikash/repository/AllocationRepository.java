package com.vikash.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.vikash.modal.Allocation;

public interface AllocationRepository extends CrudRepository<Allocation,Integer> {

	List<Allocation> findByFloorInAndRoomInAndStatusIn(int floor,int room,String status);
	List<Allocation> findByFloorInAndStatusIn(int floor,String status);
	List<Allocation> findBySidInAndStatusIn(int sid,String status);
  	List<Allocation> findBySidIn(int sid);

}
