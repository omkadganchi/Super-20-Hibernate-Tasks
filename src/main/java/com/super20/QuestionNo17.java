package com.super20;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;

import com.super20.entity.Department;
import com.super20.entity.Employee;

import java.util.List;

public class QuestionNo17 {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addAnnotatedClass(Employee.class);
        configuration.addAnnotatedClass(Department.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            
            Criteria criteria = session.createCriteria(Department.class, "department");
            
            criteria.createAlias("employees", "employee", org.hibernate.sql.JoinType.LEFT_OUTER_JOIN);
            
            criteria.setProjection(Projections.projectionList()
                    .add(Projections.property("id").as("departmentId"))
                    .add(Projections.property("name").as("departmentName"))
                    .add(Projections.property("location").as("departmentLocation"))
                    .add(Projections.property("employee.firstName").as("employeeFirstName"))
                    .add(Projections.property("employee.lastName").as("employeeLastName")));

            List<Object[]> results = criteria.list();
            
            for (Object[] result : results) {
                Long departmentId = (Long) result[0];
                String departmentName = (String) result[1];
                String departmentLocation = (String) result[2];
                String employeeFirstName = (String) result[3];
                String employeeLastName = (String) result[4];
                
                System.out.println("Department ID: " + departmentId);
                System.out.println("Department Name: " + departmentName);
                System.out.println("Department Location: " + departmentLocation);
                
                if (employeeFirstName != null && employeeLastName != null) {
                    System.out.println("Employee: " + employeeFirstName + " " + employeeLastName);
                } else {
                    System.out.println("No employees in this department.");
                }
                System.out.println();
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

