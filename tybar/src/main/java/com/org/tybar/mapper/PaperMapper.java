package com.org.tybar.mapper;

import com.org.tybar.pojo.Paper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PaperMapper {

    // 查询所有问卷
    List<Paper> getPaperList();

    // 根据用户id查问卷
    List<Paper> getPaperByUserId(int userId);

    // 根据paperId查询问卷
    Paper getPaperByPaperId(String paperId);

    // 新增问卷
    int addPaper(Paper paper);

    // 更新问卷
    int updatePaper(Paper paper);

    // 更改问卷状态（发布）
    int updateStatusPublishById(String paperId);

    // 更改问卷状态（保存）
    int updateStatusSaveById(String paperId);

    // 删除问卷
    int deletePaperByPaperId(String paperId);

}
