package com.corso.policysystem;

public class User extends Notifiable{

    private String email;
    private String password;
    private Integer policyId;

    public User(String email, String password){
        this.email      = email;
        this.password   = password;
        this.policyId =  null;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPolicyId(Integer policyId) {       //the parameter type is integer so in case of termination of the contract the policyId can be set again to null
        this.policyId = policyId;
    }

    public Integer getPolicyId() {
        return policyId;
    }

    @Override
    protected void onReceiveNotification(Notification n) {
        super.onReceiveNotification(n);

        n.printContent();
    }
}
