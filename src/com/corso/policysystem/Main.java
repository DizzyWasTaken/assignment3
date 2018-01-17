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

        //Register Use Case Simulation
        controller.registerUser("mario.rossi@policysystem.com", "123");
        controller.registerUser("mario.rossi@policysystem.com", "xyz");

        controller.registerUser("giulio.bianchi@policysystem.com", "abc");

        //Update Policy Use Case Simulation
        controller.updatePolicy(1, "premium gold", 10000D);

        //Buy Policy Use Case Simulation
        controller.buyPolicy(controller.getPolicyById(1));

        controller.closeDBSession();
    }
}
