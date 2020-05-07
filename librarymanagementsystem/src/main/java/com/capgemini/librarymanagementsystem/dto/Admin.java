package com.capgemini.librarymanagementsystem.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Admin implements Serializable {

	private int adminId = (int)Math.random(); ;
	private String name ;
	private String email ;
	private String password ;
	private long mobileNo;

	public Admin(int adminId, String name, String email, String password, long mobileNo) {
		
		super();
		this.adminId = adminId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.mobileNo = mobileNo;
	}

	public Admin() {
		// TODO Auto-generated constructor stub
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}

}
