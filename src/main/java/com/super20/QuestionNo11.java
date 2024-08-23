package com.super20;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.super20.entity.Department;
import com.super20.entity.Employee;

import java.util.List;


                            //Q.11 Select firstname and lastname from employees


public class QuestionNo11 {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration().configure().addAnnotatedClass(Employee.class)
				.addAnnotatedClass(Department.class).buildSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			org.hibernate.query.Query<Object[]> query = session
					.createQuery("select firstName, lastName from Employee", Object[].class);

			List<Object[]> list = query.getResultList();
			for (Object[] objects : list) {
				String firstName = (String) objects[0];
				String lastName = (String) objects[1];

				System.out.println("First Name: " + firstName + " \nLast Name: " + lastName + "\n");
			}

			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
				e.printStackTrace();
			}
		} finally {
			session.close();
			factory.close();
		}
	}
}
