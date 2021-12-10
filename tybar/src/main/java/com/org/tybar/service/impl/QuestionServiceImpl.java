package com.org.tybar.service.impl;

import com.org.tybar.mapper.QuestionMapper;
import com.org.tybar.pojo.Question;
import com.org.tybar.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public List<Question> getQuestionsByPaperId(String paperId) {
        return questionMapper.getQuestionsByPaperId(paperId);
    }

    @Override
    public int addQuestion(Question question) {
        return questionMapper.addQuestion(question);
    }

    @Override
    public int updateQuestion(Question question) {
        return questionMapper.updateQuestion(question);
    }

    @Override
    public int deleteQuestionByPaperId(String paperId) {
        return questionMapper.deleteQuestionByPaperId(paperId);
    }
}
