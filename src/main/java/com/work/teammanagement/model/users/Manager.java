package com.work.teammanagement.model.users;

public class Manager  extends User{

    // This empty constructor is needed for JSON (just like the unused getters)
    public Manager() {
    }

    public Manager(String username, String password, UserRole role,
                   String fullName, String address, String phone) {
        super(username, password, role, fullName, address, phone);
    }
}
