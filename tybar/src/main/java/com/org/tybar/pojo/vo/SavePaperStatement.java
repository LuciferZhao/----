package com.org.tybar.pojo.vo;

import com.org.tybar.pojo.QstAnswer;

import java.util.List;

public class SavePaperStatement {

    private List<String> qstTitles;
    private List<QstAnswer> qstAnswer;

    public SavePaperStatement() {
    }

    public SavePaperStatement(List<String> qstTitles, List<QstAnswer> qstAnswer) {
        this.qstTitles = qstTitles;
        this.qstAnswer = qstAnswer;
    }

    public List<String> getQstTitles() {
        return qstTitles;
    }

    public void setQstTitles(List<String> qstTitles) {
        this.qstTitles = qstTitles;
    }

    public List<QstAnswer> getQstAnswer() {
        return qstAnswer;
    }

    public void setQstAnswer(List<QstAnswer> qstAnswer) {
        this.qstAnswer = qstAnswer;
    }

    @Override
    public String toString() {
        return "SavePaperStatement{" +
                "qstTitles=" + qstTitles +
                ", qstAnswer=" + qstAnswer +
                '}';
    }
}
