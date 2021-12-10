package com.org.tybar.pojo.vo;

public class SaveUserInfo {

    private int id;
    private String username;
    private String avatar;

    public SaveUserInfo() {
    }

    public SaveUserInfo(int id, String username, String avatar) {
        this.id = id;
        this.username = username;
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "SaveUserInfo{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
