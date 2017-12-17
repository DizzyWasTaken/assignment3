package com.corso.policysystem;

public class User extends Notifiable{

    private String email;
    private String password;
    private Integer policy_id;

    public User(String email, String password){
        this.email      = email;
        this.password   = password;
        this.policy_id  =  null;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPolicy_id(Integer policy_id) {       //the parameter type is integer so in case of termination of the contract the policy_id can be set again to null
        this.policy_id = policy_id;
    }

    public Integer getPolicy_id() {
        return policy_id;
    }

    @Override
    protected void onReceiveNotification(Notification n) {
        super.onReceiveNotification(n);

        n.printContent();
    }
}
