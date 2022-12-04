package com.javahelps.helloworld;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.RollbackException;

import java.util.List;

public class StudentDao {

    private final EntityManagerFactory entityManagerFactory;

    public StudentDao(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public List<Student> findAll() {
        // Create a new EntityManager
        EntityManager manager = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;
        List<Student> students;
        try {
            // Get a transaction
            transaction = manager.getTransaction();
            // Begin the transaction
            transaction.begin();

            // Get all students from the table.
            // Note that the SQL is selecting from "Student" entity not the "student" table
            students = manager.createQuery("SELECT stu FROM Student stu", Student.class)
                    .getResultList();

            // Commit the transaction
            transaction.commit();
        } catch (RollbackException ex) {
            // Commit failed. Rollback the transaction
            if (transaction != null) {
                transaction.rollback();
            }
            // TODO: Decide how you want to handle this exception.
            //  Since this is a hello world project, we throw the exception.
            throw new RuntimeException(ex);
        } finally {
            // Close the EntityManager
            manager.close();
        }
        return students;
    }

    public void create(Student student) {
        // Create a new EntityManager
        EntityManager manager = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;

        try {
            // Get a transaction
            transaction = manager.getTransaction();
            // Begin the transaction
            transaction.begin();
            // Save the student object
            manager.persist(student);
            // Commit the transaction
            transaction.commit();
        } catch (RollbackException ex) {
            // Commit failed. Rollback the transaction
            if (transaction != null) {
                transaction.rollback();
            }
            // TODO: Decide how you want to handle this exception.
            //  Since this is a hello world project, we throw the exception.
            throw new RuntimeException(ex);
        } finally {
            // Close the EntityManager
            manager.close();
        }
    }

    public void update(Student newStudentWithSameId) {
        // Create a new EntityManager
        EntityManager manager = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;

        try {
            // Get a transaction
            transaction = manager.getTransaction();
            // Begin the transaction
            transaction.begin();
            // First find the student to update the object
            // You cannot insert a new student with the same id since it will be treated as a duplicate entry
            Student student = manager.find(Student.class, newStudentWithSameId.getId());
            if (student != null) {
                // Note that the id cannot be changed
                student.setName(newStudentWithSameId.getName());
                student.setAge(newStudentWithSameId.getAge());
                // Save the changes
                manager.persist(student);
            }
            // Commit the transaction
            transaction.commit();
        } catch (RollbackException ex) {
            // Commit failed. Rollback the transaction
            if (transaction != null) {
                transaction.rollback();
            }
            // TODO: Decide how you want to handle this exception.
            //  Since this is a hello world project, we throw the exception.
            throw new RuntimeException(ex);
        } finally {
            // Close the EntityManager
            manager.close();
        }
    }

    public void delete(int id) {
        // Create a new EntityManager
        EntityManager manager = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;

        try {
            // Get a transaction
            transaction = manager.getTransaction();
            // Begin the transaction
            transaction.begin();
            // First find the student
            Student student = manager.find(Student.class, id);
            if (student != null) {
                // Remove the student
                manager.remove(student);
            }
            // Commit the transaction
            transaction.commit();
        } catch (RollbackException ex) {
            // Commit failed. Rollback the transaction
            if (transaction != null) {
                transaction.rollback();
            }
            // TODO: Decide how you want to handle this exception.
            //  Since this is a hello world project, we throw the exception.
            throw new RuntimeException(ex);
        } finally {
            // Close the EntityManager
            manager.close();
        }
    }

    public void deleteAll() {
        // Create a new EntityManager
        EntityManager manager = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;

        try {
            // Get a transaction
            transaction = manager.getTransaction();
            // Begin the transaction
            transaction.begin();
            // Delete all students
            manager.createQuery("DELETE FROM Student")
                    .executeUpdate();
            // Commit the transaction
            transaction.commit();
        } catch (RollbackException ex) {
            // Commit failed. Rollback the transaction
            if (transaction != null) {
                transaction.rollback();
            }
            // TODO: Decide how you want to handle this exception.
            //  Since this is a hello world project, we throw the exception.
            throw new RuntimeException(ex);
        } finally {
            // Close the EntityManager
            manager.close();
        }
    }
}
