package com.example.urc;

import java.io.Serializable;

public class UserContact implements Serializable {
    public String UID;
    public String name;
    public String phone;

    public UserContact(String UID, String name, String phone) {
        this.UID = UID;
        this.name = name;
        this.phone = phone;
    }
}
