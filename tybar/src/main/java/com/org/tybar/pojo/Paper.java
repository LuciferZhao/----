package com.org.tybar.pojo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Paper {
    private String paperId;// 问卷ID
    private int userId;// 用户ID
    private String paperTitle;// 问卷标题
    private String paperSummary;// 问卷摘要介绍
    private String paperStartDate;// 问卷发布日期
    private String paperEndDate;// 问卷结束日期
    private int paperStatus;// 问卷状态  1:发布; 2:保存
    private int paperLimitCount;// 问卷回答次数
    private String questionList;// 问题列表

    public Paper() {
    }

    public Paper(String paperId, int userId, String paperTitle, String paperSummary, String paperStartDate, String paperEndDate, int paperStatus,String questionList) {
        this.paperId = paperId;
        this.userId = userId;
        this.paperTitle = paperTitle;
        this.paperSummary = paperSummary;
        this.paperStartDate = paperStartDate;
        this.paperEndDate = paperEndDate;
        this.paperStatus = paperStatus;
        this.questionList = questionList;
    }

    public Paper(String paperId, String paperTitle, String paperSummary, String paperEndDate, int paperStatus, String questionList) {
        this.paperId = paperId;
        this.paperTitle = paperTitle;
        this.paperSummary = paperSummary;
        this.paperEndDate = paperEndDate;
        this.paperStatus = paperStatus;
        this.questionList = questionList;
    }

    @Override
    public String toString() {
        return "Paper{" +
                "paperId='" + paperId + '\'' +
                ", userId=" + userId +
                ", paperTitle='" + paperTitle + '\'' +
                ", paperSummary='" + paperSummary + '\'' +
                ", paperStartDate='" + paperStartDate + '\'' +
                ", paperEndDate='" + paperEndDate + '\'' +
                ", paperStatus=" + paperStatus +
                ", paperLimitCount=" + paperLimitCount +
                ", questionList=" + questionList +
                '}';
    }
}
