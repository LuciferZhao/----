package com.org.tybar.pojo.vo;

import net.sf.json.JSONArray;

import java.util.List;

public class SaveAnswerDataInfo {

    private String id;
    private String title;
    private JSONArray options;
    private List<Object> answerNumbers;

    public SaveAnswerDataInfo() {
    }

    public SaveAnswerDataInfo(String id, String title, JSONArray options, List<Object> answerNumbers) {
        this.id = id;
        this.title = title;
        this.options = options;
        this.answerNumbers = answerNumbers;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public JSONArray getOptions() {
        return options;
    }

    public void setOptions(JSONArray options) {
        this.options = options;
    }

    public List<Object> getAnswerNumbers() {
        return answerNumbers;
    }

    public void setAnswerNumbers(List<Object> answerNumbers) {
        this.answerNumbers = answerNumbers;
    }

    @Override
    public String toString() {
        return "SaveAnswerDataInfo{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", options=" + options +
                ", answerNumbers=" + answerNumbers +
                '}';
    }
}
