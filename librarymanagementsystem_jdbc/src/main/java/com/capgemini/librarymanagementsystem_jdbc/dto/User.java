package com.capgemini.librarymanagementsystem_jdbc.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class User implements Serializable{

	
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	
	private String password;
	private long mobileNo;
	private String role;
	private int noOfBooksBorrowed=0;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getNoOfBooksBorrowed() {
		return noOfBooksBorrowed;
	}
	public void setNoOfBooksBorrowed(int noOfBooksBorrowed) {
		this.noOfBooksBorrowed = noOfBooksBorrowed;
	}
	
	@Override
	public String toString() {
		return String.format("%-10s %-10s %-10s %-15s %-10s %-13s %s", id, firstName, lastName, email, password,
				mobileNo, role);
	}
	
}
