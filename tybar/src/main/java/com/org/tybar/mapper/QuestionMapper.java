package com.org.tybar.mapper;

import com.org.tybar.pojo.Question;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface QuestionMapper {

    // 根据paperId查询所有问题
    List<Question> getQuestionsByPaperId(String paperId);

    // 新增问题
    int addQuestion(Question question);

    // 更新问题
    int updateQuestion(Question question);

    // 删除问题
    int deleteQuestionByPaperId(String paperId);
}
