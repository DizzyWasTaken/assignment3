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
        User user = new User(email, password);

        //After registering the user is logged in and redirected on the homepage
        if(db.getUserByEmail(email) == null) {
            user = db.createAccount(user);
            setCurrentlyLoggedUser(user);
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


    //Policy management
    public void insertPolicy(String description, long cost){
        db.insertPolicy(description, cost);
    }

    public Policy getPolicyById(int id){
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

    public void buyPolicy(Policy policy){
        //Buy policy logic

        db.printAllUsers();
        currentlyLoggedUser.setPolicyId(policy.getId());
        db.updateUser(currentlyLoggedUser);
        db.printAllUsers();

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
}
