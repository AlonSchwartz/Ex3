package com.elchananalon.ex3;

public class Contact {
    private String name, phoneNumber;
    private int photo;

    public Contact(String name, String phoneNumber, int photo) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getPhoto() {
        return photo;
    }
}
