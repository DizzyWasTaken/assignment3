package com.corso.policysystem;

public class Notification {

    private Notifiable sender;
    private String title;
    private String content;

    public Notification(){
        sender = null;
        title = "Title";
        content = "Content";
    }

    public static Notification prepare(){
        return new Notification();
    }

    public void sendTo(Notifiable receiver){
        receiver.receiveNotification(this);
    }

    public Notification setSender(Notifiable sender) {
        this.sender = sender;

        return this;
    }

    public Notification setTitle(String title) {
        this.title = title;

        return this;
    }

    public Notification setContent(String content) {
        this.content = content;

        return this;
    }

    public void printContent(){
        System.out.print("\n\n\n");

        if(sender instanceof User) {
            System.out.printf("From: %s\n", ((User) sender).getEmail());
        }
        else if(sender instanceof Company){
            System.out.printf("From: %s\n", ((Company) sender).getCompanyName());
        }
        System.out.printf("Title: %s\n", title);
        System.out.printf("Content:\n###\n%s\n###\n", content);
        System.out.print("********************************\n");
    }
}
