package com.org.tybar.service.impl;

import com.org.tybar.mapper.PaperMapper;
import com.org.tybar.pojo.Paper;
import com.org.tybar.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaperServiceImpl implements PaperService {

    @Autowired
    private PaperMapper paperMapper;

    @Override
    public List<Paper> getPaperList() {
        return paperMapper.getPaperList();
    }

    @Override
    public List<Paper> getPaperByUserId(int userId) {
        return paperMapper.getPaperByUserId(userId);
    }

    @Override
    public Paper getPaperByPaperId(String paperId) {
        return paperMapper.getPaperByPaperId(paperId);
    }

    @Override
    public int addPaper(Paper paper) {
        return paperMapper.addPaper(paper);
    }

    @Override
    public int updatePaper(Paper paper) {
        return paperMapper.updatePaper(paper);
    }

    @Override
    public int updateStatusPublishById(String paperId) {
        return paperMapper.updateStatusPublishById(paperId);
    }

    @Override
    public int updateStatusSaveById(String paperId) {
        return paperMapper.updateStatusSaveById(paperId);
    }

    @Override
    public int deletePaperByPaperId(String paperId) {
        return paperMapper.deletePaperByPaperId(paperId);
    }
}
