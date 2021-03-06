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
        users.put(user.getEmail(), user);
        return user;
    }

    public User updateUser(User user){
        if(getUserByEmail(user.getEmail()) == null){
            //error, no such user
            return null;
        }
        return users.put(user.getEmail(), user);
    }

    public User getUserByEmail(String email){

        return users.get(email);
    }

    //For debug
    public void printAllUsers(){
        System.out.println("\n\n\nDatabase log:");
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
        System.out.print("-------------------------------\n");
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
        System.out.println("\n\n\nDatabase log:");
        for(Policy p : policies){
            System.out.println("id : " + p.getId());
            System.out.println("Desc: " + p.getDescription());
            System.out.printf("Cost: €%.2f\n", p.getCost());
            System.out.print("-------------------------------\n");
        }
        System.out.println("Printed all policies");
        System.out.print("-------------------------------\n");
    }

    //END Policy table management methods
}
