package com.super20;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import com.super20.entity.Department;
import com.super20.entity.Employee;



                      //Pegination: Fetch Department with pegination

public class Question4 {

	public static void main(String[] args) {
		
		SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(Employee.class).addAnnotatedClass(Department.class).buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		
		try {
			transaction = null;
			Criteria criteria = session.createCriteria(Department.class);
			criteria.setFirstResult(0);
			criteria.setMaxResults(5);
			
			List<Department> departments = criteria.list();
			
			for (Department department : departments) {
				System.out.println(department);
			}
			transaction.commit();
		} catch (Exception e) {
			if(transaction != null) {
				transaction.rollback();
				e.printStackTrace();
			}
		} finally {
			session.close();
			sessionFactory.close();
		}
	}

}
