package com.koushik;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.koushik.modal.Allocation;
import com.koushik.modal.User;
import com.koushik.repository.AllocationRepository;
import com.koushik.repository.UserRepository;
import com.koushik.services.AllocationService;
import com.koushik.services.UserService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyApplicationTests {
	
	@Mock
	AllocationRepository allocationRepo;
	@Mock
	UserRepository userRepo;
	
	
	@InjectMocks
	AllocationService allocationService;
	
	@InjectMocks
	UserService userService;
	
	
	

	@Test
	public void showRecordOfRoomTest () throws ParseException {
		List <Allocation> testList = new ArrayList <Allocation>();
		testList.add(new Allocation (161, "2354267", 4, 8,new SimpleDateFormat("yyyy-mm-dd").parse("2018-09-03") ,new Date() , "Allocated"));
		when (allocationRepo.findByFloorInAndRoomInAndStatusIn(4, 8, "Allocated")).thenReturn(testList);
		assertEquals(testList, allocationService.showRecordOfRoom(4,8,"Allocated"));
	}
	@Test
	public void showRecordOfSidTest () throws ParseException {
		List <Allocation> testList = new ArrayList <Allocation>();
		testList.add(new Allocation (161, "2354267", 4, 8,new SimpleDateFormat("yyyy-mm-dd").parse("2018-09-03") ,new Date() , "Allocated"));
		when (allocationRepo.findBySidInAndStatusIn(161, "Allocated")).thenReturn(testList);
		assertEquals(testList, allocationService.showRecordOfSid(161,"Allocated"));
	}
	@Test
	public void showRecordOfFloorTest () throws ParseException {
		List <Allocation> testList = new ArrayList <Allocation>();
		testList.add(new Allocation (161, "2354267", 4, 8,new SimpleDateFormat("yyyy-mm-dd").parse("2018-09-03") ,new Date() , "Allocated"));
		when (allocationRepo.findByFloorInAndStatusIn(4, "Allocated")).thenReturn(testList);
		assertEquals(testList, allocationService.showRecordOfFloor(4,"Allocated"));
	}
	/*@Test
	public void showRecordOfFloorTest1 () throws ParseException {
		List <Allocation> testList = new ArrayList <Allocation>();
		testList.add(new Allocation (161, "2354267", 4, 8,new SimpleDateFormat("yyyy-mm-dd").parse("2018-09-03") ,new Date() , "Allocated"));
		when (allocationRepo.findByFloorInAndStatusIn(3, "Allocated")).thenReturn(testList);
		assertEquals(testList, allocationService.showRecordOfFloor(4,"Allocated"));
	}*/
	@Test
	public void showAllAllocationTest () throws ParseException {
		List <Allocation> testList = new ArrayList <Allocation>();
		testList.add(new Allocation (161, "2354267", 4, 8,new SimpleDateFormat("yyyy-mm-dd").parse("2018-09-03") ,new Date() , "Allocated"));
		when (allocationRepo.findAll()).thenReturn(testList);
		assertEquals(testList, allocationService.showAllAllocation());
	}
	
	@Test
	public void showAllUsersTest () throws ParseException {
		List <User> testList = new ArrayList <User>();
		testList.add(new User (169, 8465046879L, "2354267", "Sai koushik","Addanki",4,2,new SimpleDateFormat("yyyy-mm-dd").parse("2018-09-05")));
		when (userRepo.findAllByIdNotInAllocationId()).thenReturn(testList);
		assertEquals(testList, userService.showAllUsers());
	}
	@Test
	public void  showAllocatedDetailsTest () throws ParseException {
		List <User> testList = new ArrayList <User>();
		testList.add(new User (169, 8465046879L, "2354267", "Sai koushik","Addanki",4,2,new SimpleDateFormat("yyyy-mm-dd").parse("2018-09-05")));
		when (userRepo.findByAllocationStatus("Allocated")).thenReturn(testList);
		assertEquals(testList, userService.showAllocatedDetails("Allocated"));
	}
	

}
