package com.org.tybar.pojo.vo;

public class SaveIndexPapers {

    private String paperId;
    private String paperTitle;
    private int submitTimes;
    private int paperStatus;// 问卷状态  1:发布; 2:保存

    public SaveIndexPapers() {
    }

    public SaveIndexPapers(String paperId, String paperTitle, int submitTimes, int paperStatus) {
        this.paperId = paperId;
        this.paperTitle = paperTitle;
        this.submitTimes = submitTimes;
        this.paperStatus = paperStatus;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public String getPaperTitle() {
        return paperTitle;
    }

    public void setPaperTitle(String paperTitle) {
        this.paperTitle = paperTitle;
    }

    public int getSubmitTimes() {
        return submitTimes;
    }

    public void setSubmitTimes(int submitTimes) {
        this.submitTimes = submitTimes;
    }

    public int getPaperStatus() {
        return paperStatus;
    }

    public void setPaperStatus(int paperStatus) {
        this.paperStatus = paperStatus;
    }

    @Override
    public String toString() {
        return "SaveIndexPapers{" +
                "paperId='" + paperId + '\'' +
                ", paperTitle='" + paperTitle + '\'' +
                ", submitTimes=" + submitTimes +
                ", paperStatus=" + paperStatus +
                '}';
    }
}
