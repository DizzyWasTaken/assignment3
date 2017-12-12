package com.corso.policysystem;

public class User extends Notifiable{

    private String email;
    private String pwd;

    public User(String email, String pwd){
        this.email = email;
        this.pwd = pwd;
    }

    public String getEmail() {
        return email;
    }

    public String getPwd() {
        return pwd;
    }

    @Override
    void onReceiveNotification(Notification n) {
        super.onReceiveNotification(n);

        n.printContent();
    }
}
