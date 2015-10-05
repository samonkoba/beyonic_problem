/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beyonic.database;

import java.io.Serializable;

/**
 *
 * @author <%request.getAttribute("username");%>
 */
public class User implements Serializable{
	
    private String email;
    private String phone;
    private String full_name;
    private String password;
    private String verification_code;
    private int Id;
    private boolean activation;
    
    
    
    
    
    
	public User(String email, String phone, String full_name, String password,
			String verification_code,boolean activation) {
		super();
		this.email = email;
		this.phone = phone;
		this.full_name = full_name;
		this.password = password;
		this.verification_code = verification_code;
		this.activation = activation;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getFull_name() {
		return full_name;
	}
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getVerification_code() {
		return verification_code;
	}
	public void setVerification_code(String verification_code) {
		this.verification_code = verification_code;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public boolean isActivation() {
		return activation;
	}
	public void setActivation(boolean activation) {
		this.activation = activation;
	}
    
    

}
