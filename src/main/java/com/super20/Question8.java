package com.super20;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;

import com.super20.entity.Department;
import com.super20.entity.Employee;

//Q.8 Find Sum of salaries of Employees
public class Question8 {

	public static void main(String[] args) {

		SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(Employee.class).addAnnotatedClass(Department.class).buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.setProjection(Projections.sum("salary"));
		long totalSal = (long) criteria.uniqueResult();
		System.out.println("Total salary of employees is: "+totalSal);
		transaction.commit();
	}
		catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
				e.printStackTrace();
			}
		} finally {
			session.close();
			sessionFactory.close();
		}
	}
}
