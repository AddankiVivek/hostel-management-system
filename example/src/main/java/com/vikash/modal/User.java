package com.vikash.modal;

import java.util.Date;
import javax.persistence.*;




@Entity
@Table(name="Registration")
public class User {
	
	@Id
	@Column(name="sid")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQUENCE1")
	@SequenceGenerator(name="SEQUENCE1", sequenceName="SEQUENCE1", allocationSize=1)
	private int sid;
	@Column(name="phno")
	private long phno;
	@Column(name="stdid")
	private String stdid;
	@Column(name="sname")
	private String sname;
	@Column(name="lname")
	private String lname;
	@Column(name="year")
	private int year;
	@Column(name="sem")
	private int sem;
	@Temporal(value = TemporalType.DATE)
	private Date regdate;
	 @OneToOne
	 @JoinColumn(name="sid")
	public Allocation allocation;
	



	public Allocation getAllocation() {
		return allocation;
	}

	public User(int sid, long phno, String stdid, String sname, String lname, int year, int sem, Date regdate) {
		super();
		this.sid = sid;
		this.phno = phno;
		this.stdid = stdid;
		this.sname = sname;
		this.lname = lname;
		this.year = year;
		this.sem = sem;
		this.regdate = regdate;
	}

	public void setAllocation(Allocation allocation) {
		this.allocation = allocation;
	}

	public String getStdid() {
		return stdid;
	}

	public void setStdid(String stdid) {
		this.stdid = stdid;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public User() {
		super();
	}
	
	
	/*public User(long phno, String sname, int year, int sem) {
		super();
		this.phno = phno;
		this.sname = sname;
		this.year = year;
		this.sem = sem;
		
	}*/
	

	@Override
	public String toString() {
		return "User [sid=" + sid  + ", phone number=" + phno +", sname=" + sname +", year=" + year
				+ ", sem=" + sem +"Reg Date"+regdate+  "]";
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public long getPhno() {
		return phno;
	}

	public void setPhno(long phno) {
		this.phno = phno;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}


	public int getYear() {
		return year;
	}


	public void setYear(int year) {
		this.year = year;
	}


	public int getSem() {
		return sem;
	}


	public void setSem(int sem) {
		this.sem = sem;
	}
		
	public String getLname() {
		return lname;
	}


	public void setLname(String lname) {
		this.lname = lname;
	}
	
}
