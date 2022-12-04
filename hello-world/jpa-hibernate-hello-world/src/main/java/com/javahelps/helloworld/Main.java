package com.javahelps.helloworld;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Create an EntityManagerFactory when you start the application.
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JavaHelps");
        // Create the DAO object
        StudentDao dao = new StudentDao(entityManagerFactory);

        // Create some students to play around
        Student alice = new Student();
        alice.setId(1);
        alice.setName("Alice");
        alice.setAge(22);

        Student bob = new Student();
        bob.setId(2);
        bob.setName("Bob");
        bob.setAge(20);

        Student charlie = new Student();
        charlie.setId(3);
        charlie.setName("Charlie");
        charlie.setAge(25);

        // Create the Students
        dao.create(alice);
        dao.create(bob);
        dao.create(charlie);


        // Update the age of Bob using the id
        Student newBob = new Student();
        newBob.setId(2);    // You must use the same id as existing entity
        newBob.setName("Bob");
        newBob.setAge(25);
        dao.update(newBob);

        // Delete the Alice from database
        dao.delete(1);

        // Print all the Students
        List<Student> students = dao.findAll();
        if (students != null) {
            for (Student student : students) {
                System.out.println(student);
            }
        }

        // Fixme: Deleting all students to avoid duplicate entry errors when running this code multiple times
        dao.deleteAll();

        // Never forget to close the entityManagerFactory
        entityManagerFactory.close();
    }
}