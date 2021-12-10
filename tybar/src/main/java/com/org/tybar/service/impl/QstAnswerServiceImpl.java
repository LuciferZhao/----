package com.org.tybar.service.impl;

import com.org.tybar.mapper.QstAnswerMapper;
import com.org.tybar.pojo.QstAnswer;
import com.org.tybar.pojo.vo.SaveUserAnwserInfo;
import com.org.tybar.service.QstAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QstAnswerServiceImpl implements QstAnswerService {

    @Autowired
    private QstAnswerMapper qstAnswerMapper;

    @Override
    public int addQstAnswer(QstAnswer qstAnswer) {
        return qstAnswerMapper.addQstAnswer(qstAnswer);
    }

    @Override
    public List<QstAnswer> getQstAnswerInfos(String paperId) {
        return qstAnswerMapper.getQstAnswerInfos(paperId);
    }

    @Override
    public QstAnswer getQstAnserInfosById(int answerId) {
        return qstAnswerMapper.getQstAnserInfosById(answerId);
    }

    @Override
    public int copyQstAnswer(List<QstAnswer> qstAnswerList) {
        return qstAnswerMapper.copyQstAnswer(qstAnswerList);
    }

    @Override
    public int updateQstAnswer(QstAnswer qstAnswer) {
        return qstAnswerMapper.updateQstAnswer(qstAnswer);
    }

    @Override
    public int delQstAnswer(List<Integer> answerIdList) {
        return qstAnswerMapper.delQstAnswer(answerIdList);
    }

    @Override
    public List<Object> getQstAnswer(SaveUserAnwserInfo anwserInfo) {
        return qstAnswerMapper.getQstAnswer(anwserInfo);
    }

    @Override
    public List<String> getQstAnswerByPaperId(String paperId) {
        return qstAnswerMapper.getQstAnswerByPaperId(paperId);
    }
}
