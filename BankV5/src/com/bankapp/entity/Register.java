package com.bankapp.entity;

import com.bankapp.constant.Role;

public class Register {
	
	private int userid;
	private String username;
	private String fname;
	private String lname;
	private String password;
	private Role role;
	public Register(String username, String fname, String lname, String password, Role role) {
		super();
		this.username = username;
		this.fname = fname;
		this.lname = lname;
		this.password = password;
		this.role = role;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "Register [userid=" + userid + ", username=" + username + ", fname=" + fname + ", lname=" + lname
				+ ", password=" + password + ", role=" + role + "]";
	}
	

};
