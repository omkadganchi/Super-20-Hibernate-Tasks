package com.super20;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;

import com.super20.entity.Department;
import com.super20.entity.Employee;




                     //Q.16 Fetch employees along with department


public class QuestionNo16 {
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
			Criteria criteria = session.createCriteria(Employee.class,"e");
			criteria.createAlias("e.department", "d");
			criteria.setProjection(Projections.projectionList()
				    .add(Projections.property("firstName"))
				    .add(Projections.property("lastName"))
				    .add(Projections.property("d.name")));

			List<Object[]> empList = criteria.list();
			for (Object[] objects : empList) {
				String firstName = (String) objects[0];
                String lastName = (String) objects[1];
                String departmentName = (String) objects[2];

                System.out.println("First Name: " + firstName + " \nLast Name: " + lastName + " \nDepartment: " + departmentName + "\n");
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
