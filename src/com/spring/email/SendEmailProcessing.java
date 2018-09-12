package com.spring.email;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SendEmailProcessing {
	
	private SessionFactory factory ;
	private Session session;

	
	public SendEmailProcessing() {
		// TODO Auto-generated constructor stub
		createSessionFactory();
		//on intilization create session factory
	}
	
	private void createSessionFactory() {
		factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(EmailEntity.class).buildSessionFactory();
		createDBSession();
	}
	
	private void createDBSession() {
		session = factory.getCurrentSession();
	}
	
	
	public void createNewEmailUser(String title, String fromUser,String toUser,String message,String timeSent) {
		
		try {

				EmailEntity email = new EmailEntity(title, fromUser, toUser, message);
				//start transaction
				session.beginTransaction();
				//save email object
				session.save(email);
				//commit transaction
				session.getTransaction().commit();
			}
			
		
		finally {
			factory.close();

		
		}
	}

}
