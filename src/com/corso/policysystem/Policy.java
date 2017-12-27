package com.corso.policysystem;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "policies")
public class Policy {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "description")
    private String description;

    @Column(name = "cost")
    private double cost;

    @OneToMany(mappedBy = "policy")
    private Set<User> users;

    public Policy(){}

    public Policy(int id, String description, double cost){
        this.id             = id;
        this.description    = description;
        this.cost           = cost;
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
