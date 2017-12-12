package com.corso.policysystem;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class PolicyCollection {

    private ArrayList<Policy> policies = new ArrayList<>();

    public void addPolicy(Policy pol){
        for(Policy p : policies){
            if(p.getId() == pol.getId()) return;    //If a policy with this id already exists, don't add it;
        }
        policies.add(pol);
    }

    public void calculatePolicyCost(long i){
        double c = getPolicyById(i).getCost();
        System.out.printf("Cost: %f\n", c);
        //Policy cost calculation logic here
    }

    public void removePolicyById(long i){
        policies.removeIf(p -> p.getId() == i );
    }

    public void printAllPolicies(){
        System.out.print("\n\n\n");
        for(Policy p : policies){
            System.out.printf("Id: %d\n",p.getId());
            System.out.printf("Desc: %s\n", p.getDescription());
            System.out.printf("Cost: %.2f\n", p.getCost());
            System.out.print("-------------------------------\n");
        }
    }

    //Private helper methods
    @Nullable
    public Policy getPolicyById(long i){
        for(Policy p : policies){
            if(p.getId() == i) return p;
        }
        return null;
    }
}
