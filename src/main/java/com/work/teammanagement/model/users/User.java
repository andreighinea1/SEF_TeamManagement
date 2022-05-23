package com.work.teammanagement.model.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.work.teammanagement.exceptions.NotEnoughAvailableDaysException;
import com.work.teammanagement.model.users.serializing.UserRoleFromStrConverter;
import com.work.teammanagement.model.users.serializing.UserRoleToStrConverter;

import static com.work.teammanagement.services.Encoding.encodePassword;

public class User {
    private String username;
    private String passwordHash;
    @JsonSerialize(converter = UserRoleToStrConverter.class)
    @JsonDeserialize(converter = UserRoleFromStrConverter.class)
    private UserRole role;
    private String fullName;
    private String address;
    private String phone;
    private String managerUsername;
    private int availableHolidayDays;


    // This empty constructor is needed for JSON (just like the unused getters)
    public User() {
    }

    public User(String username, String password, UserRole role, String managerUsername,
                String fullName, String address, String phone) {
        this.username = username;
        this.passwordHash = encodePassword(username, password);
        this.role = role;
        if (role == UserRole.Manager)
            this.managerUsername = "";
        else
            this.managerUsername = managerUsername;
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
        this.availableHolidayDays = 21;
    }


    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public UserRole getRole() {
        return role;
    }

    public String getFullName() {
        return fullName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getManagerUsername() {
        return managerUsername;
    }

    public int getAvailableHolidayDays() {
        return availableHolidayDays;
    }

    public void setManagerUsername(String managerUsername) {
        this.managerUsername = managerUsername;
    }

    public void decreaseAvailableHolidayDays(int amount) throws NotEnoughAvailableDaysException {
        if (amount > availableHolidayDays)
            throw new NotEnoughAvailableDaysException();
        availableHolidayDays -= amount; // TODO: Update GUI with this
    }


    @JsonIgnore
    public boolean isManager() {
        return role == UserRole.Manager;
    }

    @JsonIgnore
    public boolean isEmployee() {
        return role == UserRole.Employee;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!username.equals(user.username)) return false;
        if (!passwordHash.equals(user.passwordHash)) return false;
        return role.equals(user.role);
    }

    public boolean equals(String username, String password, UserRole role) {
        if (!this.username.equals(username)) return false;
        if (!passwordHash.equals(encodePassword(username, password))) return false;
        return this.role == role;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", passwordHash='some_hash'" +
                ", role=" + role +
                ", fullName='" + fullName + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", managerUsername='" + managerUsername + '\'' +
                "}";
    }

    public String toStringForList() {
        return "Uname: " + username + ", " + "MgUname: " + managerUsername + ", " + fullName + ", " + address + ", " + phone;
    }
}
