package com.corso.policysystem;

public class Notification {

    private Notifiable sender;
    private String title;
    private String content;

    public Notification(){
        sender  = null;
        title   = "Title";
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

    public Notifiable getSender() {
        return sender;
    }

    public Notification setTitle(String title) {
        this.title = title;

        return this;
    }

    public String getTitle() {
        return title;
    }

    public Notification setContent(String content) {
        this.content = content;

        return this;
    }

    public String getContent() {
        return content;
    }

    public void printContent(){
        System.out.println("\n\n\nYou got a notification!");

        if(sender instanceof User) {
            System.out.printf("From: %s\n", ((User) this.sender).getEmail());
        }
        else if(sender instanceof Company){
            System.out.printf("From: %s\n", ((Company) this.sender).getCompanyName());
        }
        System.out.printf("Title: %s\n", this.title);
        System.out.printf("Content:\n###\n%s\n###\n", this.content);
        System.out.print("********************************\n");
    }
}
