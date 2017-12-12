package com.corso.policysystem;

public class Policy {

    private int id;
    private String description;
    private double cost;    //Symbolizes the cost of the policy
    //expiration;

    public Policy(int id, String description, double cost){
        this.id = id;
        this.description = description;
        this.cost = cost;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setCost(double cost){
        this.cost = cost;
    }

    public int getId(){
        return id;
    }

    public String getDescription() {
        return description;
    }

    public double getCost(){
        return cost;
    }

}
