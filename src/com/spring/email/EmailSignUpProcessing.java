package com.spring.email;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import antlr.collections.List;

public class EmailSignUpProcessing {
	
	
	private SessionFactory factory ;
	private Session session;
	private boolean emailValid;
	
	
	public EmailSignUpProcessing() {
		// TODO Auto-generated constructor stub
		createSessionFactory();
		//on intilization create session factory
	}
	
	private void createSessionFactory() {
		factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(EmailUserEntity.class).buildSessionFactory();
		createDBSession();
	}
	
	private void createDBSession() {
		session = factory.getCurrentSession();
	}
	
	public void emailUniqueValidation(String email) {
		try {
			session.beginTransaction();
			java.util.List <EmailUserEntity> validEmail = session.createQuery("from EmailUserEntity u where u.emailAddress='" + email + "'").getResultList();
			session.getTransaction().commit();
			if(validEmail.size() != 0) {
				System.out.println("Email Already exists");
				emailValid = false;
			}
			else {
				System.out.println("email is valid");
				emailValid=true;
			}
		}
		finally {
			session.close();
		}
	}
	
	public boolean createNewEmailUser(String email, String pass) {
		
		try {
			//create email entity object
			emailUniqueValidation(email);
			createDBSession();
			if(emailValid == true) {
				EmailUserEntity newUser = new EmailUserEntity(email, pass);
				//start transaction
				session.beginTransaction();
				//save email object
				session.save(newUser);
				//commit transaction
				session.getTransaction().commit();
			}
			
		}
		finally {
			factory.close();
			return emailValid;
		
		}
	}

	
}
