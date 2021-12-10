package com.org.tybar.service;

import com.org.tybar.pojo.QstAnswer;
import com.org.tybar.pojo.vo.SaveUserAnwserInfo;

import java.util.List;

public interface QstAnswerService {

    // 新增答案
    int addQstAnswer(QstAnswer qstAnswer);

    // 根据Id获取所有问卷答案信息
    List<QstAnswer> getQstAnswerInfos(String paperId);

    // 根据answerId查询答案信息
    QstAnswer getQstAnserInfosById(int answerId);

    // 复制答案
    int copyQstAnswer(List<QstAnswer> qstAnswerList);

    // 修改答案
    int updateQstAnswer(QstAnswer qstAnswer);

    // 删除答案
    int delQstAnswer(List<Integer> answerIdList);

    // 查询答案
    List<Object> getQstAnswer(SaveUserAnwserInfo anwserInfo);

    // 根据paperId查询答案
    List<String> getQstAnswerByPaperId(String paperId);

}
