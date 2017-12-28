package com.corso.policysystem;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "policies")
public class Policy {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "cost")
    private Double cost;

    @OneToMany(mappedBy = "policy")
    private Set<User> users;

    public Policy(){}

    public void setDescription(String description){
        this.description = description;
    }

    public void setCost(Double cost){
        this.cost = cost;
    }

    public Long getId(){
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Double getCost(){
        return cost;
    }

}
