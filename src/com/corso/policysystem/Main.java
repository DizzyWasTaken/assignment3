package com.corso.policysystem;

public class Main {

    public static void main(String[] args){
        //Initialization
        Company     company     = new Company("Policy Solutions LTD");
        Database    db          = new Database();
        Controller  controller  = new Controller(company, db);

        //Inserting Policies
        controller.insertPolicy("cheap", 10);
        controller.insertPolicy("business", 100);
        controller.insertPolicy("premium", 1000);

        controller.showAllPolicies();

        //Register Use Case simulation
        controller.registerUser("mario.rossi@policysystem.com", "123");
        controller.registerUser("mario.rossi@policysystem.com", "xyz");

        controller.registerUser("giulio.bianchi@policysystem.com", "abc");

        //Update Policy Use Case Simulation
        Policy p1 = controller.getPolicyById(0);

        p1.setDescription("premium gold");
        p1.setCost(10000);

        controller.updatePolicy(p1);

        //Buy Policy Use Case Simulation
        controller.buyPolicy(p1);
    }
}
