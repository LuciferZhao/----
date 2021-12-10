package com.org.tybar.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class QstAnswer {

    private int answerId;
    private String paperId;
    private int userId;
    private Object answer;

    public QstAnswer(int answerId, Object answer) {
        this.answerId = answerId;
        this.answer = answer;
    }

    public QstAnswer(String paperId, int userId, Object answer) {
        this.paperId = paperId;
        this.userId = userId;
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "QstAnswer{" +
                "answerId=" + answerId +
                ", paperId='" + paperId + '\'' +
                ", userId=" + userId +
                ", answer='" + answer + '\'' +
                '}';
    }
}
