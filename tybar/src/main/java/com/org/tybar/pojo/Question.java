package com.org.tybar.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Question {
    private int qstId;// 题号ID
    private String paperId;// 所属问卷ID
    private String qstType;// 题目类型
    private String qstTitle;// 题干
    private String qstOption;// 选项
    private String inputTips;// 填空题提示
    private String selected;

    @Override
    public String toString() {
        return "Question{" +
                "qstId=" + qstId +
                ", paperId='" + paperId + '\'' +
                ", qstType='" + qstType + '\'' +
                ", qstTitle='" + qstTitle + '\'' +
                ", qstOption='" + qstOption + '\'' +
                ", inputTips='" + inputTips + '\'' +
                ", selected='" + selected + '\'' +
                '}';
    }
}
