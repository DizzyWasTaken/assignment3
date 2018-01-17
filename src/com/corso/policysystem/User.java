package com.corso.policysystem;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User extends Notifiable{

    @Id
    @Column(name = "email", length = 100)   //If the length is left 255 then it can't be a primary key
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "policy_id")
    private Policy policy;

    public User(){}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Policy getPolicy() {
        return policy;
    }

    public void setPolicy(Policy policy) {
        this.policy = policy;
    }



    @Override
    protected void onReceiveNotification(Notification n) {
        super.onReceiveNotification(n);

        n.printContent();
    }
}
