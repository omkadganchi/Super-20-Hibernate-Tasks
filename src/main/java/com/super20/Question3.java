package com.super20;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import com.super20.entity.Department;
import com.super20.entity.Employee;

//Q.3 fetch all employee entites order them by their last name in asc order

public class Question3 {

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
			criteria.addOrder(Order.asc("lastName"));

			List<Employee> list = criteria.list();
			for (Employee employee : list) {
				System.out.println(employee.getLastName());
			}
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
