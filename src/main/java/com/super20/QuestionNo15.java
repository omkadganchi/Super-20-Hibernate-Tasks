package com.super20;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.super20.entity.Department;
import com.super20.entity.Employee;

import java.util.List;



                                  //Q.15 fetch employee names along with their department names

public class QuestionNo15 {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration().configure()
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Department.class)
                .buildSessionFactory();
        Session session = factory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Query<Object[]> query = session.createQuery("select e.firstName, d.name from Employee e join e.department d", Object[].class);

            List<Object[]> list = query.getResultList();
            for (Object[] objects : list) {
                String firstName = (String) objects[0];
                String departmentName = (String) objects[1];
                System.out.println("First Name: " + firstName + " \nDepartment: " + departmentName + "\n");
            }
            transaction.commit();
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
