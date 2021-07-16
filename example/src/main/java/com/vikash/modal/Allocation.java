package com.vikash.modal;



import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name="Allocation")
public class Allocation {
	 @Id
	 @Column(name = "sid")
	private int sid;
	@Column(name="stdid")
	private String stdid;
	@Column(name="floor")
	private int floor;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Column(name="room")
	private int room;
	@Column(name="allocdate")
	@Temporal(value = TemporalType.DATE)
	private Date allocdate;
	@Column(name="deallocdate")
	@Temporal(value = TemporalType.DATE)
	private Date deallocdate;
	@Column(name="status")
	private String status;
	@OneToOne
	@JoinColumn(name="sid")
	private User user;
	@Column(name="bal")
	private int bal;
	
	
	
	
	public int getBal() {
		return bal;
	}
	public void setBal(int bal) {
		this.bal = bal;
	}
	public Allocation() {
		super();
		
	}
	public String toString() {
		return "Allocation [sid=" + sid  + ", stdid=" + stdid +", floor=" + floor +", room=" + room
				+ ",alloc-date " + allocdate +"DeallocDate"+deallocdate+  "]";
	}
	
	
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getStdid() {
		return stdid;
	}
	public void setStdid(String stdid) {
		this.stdid = stdid;
	}
	
	public int getFloor() {
		return floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	public int getRoom() {
		return room;
	}
	public void setRoom(int room) {
		this.room = room;
	}
	public Date getAllocdate() {
		return allocdate;
	}
	public void setAllocdate(Date aldate) {
		this.allocdate = aldate;
	}
	public Date getDeallocdate() {
		return deallocdate;
	}
	public void setDeallocdate(Date deallocdate) {
		this.deallocdate = deallocdate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Allocation(int sid,String stdid)
	{
		this.sid=sid;
		this.stdid=stdid;
			
	}
	
	public Allocation(int sid, String stdid, int floor, int room, Date allocdate, Date deallocdate, String status) {
		super();
		this.sid = sid;
		this.stdid = stdid;
		this.floor = floor;
		this.room = room;
		this.allocdate = allocdate;
		this.deallocdate = deallocdate;
		this.status = status;
	}
	public Allocation(int sid)
	{
		this.sid=sid;
		
	}
	
}
