package com.company.hibernate.demo;

import com.company.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReadStudentDemo {

    public static void main(String[] args) {
        //Create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory();

        //Create session
        Session session = factory.getCurrentSession();

        try {
            //Use the session object to save Java Object

            //Create  a student object
            System.out.println("Creating new student object... ");
            Student tempStudent = new Student("Simon", "Lobos", "simon@luv2code.com");
            System.out.println(tempStudent.getId());

            //Start a transaction
            session.beginTransaction();

            //Save the student object
            System.out.println("Saving the student");
            System.out.println(tempStudent);
            session.save(tempStudent);

            //Commit transaction
            session.getTransaction().commit();

            //Find out the student's id : primary key
            System.out.println("Saved student. Generate id: " + tempStudent.getId());

            //Now get a new session and start a transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            //Retrieve student based on the id: primary key
            System.out.println("\n Getting student with id: " + tempStudent.getId());
            Student myStudent = session.get(Student.class,tempStudent.getId());
            System.out.println("Cet complete: " + myStudent);

            //Commit Transaction
            session.getTransaction().commit();

            System.out.println("Done !!");

        }
        finally {
            factory.close();
        }
    }
}
