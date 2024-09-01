package com.super20;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.super20.entity.Department;
import com.super20.entity.Employee;



               //Q.13 Fetch id and firstname from employees

public class QuestionNo13 {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration().configure().addAnnotatedClass(Employee.class)
				.addAnnotatedClass(Department.class).buildSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			Query<Object[]> query = session.createQuery("select id, firstName from Employee", Object[].class);

			List<Object[]> list = query.getResultList();
			for (Object[] objects : list) {
				Number id = (Number) objects[0];
				String firstName = (String) objects[1];

				System.out.println("Id: " + id + " \nFirst Name: " + firstName + "\n");
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
