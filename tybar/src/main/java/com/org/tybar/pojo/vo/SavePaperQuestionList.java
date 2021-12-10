package com.org.tybar.pojo.vo;

public class SavePaperQuestionList {

    private String questionList;

    public SavePaperQuestionList() {
    }

    public SavePaperQuestionList(String questionList) {
        this.questionList = questionList;
    }

    public String getQuestionList() {
        return questionList;
    }

    public void setQuestionList(String questionList) {
        this.questionList = questionList;
    }

    @Override
    public String toString() {
        return "SavePaperQuestionList{" +
                "questionList='" + questionList + '\'' +
                '}';
    }
}
