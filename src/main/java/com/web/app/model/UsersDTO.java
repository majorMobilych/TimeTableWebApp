package com.web.app.model;

public class UsersDTO {
    private String login;
    private String name;
    private String password;
    private String role;
    private String checkUserStatus;

    public UsersDTO() {
    }

    public UsersDTO(String login, String name, String password, String checkUserStatus) {
        this.login = login;
        this.name = name;
        this.password = password;
        this.checkUserStatus = checkUserStatus;
        this.role = "user";
    }

    public UsersDTO(String checkUserStatus) {
        this.checkUserStatus = checkUserStatus;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        //TODO: final т.к. новых админов будут регать старые вручную через бд. Потом подумать еще раз
        String role = "user";
        return role;
    }

    public String getCheckUserStatus() {
        return checkUserStatus;
    }
}
