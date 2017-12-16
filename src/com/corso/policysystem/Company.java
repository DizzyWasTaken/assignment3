package com.corso.policysystem;

public class Company extends Notifiable {

    private final String companyName;

    public Company(String companyName){
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    @Override
    protected void onReceiveNotification(Notification n) {
        super.onReceiveNotification(n);

        n.printContent();
    }
}
