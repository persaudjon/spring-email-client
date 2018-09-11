package com.spring.email;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class InboxProcess {
	
	private SessionFactory factory ;
	private Session session;
	
	public InboxProcess() {
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

	public List<EmailEntity> messagesForUsers(String toEmailAddress) {
		//create list to hold emailEntity
		java.util.List<EmailEntity> emailList;
		//query for all emails where to = emailAddres
		try {
			session.beginTransaction();
			 emailList = session.createQuery("from EmailEntity s where s.toUser = '" + toEmailAddress + "'").getResultList();
			session.getTransaction().commit();
		}
		finally {
			factory.close();
		}
		//return list
		return emailList;
	}
}
