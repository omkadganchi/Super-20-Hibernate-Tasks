package com.super20;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.super20.entity.Department;
import com.super20.entity.Employee;


//Q.1 reterive all employees


public class Question1 {

	public static void main(String... args) {

		Configuration configuration = new Configuration();
		configuration.configure();
		configuration.addAnnotatedClass(Employee.class);
		configuration.addAnnotatedClass(Department.class);

		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = null;

		try {
			Criteria criteria = session.createCriteria(Employee.class);
			List<Employee> list = criteria.list();
			for (Employee employee : list) {
				System.out.println(employee);
			}
			transaction.commit();
		}

		catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
				e.printStackTrace();
			}
		}

		finally {
			session.close();
			sessionFactory.close();
		}
	}
}