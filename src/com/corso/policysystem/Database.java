package com.corso.policysystem;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Database {
    /**
     * Constructor
     */
    public Database(){

    }

    //TODO format better the code and the try catches

    //NEW CODE
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    private static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public void closeSession(){
        ourSessionFactory.close();
    }

    //START USER MANAGEMENT METHODS
    public String saveUser(final String email, final String password){
        Transaction transaction = null;

        try (Session session = getSession()){           //It's a try with resources, the resource between parenthesis will be closed regardless of the try-catch outcome
            transaction = session.beginTransaction();

            final User user = new User();
            user.setEmail(email);
            user.setPassword(password);
            user.setPolicy(null);
            session.save(user);

            transaction.commit();
        }
        catch (final HibernateException e){
            transaction.rollback();
            e.printStackTrace();
        }

        return email;
    }

    public User getUserByEmail(final String email){
        Transaction transaction = null;
        User user               = null;

        try (Session session = getSession()){
            transaction = session.beginTransaction();

            user = session.get(User.class, email);       //get is useful for retrieving objects by id. if no
                                                                // object is found it returns null
        }
        catch (final HibernateException e){
            transaction.rollback();
            e.printStackTrace();
        }

        return user;
    }

    public void listUsers(){
        Transaction transaction = null;

        try (Session session = getSession()){
            transaction = session.beginTransaction();

            final Stream<User> users = session.createQuery("from User").stream();

            //Printing all users
            System.out.print("\n\n\n");
            users.forEach((u) -> {
                System.out.println("email : " + u.getEmail());
                System.out.println("password : " + u.getPassword());
                System.out.print("current policy: ");
                if(u.getPolicy() != null){
                    System.out.println(u.getPolicy().getId());
                }
                else{
                    System.out.println("no policy");
                }
                System.out.print("-------------------------------\n");
            });
            System.out.println("Printed all users");
        }
        catch (final HibernateException e){
            transaction.rollback();
            e.printStackTrace();
        }

    }

    public void updateUserPolicy(final String email, final Policy policy){
        Transaction transaction = null;

        try (Session session = getSession()){
            transaction = session.beginTransaction();

            final User user = session.get(User.class, email);
            user.setPolicy(policy);

            transaction.commit();
        }
        catch (final HibernateException e){
            transaction.rollback();
            e.printStackTrace();
        }
    }

    public void deleteUser(final String email){
        Transaction transaction = null;

        try (Session session = getSession()){
            transaction = session.beginTransaction();

            final User user = session.get(User.class, email);
            session.delete(user);

            transaction.commit();
        }
        catch (final HibernateException e){
            transaction.rollback();
            e.printStackTrace();
        }
    }

    public void clearUsers(){
        Transaction transaction = null;

        try (Session session = getSession()){
            transaction = session.beginTransaction();

            session.createQuery("delete from User").executeUpdate();

            transaction.commit();
        }
        catch (final HibernateException e){
            transaction.rollback();
            e.printStackTrace();
        }
    }

    //END USER MANAGEMENT METHODS

    //START POLICY MANAGEMENT METHODS

    public Long savePolicy(final String description, final double cost){
        Transaction transaction = null;
        Long id                 = null;

        try (Session session = getSession()){
            transaction = session.beginTransaction();

            final Policy policy = new Policy();
            policy.setDescription(description);
            policy.setCost(cost);
            id = (Long) session.save(policy);

            transaction.commit();
        }
        catch (final HibernateException e){
            transaction.rollback();
            e.printStackTrace();
        }

        return id;
    }

    public Policy getPolicyById(final Long id){
        Transaction transaction = null;
        Policy policy           = null;

        try (Session session = getSession()){
            transaction = session.beginTransaction();

            policy = session.get(Policy.class, id);       //get is useful for retrieving objects by id. if no
                                                         // object is found it returns null
        }
        catch (final HibernateException e){
            transaction.rollback();
            e.printStackTrace();
        }

        return policy;
    }

    public void listPolicies(){
        Transaction transaction = null;

        try (Session session = getSession()){
            transaction = session.beginTransaction();

            final Stream<Policy> policies = session.createQuery("from Policy").stream();

            //Printing all policies
            System.out.print("\n\n\n");
            policies.forEach((p) -> {
                System.out.println("id : " + p.getId());
                System.out.println("Desc: " + p.getDescription());
                System.out.printf("Cost: €%.2f\n", p.getCost());
                System.out.print("-------------------------------\n");
            });
            System.out.println("Printed all policies");
        }
        catch (final HibernateException e){
            transaction.rollback();
            e.printStackTrace();
        }

    }

    public void updatePolicy(final Long id, final String description, final Double cost){
        Transaction transaction = null;

        try (Session session = getSession()){                       //The session is closed before the catch statement.
                                                                    //Calling a rollback on a transaction after the session is closed
                                                                    //throws an exception. This makes the resource available
                                                                    //for the whole transaction
                                                                    //https://stackoverflow.com/questions/9260159/java-7-automatic-resource-management-jdbc-try-with-resources-statement
            try{
                transaction = session.beginTransaction();

                final Policy policy = session.get(Policy.class, id);
                if(description != null){
                    policy.setDescription(description);
                }
                if(cost != null){
                    policy.setCost(cost);
                }

                transaction.commit();
            }
            catch (final Exception e){
                transaction.rollback();
                e.printStackTrace();
            }
        }
    }

    public void deletePolicy(final Long id){
        Transaction transaction = null;

        try (Session session = getSession()) {
            try {
                transaction = session.beginTransaction();

                final Policy policy = session.get(Policy.class, id);
                session.delete(policy);

                transaction.commit();
            } catch (final HibernateException e) {
                transaction.rollback();
                e.printStackTrace();
            }
        }
    }


    public void clearPolicy(){
        Transaction transaction = null;

        try (Session session = getSession()){
            transaction = session.beginTransaction();

            session.createQuery("delete from Policy").executeUpdate();

            transaction.commit();
        }
        catch (final HibernateException e){
            transaction.rollback();
            e.printStackTrace();
        }
    }

    //END POLICY MANAGEMENT METHODS




}
