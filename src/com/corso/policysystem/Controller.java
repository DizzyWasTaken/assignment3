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


    //TODO see if it can be implemented better
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


    /*
    //User management
    public void registerUser(String email, String password){
        User user = new User(email, password);
        user = db.createAccount(user);

        //After registering the user is logged in and redirected on the homepage
        if(user != null) {
            setCurrentlyLoggedUser(user);
        }
        else{
            System.out.println("Email already in use");
        }

    }
*/
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

    public Policy getPolicyById(Long id){
        return db.getPolicyById(id);
    }

    public void updatePolicy(Long id, String description, Double cost){
        db.updatePolicy(id, description, cost);
    }

    public void removePolicy(Long id){
        db.deletePolicy(id);
        /*
        if(db.removePolicy(id) == null){
            System.out.println("There is no policy with this id in the database");
            return;
        }
        System.out.println("The policy has been removed");*/
    }

    public void buyPolicy(Policy policy){
        //Buy policy logic


        //TODO see if currently logged user can be handled better

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

    public void resetDB(){
        db.clearUsers();
        db.clearPolicy();
    }

    public void closeDBSession(){
        db.closeSession();
    }
}
