package com.corso.policysystem;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.SessionFactoryBuilder;
import org.hibernate.boot.spi.SessionFactoryBuilderFactory;
import org.hibernate.boot.spi.SessionFactoryBuilderImplementor;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Database {
    //Each of these private variables represents one table of the database
    private Map<String, User> users = new HashMap<>();
    private ArrayList<Policy> policies = new ArrayList<>();     //Simulates a table with an autoincrement id

    /**
     * Constructor
     */
    public Database(){

    }

    //User table management methods
/*
    public User createAccount(User user){
        if(containsEmail(user.getEmail())){
            //error, email already present in database
            return null;
        }
        users.put(user.getEmail(), user);
        return user;
    }

    public User updateUser(User user){
        if(!containsEmail(user.getEmail())){
            //error, no such user
            return null;
        }
        return users.put(user.getEmail(), user);
    }
*/
/*
    Not required for the system but can be useful
    public User getUserByEmail(String email){

        return users.get(email);
    }
*/

/*
    private boolean containsEmail(String email){

        return users.containsKey(email);
    }
*/
/*
    //For debug
    public void printAllUsers(){
        System.out.print("\n\n\n");
        for (String key: users.keySet()) {
            System.out.println("email : " + key);
            System.out.println("password : " + users.get(key).getPassword());
            System.out.print("current policy: ");
            if(users.get(key).getPolicyId() != null){
                System.out.println(users.get(key).getPolicyId());
            }
            else{
                System.out.println("no policy");
            }
            System.out.print("-------------------------------\n");
        }
        System.out.println("Printed all users");
    }

    //END User table management methods

    //Policy table management methods

    public void insertPolicy(String description, double cost){
        Policy policy = new Policy(policies.size(), description, cost); //The database has to create this object because the id isn't known beforehand
        policies.add(policy);
    }

    public void updatePolicy(Policy policy){
        int id = policy.getId();

        if(policies.size() > id){
            policies.set(id, policy);
        }
    }

    public Policy removePolicy(int id){

        return policies.remove(id);
    }

    public Policy getPolicyById(int id){

        return policies.get(id);
    }

    //For debug
    public void printAllPolicies(){
        System.out.print("\n\n\n");
        for(Policy p : policies){
            System.out.println("id : " + p.getId());
            System.out.println("Desc: " + p.getDescription());
            System.out.printf("Cost: €%.2f\n", p.getCost());
            System.out.print("-------------------------------\n");
        }
    }
*/
    //END Policy table management methods



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


    //START USER MANAGEMENT METHODS
    public String saveUser(final String email, final String password){
        Transaction transaction = null;

        try (Session session = getSession()){           //It's a try with resources, the resource between parenthesis will be closed regardless of the try-catch outcome
            transaction = session.beginTransaction();
            final User user = new User(email, password);
            session.save(user);
            transaction.commit();
        }
        catch (final HibernateException e){
            transaction.rollback();
            e.printStackTrace();
        }

        return email;
    }

    public User containsEmail(String email){
        Transaction transaction = null;
        User user               = null;

        try (Session session = getSession()){
            transaction = session.beginTransaction();

            user = (User) session.get(User.class, email);       //get is useful for retrieving objects by id. if no
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

            users.forEach((c) -> System.out.println(c.getEmail()));
        }
        catch (final HibernateException e){
            transaction.rollback();
            e.printStackTrace();
        }

    }

    public void updateUser(){

    }

    public void deleteUser(){

    }






}
