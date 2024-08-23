package com.super20;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;
import org.hibernate.query.Query;

import com.super20.entity.Department;
import com.super20.entity.Employee;




                               //Q.9 Find avg of salary

public class QuestionNo14 {

	public static void main(String[] args) {

		SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(Employee.class)
				.addAnnotatedClass(Department.class).buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Query<Object[]> query = session.createQuery("\"select department.id, count(id) from Employee group by department.id", Object[].class);
			   List<Object[]> list = query.getResultList();
	            for (Object[] objects : list) {
	                Long departmentId = (Long) objects[0];
	                Long employeeCount = (Long) objects[1];

	                System.out.println("Department ID: " + departmentId + " \nEmployee Count: " + employeeCount + "\n");
	            }

	            // Commit transaction
	            transaction.commit();
	        } catch (Exception e) {
	            if (transaction != null) {
	                transaction.rollback();
	                e.printStackTrace();
	            }
	        } finally {
	            // Close session and session factory
	            session.close();
	            sessionFactory.close();
	        }
	    }
	}