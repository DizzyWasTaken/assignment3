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

    public User createAccount(String email, String pwd){
        if(containsEmail(email)){
            //error
            return null;
        }
        return users.put(email, new User(email, pwd));
    }

    public User getUserByEmail(String email){

        return users.get(email);
    }

    public boolean containsEmail(String email){

        return users.containsKey(email);
    }

    //For debug
    public void printAllUsers(){
        for (String key: users.keySet()) {
            System.out.println("key : " + key);
            System.out.println("value : " + users.get(key).getPwd());
        }
    }

    //END User table management methods

    //Policy table management methods

    public void insertPolicy(String description, long cost){
        Policy policy = new Policy(policies.size(), description, cost);
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
