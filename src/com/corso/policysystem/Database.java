package com.corso.policysystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that simulates a database, only the necessary methods to simulate the sequence diagrams are present
 */
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
/*
    Not required for the system but can be useful
    public User getUserByEmail(String email){

        return users.get(email);
    }
*/
    private boolean containsEmail(String email){

        return users.containsKey(email);
    }

    //For debug
    public void printAllUsers(){
        for (String key: users.keySet()) {
            System.out.println("email : " + key);
            System.out.println("password : " + users.get(key).getPassword());
            if(users.get(key).getPolicy_id() != null){
                System.out.println("current policy : " + users.get(key).getPolicy_id());
            }
            else{
                System.out.println("no policy");
            }
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

    //END Policy table management methods
}
