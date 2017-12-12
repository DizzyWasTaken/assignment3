package com.corso.policysystem;

public class Main {

    public static void main(String[] args){
        //Initialization
        Company     company     = new Company("Policy Solutions LTD");
        Database    db          = new Database();
        Controller  controller  = new Controller(company, db);

        //Inserting Policies
        controller.insertPolicy(0, "cheap", 10);
        controller.insertPolicy(1, "business", 100);
        controller.insertPolicy(2, "premium", 1000);

        //Register Use Case simulation
        controller.registerUser("mario.rossi@policysystem.com", "123");
        controller.registerUser("mario.rossi@policysystem.com", "xyz");

        controller.registerUser("giulio.bianchi@policysystem.com", "abc");

        //Policy Update Use Case Simulation
        Policy p1 = controller.getPolicyToWorkOn(0);

        p1.setDescription("premium gold");
        p1.setCost(10000);

        controller.updatePolicy(p1);

        //Notification to Company Use Case Simulation
        controller.buyPolicy(controller.getCurrentLoggedUser(), p1);
        controller.renewPolicy(controller.getCurrentLoggedUser(), p1);
    }
}
