package com.spring.email;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="inbox_email")
public class EmailEntity {
	
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="fromUser")
	private String fromUser;
	
	@Column(name="toUser")
	private String toUser;
	
	@Column(name="message")
	private String message;
	
	@Column(name="timeSent")
	private String timeSent;
	
	public EmailEntity(String title, String fromUser, String toUser, String message) {
		super();
		this.title=title;
		this.fromUser=fromUser;
		this.toUser=toUser;
		this.message=message;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		timeSent =  LocalDateTime.now().toString();
	}
	
	public EmailEntity() {}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFromUser() {
		return fromUser;
	}

	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}

	public String getToUser() {
		return toUser;
	}

	public void setToUser(String toUser) {
		this.toUser = toUser;
	}

	public String getTimeSent() {
		return timeSent;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

		

}
