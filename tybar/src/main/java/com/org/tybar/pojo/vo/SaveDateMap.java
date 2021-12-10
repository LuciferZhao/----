package com.org.tybar.pojo.vo;

import java.util.List;

public class SaveDateMap {
    private String title;
    private String id;
    private List<Object> dataList;

    public SaveDateMap() {
    }

    public SaveDateMap(String title,String id, List<Object> dataList) {
        this.title = title;
        this.id = id;
        this.dataList = dataList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Object> getDataList() {
        return dataList;
    }

    public void setDataList(List<Object> dataList) {
        this.dataList = dataList;
    }

    @Override
    public String toString() {
        return "SaveDateMap{" +
                "title='" + title + '\'' +
                ", id='" + id + '\'' +
                ", dataList=" + dataList +
                '}';
    }
}
