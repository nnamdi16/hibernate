package com.company.hibernate.demo;

import com.company.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudentDemo {

    public static void main(String[] args) {
        //Create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        //Create session
        Session session = factory.getCurrentSession();

        try {
            //Use the session object to save Java Object
            //Start a transaction
            session.beginTransaction();

            //Query Students
           List<Student> theStudents = session.createQuery("from Student").list();

           //Display the student
            displayStudent(theStudents);

            //Query student: lastName='Doe'
            theStudents = session.createQuery("from Student s where  s.lastName = 'Doe'").list();

            //Display the student
            System.out.println("\n\n Student who have last name of Doe");
            displayStudent(theStudents);

            //Commit transaction
            session.getTransaction().commit();
            System.out.println("Done !!");
        }
        finally {
            factory.close();
        }
    }

    private static void displayStudent(List<Student> theStudents) {
        for (Student tempStudent:theStudents) {
            System.out.println(tempStudent);
        }
    }
}
