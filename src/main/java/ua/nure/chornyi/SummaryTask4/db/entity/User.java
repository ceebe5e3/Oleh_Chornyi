package ua.nure.chornyi.SummaryTask4.db.entity;

import java.util.Date;

/**
 * User entity
 */
public class User extends Entity {

    private String fullName;

    private Date dateOfBirth;

    private String address;

    private String email;

    private String login;

    private String password;

    private int roleId;

    public User(String fullName, Date dateOfBirth, String address, String email, String login, String password, int roleId) {
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.email = email;
        this.login = login;
        this.password = password;
        this.roleId = roleId;
    }

    public User() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "User{" +
                "fullName='" + fullName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", roleId=" + roleId +
                '}';
    }
}
