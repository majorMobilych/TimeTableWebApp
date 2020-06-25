package com.web.app.model;

public class UsersDTO {
    private String login;
    private String name;
    private String password;
    private int roleId;
    private String checkUserStatus;

    public UsersDTO() {
    }

    public UsersDTO(String login, String name, String password, String checkUserStatus) {
        this.login = login;
        this.name = name;
        this.password = password;
        this.checkUserStatus = checkUserStatus;
        this.roleId = 2;
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

    public int getRole_id() {
        //TODO: final т.к. новых админов будут регать старые вручную через бд. Потом подумать еще раз
        int role_id = 2;
        return role_id;
    }

    public String getCheckUserStatus() {
        return checkUserStatus;
    }
}
