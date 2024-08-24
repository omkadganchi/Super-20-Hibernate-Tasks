package com.super20;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;

import com.super20.entity.Department;
import com.super20.entity.Employee;

//Q.5 Fetch FirstName and LastName

public class Question5 {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration().configure().addAnnotatedClass(Employee.class)
				.addAnnotatedClass(Department.class).buildSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();

			Criteria criteria = session.createCriteria(Employee.class);
			Projection projection1 = Projections.property("firstName");
			Projection projection2 = Projections.property("lastName");

			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(projection1);
			projectionList.add(projection2);

			criteria.setProjection(projectionList);

			List<Object[]> list = criteria.list();
			for (Object[] objects : list) {
				String firString =(String) objects[0];
				String lasString = (String) objects[1];
				
				System.out.println("First Name: "+firString+" \nLast Name: "+lasString+"\n");
			}
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
