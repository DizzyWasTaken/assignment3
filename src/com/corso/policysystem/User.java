package com.corso.policysystem;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User extends Notifiable{

    @Id
    @Column(name = "email")
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "policy_id")
    private Policy policy;

    public User(){}

    public User(String email, String password){
        this.email      = email;
        this.password   = password;
        this.policy =  null;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPolicy(Policy policy) {
        this.policy = policy;
    }

    public Policy getPolicy() {
        return policy;
    }



    @Override
    protected void onReceiveNotification(Notification n) {
        super.onReceiveNotification(n);

        n.printContent();
    }
}
