package com.corso.policysystem;

public class Controller {

    private Company company;
    private Database db;
    private User currentlyLoggedUser;

    public Controller(Company company, Database database){
        this.company                = company;
        this.db                     = database;
        this.currentlyLoggedUser    = null;
    }


    //User management
    public void registerUser(String email, String password){
        if(db.getUserByEmail(email) == null) {
            db.saveUser(email, password);
            setCurrentlyLoggedUser(db.getUserByEmail(email));
        }
        else{
            System.out.println("Email already in use");
        }
    }

    public void setCurrentlyLoggedUser(User user){
        currentlyLoggedUser = user;
    }

    public User getCurrentlyLoggedUser(){
        return currentlyLoggedUser;
    }
    //END USER MANAGEMENT

    //Policy management
    public void insertPolicy(String description, double cost){
        db.savePolicy(description, cost);
    }

    public Policy getPolicyById(Integer id){
        return db.getPolicyById(id);
    }

    public void updatePolicy(Integer id, String description, Double cost){
        db.updatePolicy(id, description, cost);
    }

    public void removePolicy(Long id){
        db.deletePolicy(id);
    }

    public void buyPolicy(Policy policy){
        //Buy policy logic

        db.listUsers();
        db.updateUserPolicy(currentlyLoggedUser.getEmail(), policy);
        db.listUsers();

        Notification.prepare()
                .setSender(currentlyLoggedUser)
                .setTitle("New policy purchase")
                .setContent("Policy id: "+policy.getId()+"\nPolicy description: "+policy.getDescription())
                .sendTo(company);
    }

    public void renewPolicy(Policy policy){
        //Policy renewal logic

        Notification.prepare()
                .setSender(currentlyLoggedUser)
                .setTitle("New policy renewal application")
                .setContent("Policy id: "+policy.getId()+"\nPolicy description: "+policy.getDescription())
                .sendTo(company);
    }

    public void showAllPolicies(){
        db.listPolicies();
    }

    public void closeDBSession(){
        db.closeSession();
    }
}
