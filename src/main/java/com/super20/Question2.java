package com.super20;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.super20.entity.Department;
import com.super20.entity.Employee;



                                         //Q.2 finding employee whose salary is greater than 20000


public class Question2 {

	public static void main(String... args) {

		SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(Employee.class)
				.addAnnotatedClass(Department.class).buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();

			Criteria criteria = session.createCriteria(Employee.class);

			criteria.add(Restrictions.gt("salary", 48000));

			List<Employee> employees = criteria.list();

			for (Employee employee : employees) {
				System.out.println(employee);
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