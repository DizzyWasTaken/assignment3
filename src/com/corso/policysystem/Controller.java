package com.corso.policysystem;

public class Controller {

    private Company company;
    private Database db;
    private User currentLoggedUser;

    public Controller(Company company, Database database){
        this.company = company;
        this.db = database;
    }

    public void registerUser(String email, String password){
        User user = new User(email, password);
        user = db.createAccount(user);

        //After registering the user is logged in and redirected on the homepage
        if(user != null) {
            setCurrentLoggedUser(user);
        }
        else{
            System.out.print("Email already in use");
        }

    }

    public void setCurrentLoggedUser(User user){
        currentLoggedUser = user;
    }

    public User getCurrentLoggedUser(){
        return currentLoggedUser;
    }

    public void insertPolicy(String description, long cost){
        db.insertPolicy(description, cost);
    }

    public Policy getPolicyToWorkOn(int id){
        return db.getPolicyById(id);
    }

    public void updatePolicy(Policy policy){
        /*
        if(policy == null){
            System.out.print("No policy specified");
            return;
        }*/

        db.updatePolicy(policy);

    }



    public void removePolicy(int id){
        if(db.removePolicy(id) == null){
            System.out.println("There is no policy with this id in the database");
            return;
        }
        System.out.println("The policy has been removed");
    }

    public void buyPolicy(User user, Policy policy){
        //Buy policy logic

        Notification.prepare()
                .setSender(user)
                .setTitle("New policy purchase")
                .setContent("Policy id: "+policy.getId()+"\nPolicy description: "+policy.getDescription())
                .sendTo(company);
    }

    public void renewPolicy(User user, Policy policy){
        Notification.prepare()
                .setSender(user)
                .setTitle("New policy renewal application")
                .setContent("Policy id: "+policy.getId()+"\nPolicy description: "+policy.getDescription())
                .sendTo(company);
    }
}
