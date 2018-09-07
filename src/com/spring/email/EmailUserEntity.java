package com.spring.email;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="email_User")
public class EmailUserEntity {
	
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="emailAddress")
	private String emailAddress;
	
	@Column(name="password")
	private String password;
	
	
	public EmailUserEntity(String emailAddress, String password) {
		super();
		this.emailAddress = emailAddress;
		this.password = password;
	}
	
	public EmailUserEntity() {}
	
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "emailUser [id=" + id + ", emailAddress=" + emailAddress + ", password=" + password + "]";
	}
	
	
	
	
}
