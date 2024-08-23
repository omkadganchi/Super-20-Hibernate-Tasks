package com.super20;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.super20.entity.Department;
import com.super20.entity.Employee;




                        // Q.22 Add employee into existing department

public class QuestionNo22 {


    public static void main(String[] args) {

        Configuration cfg = new Configuration();
        cfg.configure();
        cfg.addAnnotatedClass(Department.class);
        cfg.addAnnotatedClass(Employee.class);

        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            Department department = session.get(Department.class, (long)1);
            
            if (department != null) {
                Employee employee = new Employee();
                employee.setFirstName("Nikita"); 
                employee.setLastName("Jirwankar"); 
                employee.setEmail("nikitajirwankar@gmail.com"); 
                employee.setSalary(46000); 
                employee.setDepartment(department); 

                session.save(employee);
                System.out.println("New employee added successfully...");
            } else {
                System.out.println("Department not found!");
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); 
            }
            e.printStackTrace();
        } finally {
            session.close(); 
            sessionFactory.close();
        }
    }
}
