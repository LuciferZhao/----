package com.org.tybar.pojo.vo;

public class SaveUserAnwserInfo {
    private String paperId;
    private int userId;

    public SaveUserAnwserInfo() {
    }

    public SaveUserAnwserInfo(String paperId, int userId) {
        this.paperId = paperId;
        this.userId = userId;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "SaveUserAnwserInfo{" +
                "paperId='" + paperId + '\'' +
                ", userId=" + userId +
                '}';
    }
}
