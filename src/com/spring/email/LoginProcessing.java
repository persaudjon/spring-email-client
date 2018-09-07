package com.spring.email;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class LoginProcessing {

	//Query DB for login details
	//
	private SessionFactory factory ;
	private Session session;
	private boolean loginValid;
	
	public LoginProcessing() {
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
	
	public void emailUniqueValidation(String email, String pass) {
		try {
			session.beginTransaction();
			java.util.List <EmailUserEntity> validEmail = session.createQuery("from EmailUserEntity u where u.emailAddress='" + email + "' and u.password='" + pass + "'").getResultList();
			session.getTransaction().commit();
			if(validEmail.size() != 0) {
				loginValid=true;
			}
			else {
				loginValid=false;
			}
		}
		finally {
			System.out.println("Login is: " + loginValid);
			factory.close();
		}
		
	}


	public boolean isLoginValid() {
		return loginValid;
	}
	
	
}
