package com.super20;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.sql.JoinType;

import com.super20.entity.Department;
import com.super20.entity.Employee;

import java.util.List;

public class QuestionNo21 {
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
            
            criteria.createAlias("employees", "employee", JoinType.LEFT_OUTER_JOIN);
         
            List<Department> departments = criteria.list();
            
            // Process and print the results
            for (Department department : departments) {
                System.out.println("Department ID: " + department.getId());
                System.out.println("Department Name: " + department.getName());
                System.out.println("Department Location: " + department.getLocation());
                
                if (department.getEmployees() != null) {
                    for (Employee employee : department.getEmployees()) {
                        System.out.println("Employee ID: " + employee.getId());
                        System.out.println("Employee First Name: " + employee.getFirstName());
                        System.out.println("Employee Last Name: " + employee.getLastName());
                        System.out.println("Employee Email: " + employee.getEmail());
                        System.out.println("Employee Salary: " + employee.getSalary());
                    }
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
