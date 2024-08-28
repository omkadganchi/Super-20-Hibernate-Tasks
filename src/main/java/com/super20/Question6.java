package com.super20;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;

import com.super20.entity.Department;
import com.super20.entity.Employee;


                                 //Q.6 Count no.of Employees 

public class Question6 {

	public static void main(String[] args) {
		
		Configuration configuration = new Configuration();
		configuration.configure();
		configuration.addAnnotatedClass(Employee.class);
		configuration.addAnnotatedClass(Department.class);

		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = null;

		try {
			transaction = null;
			Criteria criteria = session.createCriteria(Employee.class);
			criteria.setProjection(Projections.rowCount());
			Long count = (Long) criteria.uniqueResult();
			System.out.println("Total Count is : "+count);
			
			transaction.commit();
		} catch (Exception e) {
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
