package com.org.tybar.pojo.vo;

import java.util.List;

public class SavePaperQstData {
    private List<Object> dataList;
    private String paperQst;

    public SavePaperQstData() {
    }

    public SavePaperQstData(List<Object> dataList, String paperQst) {
        this.dataList = dataList;
        this.paperQst = paperQst;
    }

    public List<Object> getDataList() {
        return dataList;
    }

    public void setDataList(List<Object> dataList) {
        this.dataList = dataList;
    }

    public String getPaperQst() {
        return paperQst;
    }

    public void setPaperQst(String paperQst) {
        this.paperQst = paperQst;
    }

    @Override
    public String toString() {
        return "SavePaperQstData{" +
                "dataList=" + dataList +
                ", paperQst='" + paperQst + '\'' +
                '}';
    }
}
