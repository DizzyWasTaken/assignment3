package com.corso.policysystem;

public class User extends Notifiable{

    private String email;
    private String password;

    public User(String email, String password){
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    void onReceiveNotification(Notification n) {
        super.onReceiveNotification(n);

        n.printContent();
    }
}
