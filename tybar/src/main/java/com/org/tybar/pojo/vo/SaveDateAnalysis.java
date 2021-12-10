package com.org.tybar.pojo.vo;

public class SaveDateAnalysis {

    private Object value;
    private Object name;

    public SaveDateAnalysis() {
    }

    public SaveDateAnalysis(Object value, Object name) {
        this.value = value;
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SaveDateAnalysis{" +
                "value=" + value +
                ", name=" + name +
                '}';
    }
}
