package io.nagender.config;

import org.springframework.stereotype.Component;


public class Student {

	private int studentid;
	private String FirstName;
	private String LastName;
	private String Branch;
	private String Email;
	private String PhoneNumber;
	
	
	
	public String getPhoneNumber() {
		return PhoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}
	public Student() {
		super();
	}
	public int getStudentid() {
		return studentid;
	}
	public void setStudentid(int i) {
		this.studentid = i;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getbranch() {
		return Branch;
	}
	public void setBranch(String branch) {
		Branch = branch;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}

	
	
}
